package Controller;

import Model.MP3Player;
import Model.PlaylistManager;
import Model.Track;
import javafx.scene.image.Image;


import java.io.ByteArrayInputStream;
import java.util.Observable;



/**
 * Created by User on 06.11.2017.
 */
public class Controller extends Observable {
  //  final String PATH = "F:\\GitHub\\mp3-PlayerNew\\src\\music\\02_LoveWillBeWithYou.mp3";
    //final String PATH2 ="/Users/deniz/IdeaProjects/mp3-PlayerNew/src/music/02_LoveWillBeWithYou.mp3";

    //os.name : "Mac OS X" | "Linux" | ("Windows" (?))

    PlaylistManager playlistManager;
    //Track track = new Track();
    MP3Player player;








    public Controller() {
        player = new MP3Player();

    }

    public MP3Player getPlayer() {
        return player;
    }

    public boolean isplaying() {
        return player.isPlaying();
    }


    public void play(String path) {
        //track = new Track(path);
        play();

    }

    public void play() {


        player.play();



        setChanged();
        notifyObservers("controller");


    }


    public void pause() {

        player.pause();
    }

    public Image getCover() {
        return new Image(new ByteArrayInputStream(player.getcurrentTrack().getCover()));
    }

    public void volume(float lautstaerke) {
        player.volume(lautstaerke);
    }
}


