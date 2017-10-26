package Model;

import ddf.minim.Playable;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.scene.layout.Background;

/**
 * Created by Pascal on 25.10.2017.
 */
public class MP3Player {


    private SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private Thread background;

    public MP3Player() {

    }

    public void play(String fileName) {

        minim = new SimpleMinim();
        audioPlayer = minim.loadMP3File(fileName);
        play();
        System.out.println("Es wird " + fileName + " gespielt");

    }

    public void play() {
      new Thread(){
            public void run() {
                audioPlayer.play();
            }
        }.start();
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






}














