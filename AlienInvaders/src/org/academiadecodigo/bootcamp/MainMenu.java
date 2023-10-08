package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MainMenu {
    private Picture picture;
    private boolean isActive;

    public MainMenu() {
        Picture picture = new Picture(Field.PADDING, Field.PADDING, "/Gustavo_Invaders.png");
        picture.draw();

    }

    public void deactivate() {
        if (isActive) {
            picture.delete();
            isActive = false;
        }
    }

    public void activate() {
        if (!isActive){
            picture.draw();
            isActive = true;
        }
    }

    public boolean isActive() {
        return isActive;
    }
}
