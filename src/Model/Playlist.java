package Model;

import java.io.*;
import java.util.*;


/**
 * Created by Pascal on 29.10.2017.
 */
public class Playlist {
    private int index = 0;
    private Track currentTrack = null;

    private ArrayList<Track> tracks;

    private String name;
    final String extension = ".m3u";

public Playlist(){
    this.tracks = new ArrayList<Track>(); {
    }
}

    public Playlist(String name, String path) {
        this.name = name;
        this.tracks = new ArrayList<Track>();
    }

    public String getName() {
        return this.name;
    }


    public Track getCurrentTrackTrack(){
    return currentTrack;
    }


    public Track skip() {
        index++;
        currentTrack = tracks.get(index);
        return currentTrack;
    }

    public Track skipback() {

            index= index-1;
     if (index > 0) {
         currentTrack = tracks.get(index);
     }
        return currentTrack;
    }

    public Track shuffle() {
        currentTrack = tracks.get((int) (Math.random() * tracks.size()));
        return currentTrack;
    }

    public ArrayList<Track> getTracks(){return tracks;}

    public void addTrack(Track t) {
        tracks.add(t);
    }

    /*
Beispiel m3u:
0 #EXTM3U
1 #EXTINF:0,2Pac - Ghetto Gospel.mp3
2 E:\Diverse Interpreten\2Pac - Ghetto Gospel.mp3
3 #EXTINF:0,50 Cent - I'll Still Kill (feat. Akon).mp3
4 E:\Diverse Interpreten\50 Cent - I'll Still Kill (feat. Akon).mp3
 */
    public void loadPlaylist(String path) {

        String zeile = null;
        boolean next = false;

        BufferedReader datei = null;
        try {
            datei = new BufferedReader(new FileReader(path));

            while ((zeile = datei.readLine()) != null) // liest zeilenweise aus Datei
            {
                if (next == true) {
                    String trackpath =  zeile;



                    tracks.add(new Track(trackpath));



                    next = false;
                }

                if (zeile.startsWith("#EXTINF:")) {

                    next = true;
                }
                if (zeile.startsWith("#EXTM3U")) {   //Zeile exestiert

                }
                if (zeile.isEmpty() == true) {

                }

            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        currentTrack = tracks.get(0);

    }
}

