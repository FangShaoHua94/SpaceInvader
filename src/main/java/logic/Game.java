package logic;

import gui.Painter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.AlienTeam;
import model.Board;
import model.Tile;
import model.character.Alien;
import model.character.Cannon;
import model.character.projectile.Projectile;

import java.util.ArrayList;

import static logic.FrameDelay.delay;
import static model.AlienTeam.ALIEN_COL;
import static model.AlienTeam.ALIEN_ROW;
import static model.character.Alien.spawnAlien;
import static model.character.Bunker.spawnBunker;
import static model.character.Cannon.spawnCannon;

public class Game implements Runnable {

    private static final int ROW = 150;
    private static final int COL = 200;
    private static final int BUNKER_COUNT = 4;
    private static final long FRAME_DELAY = 50;

    private GraphicsContext gc;
    private Board board;
    private int score;
    private Cannon cannon;

    private AlienTeam alienTeam;
    private ArrayList<Projectile> projectiles;

    public Game(GraphicsContext gc) {
        this.gc = gc;
        board = new Board(ROW, COL);
        projectiles = new ArrayList<>();
        alienTeam = new AlienTeam();
        score = 0;
    }

    public Board getBoard() {
        return board;
    }

    public Cannon getCannon() {
        return cannon;
    }

    @Override
    public void run() {
        initializeGame();
        while (true) {
            Painter.paint(this, gc);
            updateProjectiles();
            alienTeam.move();
            alienTeam.fire().forEach(projectile -> addProjectile(projectile));
            update();
//            board.print();
            delay(FRAME_DELAY);
        }
    }

    private void initializeGame() {
        // add bunker
        for (int i = 0; i < BUNKER_COUNT; i++) {
            board.add(spawnBunker(ROW - 50, i * 50 + 15).getCharacter());
        }

        // add cannon
        cannon = spawnCannon(ROW - 20, COL / 2 - 5);
        board.add(cannon.getCharacter());

        // add alien
        for (int i = 0; i < ALIEN_ROW; i++) {
            for (int j = 0; j < ALIEN_COL; j++) {
                Alien alien = spawnAlien(i * 10 + 1, j * 15 + 20);
                alienTeam.addAlien(alien, i, j);
                board.add(alien.getCharacter());
            }
        }

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

    private void updateProjectiles() {
        ArrayList<Projectile> toBeRemove = new ArrayList<>();
        projectiles.forEach(projectile -> {
            if (withinBoundary(projectile.nextRow(), projectile.getCol())) {
                ArrayList<Tile> collidedTiles = board.collision(projectile.nextPos().getCharacter());
                if (collidedTiles.isEmpty() || collidedTiles.stream().allMatch(tile -> projectile.contains(tile))) {
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

    private ArrayList<Projectile> hit(ArrayList<Tile> collidedTiles) {
        ArrayList<Projectile> toBeRemove = new ArrayList<>();
        collidedTiles.forEach(tile -> {
            Color color = tile.getColor();
            // need to convert color to custom enum to use switch case
            if (color.equals(Color.GREEN)) {
                board.destroy(tile);
            } else if (color.equals(Color.PURPLE)) {
                Alien toBeDestroyed = alienTeam.getAlien(collidedTiles.get(0));
                if (toBeDestroyed != null) {
                    board.destroy(toBeDestroyed.getCharacter());
                }
            } else if (color.equals(Color.ORANGE)) {

            } else if (color.equals(Color.RED) || color.equals(Color.WHITE)) {
                Projectile toBeDestroy = getProjectile(tile);
                if (toBeDestroy != null) {
                    toBeRemove.add(toBeDestroy);
                }
            }
        });
        return toBeRemove;
    }

    public void update() {
        board.updateBoard();
    }

    public static boolean withinBoundary(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COL;
    }

    private void removeProjectile(ArrayList<Projectile> toBeRemove) {
        toBeRemove.forEach(projectile -> removeProjectile(projectile));
    }

    private void removeProjectile(Projectile toBeRemove) {
        board.remove(toBeRemove.getCharacter());
        alienTeam.resetFire(toBeRemove);
        cannon.resetFire(toBeRemove);
        projectiles.remove(toBeRemove);
        update();
    }

    private Projectile getProjectile(Tile tile) {
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).contains(tile)) {
                return projectiles.get(i);
            }
        }
        return null;
    }

}
