package Model;


import ddf.minim.AudioListener;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.InvalidationListener;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Pascal, Julian
 */
public class MP3Player  extends Observable implements Observer  {


    private long currentTime = 0;
    private SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;

    public Playlist getPlaylist() {
        return playlist;
    }

    private Playlist playlist;
    private Track currenttrack;

    boolean again = true;
    boolean playing = false;




    public long getCurrentTime() {
        currentTime = audioPlayer.position();
        return currentTime;
    }



    /**
     * Gibt den aktuellen track heraus
     *
     * @return aktueller Track.
     */
    public Track getcurrentTrack() {
        return currenttrack;
    }



    ;

    public boolean isPlaying() {
        return playing;
    }

    public MP3Player() {
        minim = new SimpleMinim(true);
        playlist = new Playlist();




    }

    /**
     * Load Playlist
     *
     * @param path laed die Playlist des aktuellen Pfades
     */
    public void loadPlaylist(String path) {


        playlist.loadPlaylist(path);
        currenttrack = playlist.getTrack(0);


    }


    /**
     * Diese Methode braucht ein Track.
     * @param track
     */

    public void play(Track track) {
        if (playing == false) {

            this.currenttrack = track;

            playing = true;

            play();
        }
    }

    public String getTitle() {
        if (currenttrack.getTitle() == null) {
            return "";
        }
        return currenttrack.getTitle();
    }

    public String getAlbum() {
        return currenttrack.getAlbum();
    }

    public String getArtist() {
        return currenttrack.getArtist();
    }

    public void setTrack(int number) {
        playlist.setCurrentTrack(number);
    }


    public void repeatSong(){
        pause();

        currentTime = 0;
        play();
    }

    public void play() {

        if (getcurrentTrack() == null){
           return;
       }

        if (currenttrack != null) {

            audioPlayer = minim.loadMP3File(currenttrack.getPath());


        System.out.println(currenttrack.getTitle() + " wird gespielt ");
        playing = true;


        audioPlayer.play((int) currentTime);




    }
        setChanged();
        notifyObservers("player");
    }


    public void pause() {

        currentTime = audioPlayer.position();

        audioPlayer.pause();
        playing = false;


    }

    public void stop() {
        minim.stop();
        currenttrack = null;

    }
public float getVolume(){
        return audioPlayer.getVolume();
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

    public void setCurrentNumber(int number) {


        playlist.setCurrentTrack(number);
        stop();
        currenttrack = playlist.getCurrentTrackTrack();
        currentTime = 0;

        setChanged();
        notifyObservers("player");

    }

    public boolean isShuffle() {
        return playlist.isShuffling();
    }

//Jetzt gehts
    public void setPlaylist(Playlist actPlaylist) {
    Playlist neu = new Playlist();
    for(int i = 0; i < actPlaylist.getTracks().size(); i++){
        neu.addTrack(actPlaylist.getTrack(i));
    }
    this.playlist = neu;
    }

    public void skip() {
        minim.stop();
        currenttrack = playlist.skip();
        audioPlayer = minim.loadMP3File(currenttrack.getPath());
        currentTime = 0;
        play();
    }

    public void skipBack() {
        minim.stop();
        currenttrack = playlist.skipback();
        audioPlayer = minim.loadMP3File(currenttrack.getPath());
        currentTime = 0;
        play();

    }

    public void shuffle(boolean on) {
        if (on) {
            playlist.shuffle(on);
        }

    }

    public void repeat(boolean on) {
        again = on;

    }

    @Override
    public void update(Observable o, Object arg) {
        o.equals(audioPlayer);
    }


    public void clear() {
    playlist.clear();
    }
}














