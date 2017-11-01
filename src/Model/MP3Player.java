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
    Playlist playlist;
    Track track;


    public MP3Player() {
        minim = new SimpleMinim(true);


    }

    public void play(String fileName) {
        track = new Track(fileName);
        audioPlayer = minim.loadMP3File(track.getPath());

        play();
        System.out.println("Es wird '" + track.getTitle() + "' von " + track.getArtist()+" gespielt. ");

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


        //Lauter
        if (value > 0.5) {
            value = (float) ((value - 0.5) * 100);
            audioPlayer.setGain(value);
            return;
        }
        //leiser
        if (value < 0.5) {
            value = (float) ((0.5 - value) * 100) * (-1);
            audioPlayer.setGain(value);
            return;
        }
        //nichts
        if (value == 0.5) {

            audioPlayer.setGain(0);
            return;
        }


        audioPlayer.setGain(value);
    }

    public void balance(float value) {
        audioPlayer.setBalance(value);
    }





    public void setPlaylist(Playlist actPlaylist){
    this.playlist = actPlaylist;
    }
    public void skip(){
        audioPlayer= minim.loadMP3File(playlist.skip());
    }
     public void skipBack( ){}
    public void shuffle(boolean on){}
    public void repeat(boolean on) {}


    @Override
    public void run() {

    }
}














