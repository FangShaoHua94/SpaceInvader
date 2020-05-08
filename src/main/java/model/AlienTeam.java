package model;

import model.character.Alien;
import model.character.projectile.Projectile;

import java.util.ArrayList;
import java.util.Random;

import static logic.Game.withinBoundary;

public class AlienTeam {

    public static final int ALIEN_ROW = 4;
    public static final int ALIEN_COL = 11;
    private static final int FIRING_FREQUENCY = 100;

    private Alien[][] aliens;
    private Direction direction;

    enum Direction {
        LEFT, RIGHT
    }

    public AlienTeam() {
        aliens = new Alien[ALIEN_ROW][ALIEN_COL];
        direction = Direction.RIGHT;
    }

    public void addAlien(Alien alien, int row, int col) {
        aliens[row][col] = alien;
    }

    public void resetFire(Projectile projectile) {
        for (int i = 0; i < ALIEN_ROW; i++) {
            for (int j = 0; j < ALIEN_COL; j++) {
                if (aliens[i][j] != null) {
                    aliens[i][j].resetFire(projectile);
                }
            }
        }
    }

    public void move() {
        Alien alien;
        switch (direction) {
        case LEFT:
            alien = leftMostAlien();
            if (alien == null) {
                break;
            }
            if (withinBoundary(alien.getCharacter().get(0).getRow(), alien.nextLeftCol())) {
                moveLeft();
            } else {
                direction = Direction.RIGHT;
                moveDown();
            }
            break;
        case RIGHT:
            alien = rightMostAlien();
            if (alien == null) {
                break;
            }
            if (withinBoundary(alien.getCharacter().get(0).getRow(), alien.nextRightCol())) {
                moveRight();
            } else {
                direction = Direction.LEFT;
                moveDown();
            }
            break;
        default:
            break;
        }
    }

    private void moveLeft() {
        for (int i = 0; i < ALIEN_ROW; i++) {
            for (int j = 0; j < ALIEN_COL; j++) {
                if (aliens[i][j] != null) {
                    aliens[i][j].moveLeft();
                }
            }
        }
    }

    private void moveRight() {
        for (int i = 0; i < ALIEN_ROW; i++) {
            for (int j = 0; j < ALIEN_COL; j++) {
                if (aliens[i][j] != null) {
                    aliens[i][j].moveRight();
                }
            }
        }
    }

    private void moveDown() {
        for (int i = 0; i < ALIEN_ROW; i++) {
            for (int j = 0; j < ALIEN_COL; j++) {
                if (aliens[i][j] != null) {
                    aliens[i][j].moveDown();
                }
            }
        }
    }

    private Alien leftMostAlien() {
        for (int i = 0; i < ALIEN_COL; i++) {
            for (int j = 0; j < ALIEN_ROW; j++) {
                if (aliens[j][i] != null) {
                    return aliens[j][i];
                }
            }
        }
        return null;
    }

    private Alien rightMostAlien() {
        for (int i = ALIEN_COL - 1; i >= 0; i--) {
            for (int j = 0; j < ALIEN_ROW; j++) {
                if (aliens[j][i] != null) {
                    return aliens[j][i];
                }
            }
        }
        return null;
    }

    public Alien destroyAlien(Tile collidedTile) {
        for (int i = 0; i < ALIEN_ROW; i++) {
            for (int j = 0; j < ALIEN_COL; j++) {
                Alien alien = aliens[i][j];
                if (alien != null && alien.contains(collidedTile)) {
                    aliens[i][j] = null;
                    return alien;
                }
            }
        }
        return null;
    }

    public ArrayList<Projectile> fire() {
        ArrayList<Projectile> projectiles = new ArrayList<>();
        for (int i = 0; i < ALIEN_COL; i++) {
            for (int j = ALIEN_ROW - 1; j >= 0; j--) {
                if (aliens[j][i] != null) {
                    if (toFire(j + i)) {
                        projectiles.add(aliens[j][i].fire());
                    }
                    break;
                }
            }
        }
        return projectiles;
    }

    private boolean toFire(int seed) {
        Random random = new Random(System.currentTimeMillis() + seed);
        int number = random.nextInt(FIRING_FREQUENCY);
        return number == 1;
    }

    public boolean invaded(int targetRow) {
        for (int i = ALIEN_ROW - 1; i >= 0; i--) {
            for (int j = 0; j < ALIEN_COL; j++) {
                if (aliens[i][j] != null
                        && aliens[i][j].getCharacter().stream().anyMatch(tile -> tile.getRow() == targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}
