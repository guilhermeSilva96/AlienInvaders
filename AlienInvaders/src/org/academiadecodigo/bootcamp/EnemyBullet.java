package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class EnemyBullet {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 20;
    private final int speed = 10;
    private Picture bulletShape;
    private Field field;
    private boolean isDead;

    public EnemyBullet(int x, int y, Field field) {

        bulletShape = new Picture(x, y,"/rockThisOne.png");
        bulletShape.draw();
        this.field = field;

    }

    public int getY() {
        return bulletShape.getY();
    }

    public void fire() {
        while (bulletShape.getY() < (Field.HEIGHT - Field.PADDING*3)) {
            bulletShape.translate(0, speed);
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
            int playerPosX = field.getPlayer().getX();
            int playerPosY = field.getPlayer().getY();

            int bulletPosX = bulletShape.getX();
            int bulletPosY = bulletShape.getY();

            if (!field.getPlayer().isDead() && !this.isDead &&
                playerPosX <= bulletPosX && (bulletPosX <= playerPosX + Player.PLAYER_SIZE) &&
                    (playerPosY <= bulletPosY) && (playerPosY + Player.PLAYER_SIZE >= bulletPosY)) {
            System.out.println("you lose");
            bulletHits(bulletShape, field.getPlayer());
            }
        }

    public void die() {
        isDead = true;
    }


    private void bulletHits(Picture bullet, Player player) {
        bullet.delete();
        die();
        player.getPlayerShape().delete();
        player.die();
    }

    public void setField(Field field) {
        this.field = field;
    }
}

