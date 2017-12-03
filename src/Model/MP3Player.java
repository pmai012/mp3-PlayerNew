package Model;


import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.InvalidationListener;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Observable;

/**
 * Created by Pascal, Julian
 */
public class MP3Player  {


    private SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private Playlist playlist;
    private Track track;

    boolean again = true;


    boolean playing = false;


    /**
     * Gibt den aktuellen track heraus
     *
     * @return aktueller Track.
     */
    public Track getcurrentTrack() {
        return track;
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

    public String getTitle() {
        return track.getTitle();
    }

    public String getAlbum() {
        return track.getAlbum();
    }

    public String getArtist() {
        return track.getArtist();
    }


    public void play() {


        if (track != null) {
            audioPlayer = minim.loadMP3File(track.getPath());
            audioPlayer.cue((int) track.getCurrenttime());
        }
        System.out.println(track.getTitle() + " wird gespielt ");
        playing = true;

        audioPlayer.play();
        if (again) {
            audioPlayer.loop();

        }


    }


    public void pause() {

        track.setCurrenttime(audioPlayer.position());
        audioPlayer.pause();
        playing = false;


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


    public boolean isShuffle() {
        return playlist.isShuffling();
    }


    public void setPlaylist(Playlist actPlaylist) {
        this.playlist = actPlaylist;
    }

    public void skip() {
        minim.stop();
        track = playlist.skip();
        audioPlayer = minim.loadMP3File(track.getPath());
        play();
    }

    public void skipBack() {
        minim.stop();
        track = playlist.skipback();
        audioPlayer = minim.loadMP3File(track.getPath());
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


}














