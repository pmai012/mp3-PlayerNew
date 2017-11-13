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
    boolean again = false;


    public MP3Player() {
        minim = new SimpleMinim(true);
        playlist = new Playlist("no-name");

    }

  public void loadPlaylist(String path){
        playlist.loadPlaylist(path);
  }

    public void play(Track track) {
        this.track = track;
        audioPlayer = minim.loadMP3File(track.getPath());

        play();

    }

    public void play() {


        audioPlayer.play();

        if (again) {
            audioPlayer.play();
        }
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
        audioPlayer= minim.loadMP3File(playlist.skip().getPath());
    }
     public void skipBack( ){
         audioPlayer= minim.loadMP3File(playlist.skipback().getPath());
     }
    public void shuffle(boolean on){
        audioPlayer= minim.loadMP3File(playlist.shuffle().getPath());
    }
    public void repeat(boolean on) {
        again = on;
    }


    @Override
    public void run() {

    }
}














