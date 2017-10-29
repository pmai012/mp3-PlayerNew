package Model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PlaylistManager {

    private List<Playlist> playlists;
    public PlaylistManager()
    {

    }

    public List<Playlist> findPlaylist(String name){
        for (Playlist x: playlists) {
            if (x.getName().equals(name)){

            }
        }
        return null;
    }
    public Playlist getAllTracks()
    {
        return new Playlist("AllTracks");
    }
    public void setPlaylist(Playlist actPlaylist){}
    public void deletePlaylist(Playlist actPlaylist){}
    public void updatePlaylist(Playlist actPlaylist){}
}
