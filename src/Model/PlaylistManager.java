package Model;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class PlaylistManager {

    private List<Playlist> playlists;
    File newPlaylist = new File(System.getProperty("user.home").concat("//Music") + "/AllTracks.m3u");
    private String extension = ".mp3";

    public PlaylistManager() {
    }

    public List<Playlist> findPlaylist(String name) {
        for (Playlist x : playlists) {
            if (x.getName().equals(name)) {

            }
        }
        return null;
    }

    public Playlist getAllTracks() {

        Playlist playlist = new Playlist("AllTracks");

        createPlaylist(System.getProperty("user.home"), playlist );
        return playlist;
    }

    public void setPlaylist(Playlist actPlaylist) {
    }

    public void deletePlaylist(Playlist actPlaylist) {
        for (Playlist x: playlists) {
            if (x.getName().equals(actPlaylist.getName()))
            {
                playlists.remove(x);
            }
        }
    }

    public void updatePlaylist(Playlist actPlaylist) {

    }


    private Playlist createPlaylist(String directoryName, Playlist playlist){
        try {
            File directory = new File(directoryName);
            File newPlaylist = new File(System.getProperty("user.home").concat("//Music") + "/AllTracks.txt");

            FileWriter writer;

            // get all the files from a directory
            File[] fList = directory.listFiles();
            if (fList != null) {
                for (File file : fList) {
                    if (file.isFile()) {
                        if (file.getName().endsWith(extension)) {
                            writer = new FileWriter(newPlaylist);
                            writer.write(file.getAbsolutePath() + file.getName());
                            playlist.addTrack(new Track(file.getAbsolutePath()));
                        }
                    } else if (file.isDirectory()) {
                        createPlaylist(file.getAbsolutePath(), playlist);
                    }
                }
            }
        }
        catch (FileNotFoundException e1){
            e1.printStackTrace();
        }
        catch (IOException e1){
            e1.printStackTrace();
        }
        return playlist;
    }
}
