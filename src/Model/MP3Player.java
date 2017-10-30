package Model;

import ddf.minim.Playable;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.scene.layout.Background;

/**
 * Created by Pascal, Julian
 */
public class MP3Player implements Runnable {


    SimpleMinim minim;
    SimpleAudioPlayer audioPlayer;


    public MP3Player() {
        minim = new SimpleMinim(true);

    }

    public void play(String fileName) {

        audioPlayer = minim.loadMP3File(fileName);

        play();
        System.out.println("Es wird " + fileName + " gespielt. ");

    }

    public void play() {


        audioPlayer.play();


    }


    public void pause() {

        if (audioPlayer.isPlaying()) {
            audioPlayer.pause();

        }
    }

    public void stop() {
        minim.stop();
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














