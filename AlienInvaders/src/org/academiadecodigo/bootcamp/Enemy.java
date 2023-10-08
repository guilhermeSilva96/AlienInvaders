package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Enemy {

   public static final int ENEMY_SIZE = 50;
    private Picture enemyShape;
    private boolean isDead;
    private EnemyBullet bullet;
    private Field field;

    public Enemy(int x, int y, Field field) {
        enemyShape = new Picture(x,y,"/bossGUSTAVO.png");
        enemyShape.draw();
        this.field = field;

    }


    public int getX() {
        return enemyShape.getX();
    }

    public int getY() {
        return enemyShape.getY();
    }
    public int getWidth() {
        return enemyShape.getWidth();
    }

    public int getHeight() {
        return enemyShape.getHeight();
    }

    public Picture getEnemyShape() {
        return enemyShape;
    }

    public void move(int distance) {
        enemyShape.translate(distance, 0);
    }

    public void die() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public void shoot() {
        int random = (int) Math.floor(Math.random()*10000);
        if (random < 1) {
            this.bullet = new EnemyBullet((getX() + getWidth()/2), (getY() + getHeight()), this.field);
            new Thread(bullet::fire).start();
        }
    }
}