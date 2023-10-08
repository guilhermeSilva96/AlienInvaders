package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import static org.academiadecodigo.bootcamp.Field.PADDING;

public class Player implements KeyboardHandler {
    private int bulletDamage = 1; //will have class bullet to change damage
    private Keyboard keyboard;
    private Picture playerShape;
    private PlayerBullet bullet;
    private Field field;
    public static final int PLAYER_SIZE = 50;
    public boolean isDead;

    public Player(Field field) {
        int startX = (Field.WIDTH - PLAYER_SIZE) /2;
        int startY = Field.HEIGHT-100; // initial position of y;

        playerShape = new Picture(startX,startY,"/bossNOZK.png");
        playerShape.draw();

        keyboard = new Keyboard(this);
        keyboardInit();

        this.field = field;
    }

    public int getX() {
        return playerShape.getX();
    }

    public int getY() {
        return playerShape.getY();
    }

    public void shoot() {
        if(isDead){
            return;
        }
        int random = (int) Math.floor(Math.random()*14);
        if (random < 7) {
            Sound sound = new Sound();
            sound.setWeaponSound();
            sound.playWeapon();
            this.bullet = new PlayerBullet(getX() + (playerShape.getWidth() - PADDING) / 2, getY(), this.field);
            new Thread(bullet::fire).start();
        }
    }

    public PlayerBullet getBullet() {
        return bullet;
    }

    public void keyboardInit() {
        Keyboard keyboard = new Keyboard(this);

        //Move right
        KeyboardEvent rightPress = new KeyboardEvent();
        rightPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        rightPress.setKey(KeyboardEvent.KEY_RIGHT);

        //Move left
        KeyboardEvent leftPress = new KeyboardEvent();
        leftPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        leftPress.setKey(KeyboardEvent.KEY_LEFT);

        //shoot
        KeyboardEvent spacePress = new KeyboardEvent();
        spacePress.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        spacePress.setKey(KeyboardEvent.KEY_SPACE);

        keyboard.addEventListener(rightPress);
        keyboard.addEventListener(leftPress);
        keyboard.addEventListener(spacePress);
    }

    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                if (this.playerShape.getX() > Field.PADDING * 2) {
                    this.playerShape.translate(-20.0, 0.0);
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (this.playerShape.getX() + this.playerShape.getWidth() < Field.WIDTH + PADDING) {
                    this.playerShape.translate(20.0, 0.0);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_SPACE: {
                if (!isDead){
                    shoot();
                }
            }
        }
    }

    public void die() {
        this.isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public Picture getPlayerShape() {
        return playerShape;
    }
}

