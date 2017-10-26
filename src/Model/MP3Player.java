package Model;

import ddf.minim.Playable;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.scene.layout.Background;

/**
 * Created by Pascal, Julian
 */
public class MP3Player implements Runnable{


    private SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private Thread background;

    public MP3Player() {

    }

    public void play(String fileName) {

        minim = new SimpleMinim(true);
        audioPlayer = minim.loadMP3File(fileName);
        play();
        System.out.println("Es wird " + fileName + " gespielt");

    }

    public void play() {

        audioPlayer.play();


    }


    public void pause() {

        audioPlayer.pause();
    }

    public void stop() {
    }


    public void volume(float value) {
        audioPlayer.setVolume(value);
    }

    public void balance(float value) {
        audioPlayer.setBalance(value);
    }


    @Override
    public void run() {

    }
}














