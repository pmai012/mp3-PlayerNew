package Model;

import java.io.File;
import java.util.List;

/**
 * Created by Pascal on 29.10.2017.
 */
public class Playlist {
    private int index = 0;
    private Track currentTrack = null;

    private List<Track> tracks;

    private String name;

    public Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Track skip() {
        currentTrack =  tracks.get(index+1);
        return   currentTrack;
    }
}

