package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class PlayerBullet {

    private final int speed = 35;
    private Picture bulletShape;
    private Field field;
    private boolean isDead;

    public PlayerBullet(int x, int y, Field field) {

        bulletShape = new Picture(x, y,"/flower.png");
        bulletShape.draw();
        this.field = field;
    }

    public void fire() {
        while (bulletShape.getY() > 0) {
            bulletShape.translate(0, -speed);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            checkBulletHit();
        }
        bulletShape.delete();
    }

    public void checkBulletHit() {
        for (int i = 0; i < field.getEnemies().length; i++) {
            int enemyPosX = field.getEnemies()[i].getEnemyShape().getX();
            int enemyPosY = field.getEnemies()[i].getEnemyShape().getY();

            int bulletPosX = bulletShape.getX();
            int bulletPosY = bulletShape.getY();

            if (!field.getEnemies()[i].isDead() && !this.isDead &&
                    enemyPosX <= bulletPosX && (bulletPosX <= enemyPosX + Enemy.ENEMY_SIZE)
                    && (enemyPosY <= bulletPosY) && (enemyPosY + Enemy.ENEMY_SIZE >= bulletPosY)) {

                System.out.println("boom");

                bulletHits(bulletShape, field.getEnemies()[i]);
            }
        }
    }

    public void die() {
        isDead = true;
    }


    private void bulletHits(Picture bullet, Enemy enemy) {
        bulletShape.delete();
        die();
        enemy.getEnemyShape().delete();
        enemy.die();
    }

    public void setField(Field field) {
        this.field = field;
    }
}