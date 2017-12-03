package Controller;

import Model.MP3Player;
import Model.PlaylistManager;
import Model.Track;
import sample.Frameapplication;
import java.util.Observable;

import static sample.Frameapplication.*;


/**
 * Created by User on 06.11.2017.
 */
public class Controller extends Observable {
    final String PATH = "F:\\GitHub\\mp3-PlayerNew\\src\\music\\02_LoveWillBeWithYou.mp3";
    //final String PATH2 ="/Users/deniz/IdeaProjects/mp3-PlayerNew/src/music/02_LoveWillBeWithYou.mp3";

    //os.name : "Mac OS X" | "Linux" | ("Windows" (?))

    PlaylistManager playlistManager;
    Track track ;
    MP3Player player;
    public Controller(){
        player = new MP3Player();

    }

public String title(){
        return player.getTitle();
    }
    public String album(){
        return player.getcurrentTrack().getAlbum();
    }
    public String artist(){
        return player.getcurrentTrack().getArtist();
    }

    public boolean isplaying(){
        return player.isPlaying();
    }

    public byte[] Cover(){
   return     player.getcurrentTrack().getCover();
    }


    public void play(String path){
         track = new Track(path);
        // track = new Track();
        player.play(track);
        setChanged();
        System.out.println(hasChanged());
        notifyObservers();
        System.out.println("Observer: " + countObservers());
    }

    public void play(){
        track = new Track(PATH);
       // track = new Track();
        player.play(track);
        setChanged();
        notifyObservers();
        System.out.println("Observer: " + countObservers());

    }



    public void pause(){

        player.pause();
    }

    public String getTitle(){
        return track.getTitle();
    }

    public String getArtist(){
        return track.getArtist();
    }

    public String getAlbum(){

        return track.getAlbum();
    }

    public void volume(float lautstaerke){
        player.volume(lautstaerke);
    }
public byte[] getCover(){
        return track.getCover();
}
}
