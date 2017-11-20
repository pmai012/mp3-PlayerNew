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
    Playlist playlist ;
    Track track;
    boolean again = false;
    boolean playing = false;


    public MP3Player() {
        minim = new SimpleMinim(true);
       playlist = new Playlist();


    }

  public void loadPlaylist(String path){

       playlist.loadPlaylist(path);
        track = playlist.getCurrentTrackTrack();


  }

    public void play(Track track) {
      if (playing == false) {
          this.track = track;
          audioPlayer = minim.loadMP3File(track.getPath());
            playing = true;

          play();
      }
    }

    public void play() {

if (track != null){
    audioPlayer = minim.loadMP3File(track.getPath());
    audioPlayer.cue((int)track.getCurrenttime());
}
        audioPlayer.play();

        if (again) {
            audioPlayer.play();
        }
    }


    public void pause() {

      track.setCurrenttime(audioPlayer.position());
            audioPlayer.pause();


    }

    public void stop() {
        minim.stop();
        track = null;
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
        minim.stop();
        track = playlist.skip();
        audioPlayer= minim.loadMP3File(track.getPath());
        audioPlayer.play();
    }
     public void skipBack( ){
        minim.stop();
        track =playlist.skipback();
         audioPlayer= minim.loadMP3File(track.getPath());
         audioPlayer.play();
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














