package Model;

import javax.print.attribute.standard.PrinterLocation;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class PlaylistManager {

    private ArrayList<Playlist> playlists;
    private List<String> contents;
    File allTacksPlaylist;
    private String extension = ".mp3";

    public PlaylistManager() {
        allTacksPlaylist = new File(System.getProperty("user.home").concat("//Music") + "/AllTracks.m3u");
        this.playlists = new ArrayList<>();
        contents = new ArrayList<String>();
        contents.add("#EXTM3U");
    }

    public List<Playlist> findPlaylist(String name) {
        for (Playlist x : playlists) {
            if (x.getName().equals(name)) {

            }
        }
        return null;
    }

    public Playlist getAllTracks() throws IOException{

        Playlist playlist = new Playlist("AllTracks", null);

        playlist = searchMP3(System.getProperty("user.home").concat("//Music"), playlist);
        savePlaylist(allTacksPlaylist);
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
    public void createEmptyPlaylist(File playlist){
        savePlaylist(playlist);
    }

    private Playlist searchMP3(String directoryName, Playlist playlist) throws IOException{
            File directory = new File(directoryName);

            Writer output = null;

            // get all the files from a directory
            File[] fList = directory.listFiles();
            if (fList != null) {
                for (File file : fList) {
                    if (file.isFile()) {
                        if (file.getName().endsWith(extension)) {
                            playlist.addTrack(new Track(file.getAbsolutePath()));
                        }
                    } else if (file.isDirectory()) {
                        searchMP3(file.getAbsolutePath(), playlist);
                    }
                }

                for (Track t: playlist.getTracks())
                {StringBuffer songInfo = new StringBuffer("#EXTINF:");
                 songInfo.append(t.getArtist());
                 songInfo.append(" - ");
                 songInfo.append(t.getTitle());
                 contents.add(songInfo.toString());
                 contents.add(t.getPath());
                }
            }
        return playlist;
    }
    private void savePlaylist(File playlist){
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(playlist));
            for (String content : contents) {
                if (content != null) {
                    output.write(content);
                    output.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null)
                try {
                    output.flush();
                    output.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        allTacksPlaylist = null;
        contents = null;
    }

    public ArrayList<Playlist> getPlaylists(){return this.playlists;}



    public ArrayList<Playlist> searchPlaylists(String path)
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
                    searchPlaylists(file.getAbsolutePath());
                }
            }
        }
        return playlists;
    }
}
