package Model;

import javax.print.attribute.standard.PrinterLocation;
import java.io.*;
import java.util.*;


public class PlaylistManager {

    private ArrayList<Playlist> playlists;
    private List<String> contents;
    File newPlaylist = new File(System.getProperty("user.home").concat("//Music") + "/AllTracks.m3u");
    private String extension = ".mp3";

    public PlaylistManager() {
        this.playlists = new ArrayList<>();
    }

    public List<Playlist> findPlaylist(String name) {
        for (Playlist x : playlists) {
            if (x.getName().equals(name)) {

            }
        }
        return null;
    }

    public Playlist getAllTracks() {

        Playlist playlist = new Playlist("AllTracks", null);

        playlist = createPlaylist(System.getProperty("user.home").concat("//Music"), playlist );
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
            File directory = new File(directoryName);

            Writer output = null;

            // get all the files from a directory
            File[] fList = directory.listFiles();
            if (fList != null) {
                for (File file : fList) {
                    if (file.isFile()) {
                        if (file.getName().endsWith(extension)) {
                            contents.add(file.getAbsolutePath() + file.getName());
                            playlist.addTrack(new Track(file.getAbsolutePath()));
                        }
                    } else if (file.isDirectory()) {
                        createPlaylist(file.getAbsolutePath(), playlist);
                    }
                }
            }
        return playlist;
    }
    private void savePlaylist(){}

    public List getPlaylists(){return this.playlists;}

    public List<Playlist> loadPlaylists(String path)
    {
//        String path = System.getProperty("user.home").concat("//Music");
        File search = new File(path);

        File[] fList = search.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    if (file.getName().endsWith(".m3u")) {
                        this.playlists.add(new Playlist(file.getName(), file.getAbsolutePath()));
                    }
                } else if (file.isDirectory()) {
                    loadPlaylists(file.getAbsolutePath());
                }
            }
        }
        return playlists;
    }
}
