package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Field implements KeyboardHandler{

    public static final int PADDING = 10;
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 900;
    private static final int ENEMY_SIZE = 50;
    private static final int NUM_ROWS = 4;
    private static final int NUM_COL = 6;
    private static final int ENEMY_GAP = 20;
    private static final int ENEMY_MOVEMENT_X = 5;
    private Sound sound;
    private Player player;
    private Picture field;
    private Enemy[] enemies = new Enemy[NUM_COL * NUM_ROWS];
    private MainMenu mainMenu;
    private int direction; // 1 for moving right, -1 for moving left

    private Picture gameOver;

    public Field() {
        mainMenu = new MainMenu();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        field = new Picture(PADDING, PADDING, "Background.png");
        field.draw();
        this.sound = new Sound();
        sound.setBackgroundSound();
        sound.playBackgroundSound();


    }

    public void gameInit() {
        player = new Player(this);
        createEnemies();
        startGameLoop();

    }

    private void createEnemies() {

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int i = 0; i < NUM_COL; i++) {
                int initialX = 75;
                int y = 75;

                int x = initialX + i * (ENEMY_SIZE + ENEMY_GAP);
                int index = row * NUM_COL + i;

                int enemyX = x;
                int enemyY = y + (ENEMY_SIZE + ENEMY_GAP) * row;

                enemies[index] = new Enemy(enemyX, enemyY, this);

                enemies[index].getEnemyShape().translate(0, 20 * row);
                enemies[index].getEnemyShape().draw();
            }
        }
        direction = -1;
    }


    private void startGameLoop() {
        while (!player.isDead) {
            moveEnemies();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameOver();


    }

    private void moveEnemies() {
        int movementX = ENEMY_MOVEMENT_X * direction;

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int i = 0; i < NUM_COL; i++) {

                int index = row * NUM_COL + i;
                enemies[index].shoot();

                if (enemies[index] != null) {
                    int currentX = enemies[index].getX();

                    if (currentX <= PADDING && direction == -1) {
                        direction = 1;
                        enemies[index].move(movementX);
                    } else if (currentX >= WIDTH - ENEMY_SIZE - PADDING && direction == 1) {
                        direction = -1;
                        enemies[index].move(movementX);
                    } else {
                        int nextX = currentX + movementX;
                        if (nextX >= PADDING && nextX <= WIDTH - ENEMY_SIZE - PADDING) {
                            enemies[index].move(movementX);
                        }

                    }
                }
            }
        }
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public void gameOver() {
        sound.stopBackground();
        sound.stopWeapon();
        gameOver = new Picture(PADDING, PADDING, "/gameOver.png");
        gameOver.draw();
        sound.setGameOverSound();
        sound.playGameOver();
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_S:
                if (!this.mainMenu.isActive()){
                    mainMenu.activate();
                } else {
                    mainMenu.deactivate();
                }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        //nop
    }
}


