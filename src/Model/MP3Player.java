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


        if (value > 0.5){
            value = (float) ((value - 0.5)*100);
            audioPlayer.setGain(value);
            return;
        }

        if (value < 0.5){
            value = (float) ((0.5-value)*100)*(-1);
            audioPlayer.setGain(value);
            return;
        }

        if (value == 0.5){

            audioPlayer.setGain(0);
            return;
        }



        audioPlayer.setGain(value);
    }

    public void balance(float value) {
        audioPlayer.setBalance(value);
    }


    @Override
    public void run() {

    }
}














