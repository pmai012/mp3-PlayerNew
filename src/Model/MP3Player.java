package Model;

import ddf.minim.Playable;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.scene.layout.Background;

/**
 * Created by Pascal, Julian
 */
public class MP3Player implements Runnable{


    SimpleMinim minim;
    SimpleAudioPlayer audioPlayer;


    public MP3Player() {
       minim = new SimpleMinim(true);

    }

    public synchronized void play(String fileName) {

        audioPlayer = minim.loadMP3File(fileName);

        play();
    System.out.println("Es wird " + fileName + " gespielt. \n Du kannst es nicht beenden! Du musst ihn bis zum Ende hören!!!!!!!!!!!!");

    }

    public synchronized void play() {


    audioPlayer.play();


    }


    public  synchronized void pause() {

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














