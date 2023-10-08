package org.academiadecodigo.bootcamp;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private Clip ladyClip;
    private Clip bulletClip;

    private Clip gameOverClip;

    public void setBackgroundSound() {
        try {
            URL ladyURL = getClass().getResource("/ladyMin.wav");
            AudioInputStream ladyStream = AudioSystem.getAudioInputStream(ladyURL);

            ladyClip = AudioSystem.getClip();
            ladyClip.open(ladyStream);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();

        }

    }

    public void setWeaponSound(){
        try {
            URL bulletURL = getClass().getResource("/bullet.wav");
            AudioInputStream bulletStream = AudioSystem.getAudioInputStream(bulletURL);

            bulletClip = AudioSystem.getClip();
            bulletClip.open(bulletStream);


        }catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void setGameOverSound() {
        try {
            URL gameOverURL = getClass().getResource("/gameOver.wav");
            AudioInputStream gameOverStream = AudioSystem.getAudioInputStream(gameOverURL);

            gameOverClip = AudioSystem.getClip();
            gameOverClip.open(gameOverStream);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();

        }

    }

    public void playBackgroundSound(){
        if (ladyClip != null) {
            ladyClip.loop(Clip.LOOP_CONTINUOUSLY);
        }

    }
    public void playWeapon(){
        if(bulletClip != null) {
            bulletClip.setFramePosition(0);
            bulletClip.start();
        }
    }

    public void stopBackground(){
        if(ladyClip != null && ladyClip.isRunning()){
            ladyClip.stop();
            ladyClip.close();
        }
    }

    public void stopWeapon(){
        if(bulletClip != null && bulletClip.isRunning()){
            bulletClip.stop();
            bulletClip.close();
        }
    }

    public void playGameOver(){
        if(gameOverClip != null){
            gameOverClip.setFramePosition(0);
            gameOverClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}