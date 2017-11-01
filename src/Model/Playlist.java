package Model;

import java.io.File;
import java.util.List;

/**
 * Created by Pascal on 29.10.2017.
 */
public class Playlist {

    private List<Track> tracks ;

    private String name;

    public Playlist(String name){
        this.name = name;
    }
    public String getName()
    {return this.name;}

    public String skip(){
        return null;
    }
}

