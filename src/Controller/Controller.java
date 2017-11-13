package Controller;

import Model.MP3Player;
import Model.PlaylistManager;
import Model.Track;


/**
 * Created by User on 06.11.2017.
 */
public class Controller {
    final String PATH = "F:\\GitHub\\mp3-PlayerNew\\src\\music\\02_LoveWillBeWithYou.mp3";
    MP3Player player;
    PlaylistManager playlistManager;
    Track track = new Track(PATH);
    public Controller(){
        player = new MP3Player();
    }

    public void play(){

        player.play(track);
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
public byte[] getCover(){
        return track.getCover();
}
}
