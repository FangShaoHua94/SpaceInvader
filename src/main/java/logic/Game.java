package logic;

import gui.Painter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.AlienTeam;
import model.Board;
import model.Live;
import model.Score;
import model.Tile;
import model.character.Alien;
import model.character.Bunker;
import model.character.Cannon;
import model.character.MysteryShip;
import model.character.projectile.AlienProjectile;
import model.character.projectile.CannonProjectile;
import model.character.projectile.Projectile;

import java.util.ArrayList;
import java.util.Random;

import static logic.FrameDelay.delay;
import static model.AlienTeam.ALIEN_COL;
import static model.AlienTeam.ALIEN_ROW;
import static model.Live.startingLive;
import static model.Score.startingScore;
import static model.character.Alien.spawnAlien;
import static model.character.Bunker.spawnBunker;
import static model.character.Cannon.spawnCannon;
import static model.character.MysteryShip.spawnMysteryShip;

public class Game implements Runnable {

    private static final int ROW = 150;
    private static final int COL = 200;
    private static final int BUNKER_COUNT = 4;
    private static final long GAME_DELAY = 50;
    private static final long FLICKING_DELAY=20;
    private static final int MYSTERY_SHIP_FREQUENCY = 100;

    private GraphicsContext gc;
    private Board board;
    private Cannon cannon;
    private MysteryShip mysteryShip;
    private AlienTeam alienTeam;
    private ArrayList<Projectile> projectiles;
    private boolean terminated;

    private Live live;
    private Score score;

    public Game(GraphicsContext gc) {
        this.gc = gc;
        board = new Board(ROW, COL);
        projectiles = new ArrayList<>();
        alienTeam = new AlienTeam();
        score = startingScore();
        live = startingLive();
        terminated=false;
    }

    public Board getBoard() {
        return board;
    }

    public Cannon getCannon() {
        return cannon;
    }

    public Score getScore(){
        return score;
    }

    public Live getLive(){
        return live;
    }

    public boolean isTerminated(){
        return terminated;
    }

    @Override
    public void run() {
        initializeGame();
        while (!engGame()) {
            updateProjectiles();
            updateMysteryShip();
            updateAlienTeam();
            updateCannon();
            updateBoard();
            Painter.paint(this, gc);
            delay(GAME_DELAY);
        }
        terminated=true;
        endGameEffect();
    }

    private void initializeGame() {
        setBunker();
        setCannon();
        setAlienTeam();
        setMysteryShip();
    }

    private void setMysteryShip() {
        Random random = new Random(System.currentTimeMillis());
        if (random.nextInt(MYSTERY_SHIP_FREQUENCY) == 0 && !MysteryShip.isPresent) {
            mysteryShip = spawnMysteryShip(5, COL - 10);
            board.add(mysteryShip.getCharacter());
        }
    }

    private void setBunker() {
        for (int i = 0; i < BUNKER_COUNT; i++) {
            board.add(spawnBunker(ROW - 50, i * 50 + 15).getCharacter());
        }
    }

    private void setCannon() {
        if (!live.noMoreLive()) {
            cannon = spawnCannon(ROW - 20, COL / 2 - 5);
            board.add(cannon.getCharacter());
        }
    }

    private void setAlienTeam() {
        for (int i = 0; i < ALIEN_ROW; i++) {
            for (int j = 0; j < ALIEN_COL; j++) {
                Alien alien = spawnAlien(i * 10 + 10, j * 15 + 20);
                alienTeam.addAlien(alien, i, j);
                board.add(alien.getCharacter());
            }
        }
    }

    private boolean engGame() {
        return live.noMoreLive() || alienTeam.invaded(ROW - 70);
    }

    private void endGameEffect(){
        for(int i=0;i<COL;i++){
            for (int j=0;j<ROW-12;j++){
                if((i+j)%2==0){
                    board.add(new Tile(j,i, Color.GREEN));
                }else{
                    board.add(new Tile(j,i, Color.BLACK));
                }
            }
            Painter.paint(this,gc);
            delay(FLICKING_DELAY);
        }
    }

    private void updateMysteryShip() {
        if (MysteryShip.isPresent) {
            if (withinBoundary(mysteryShip.getCharacter().get(0).getRow(), mysteryShip.nextLeftCol())) {
                mysteryShip.moveLeft();
            } else {
                destroyMysteryShip();
            }
        } else {
            setMysteryShip();
        }
    }

    private void updateAlienTeam() {
        alienTeam.move();
        alienTeam.fire().forEach(this::addProjectile);
    }

    private void updateCannon() {
        if (Cannon.isDestroyed) {
            setCannon();
        }
    }

    private void updateProjectiles() {
        ArrayList<Projectile> toBeRemove = new ArrayList<>();
        projectiles.forEach(projectile -> {
            if (withinBoundary(projectile.nextRow(), projectile.getCol())) {
                ArrayList<Tile> collidedTiles = board.collision(projectile.nextPos().getCharacter());
                if (collidedTiles.isEmpty() || collidedTiles.stream().allMatch(projectile::contains)) {
                    projectile.advance();
                } else {
                    toBeRemove.addAll(hit(collidedTiles));
                    toBeRemove.add(projectile);
                }
            } else {
                toBeRemove.add(projectile);
            }
        });
        removeProjectile(toBeRemove);
    }

    public void updateBoard() {
        board.updateBoard();
    }

    public void addProjectile(Projectile projectile) {
        if (projectile == null) {
            return;
        }
        ArrayList<Tile> collidedTiles = board.collision(projectile.getCharacter());
        if (collidedTiles.isEmpty()) {
            // no collision
            projectiles.add(projectile);
            board.add(projectile.getCharacter());
        } else {
            // collision
            hit(collidedTiles);
            removeProjectile(projectile);
        }
    }

    private ArrayList<Projectile> hit(ArrayList<Tile> collidedTiles) {
        ArrayList<Projectile> toBeRemove = new ArrayList<>();

        collidedTiles.forEach(tile -> {
            if (Bunker.belongTo(tile)) {
                board.destroy(tile);
            } else if (Alien.belongTo(tile)) {
                Alien toBeDestroyed = alienTeam.destroyAlien(collidedTiles.get(0));
                if (toBeDestroyed != null) {
                    score.addPoint(toBeDestroyed.getPoint());
                    board.destroy(toBeDestroyed.getCharacter());
                }
            } else if (Cannon.belongTo(tile) && !Cannon.isDestroyed) {
                live.reduceLive();
                board.destroy(cannon.getCharacter());
                Cannon.isDestroyed = true;
            } else if (AlienProjectile.belongTo(tile) || CannonProjectile.belongTo(tile)) {
                Projectile toBeDestroy = getProjectile(tile);
                if (toBeDestroy != null) {
                    toBeRemove.add(toBeDestroy);
                }
            } else if (MysteryShip.belongTo(tile) && MysteryShip.isPresent) {
                score.addPoint(mysteryShip.getPoint());
                destroyMysteryShip();
            }
        });
        return toBeRemove;
    }

    public static boolean withinBoundary(int row, int col) {
        return row >= 0 && row < ROW-12 && col >= 0 && col < COL;
    }

    private void removeProjectile(ArrayList<Projectile> toBeRemove) {
        toBeRemove.forEach(this::removeProjectile);
    }

    private void removeProjectile(Projectile toBeRemove) {
        board.destroy(toBeRemove.getCharacter());
        alienTeam.resetFire(toBeRemove);
        cannon.resetFire(toBeRemove);
        projectiles.remove(toBeRemove);
        updateBoard();
    }

    private Projectile getProjectile(Tile tile) {
        for (Projectile projectile : projectiles) {
            if (projectile.contains(tile)) {
                return projectile;
            }
        }
        return null;
    }

    private void destroyMysteryShip() {
        board.destroy(mysteryShip.getCharacter());
        mysteryShip = null;
        MysteryShip.isPresent = false;
    }

}
