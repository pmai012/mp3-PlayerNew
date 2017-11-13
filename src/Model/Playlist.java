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
    tracks = new ArrayList<Track>() {
    };
}

    public Playlist(String name, String path) {
        this.name = name;
        this.tracks = new ArrayList<Track>();
    }

    public String getName() {
        return this.name;
    }


    public Track getTrack(int index){
    return tracks.get(index);
    }


    public Track skip() {
        currentTrack = tracks.get(index + 1);
        return currentTrack;
    }

    public Track skipback() {
        currentTrack = tracks.get(index - 1);
        return currentTrack;
    }

    public Track shuffle() {
        currentTrack = tracks.get((int) (Math.random() * tracks.size()));
        return currentTrack;
    }


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

                    if (trackpath.isEmpty() != false){
                        System.out.println(trackpath);

                   tracks.add(new Track(trackpath));
                    }

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



        /*
        BufferedReader auslesen = null;
        String quelle;
        try {
            auslesen = new BufferedReader(new FileReader(path));
            while ((quelle=auslesen.readLine())!= null) {
            }
            //Leere Zeilen werden übersprungen
            String[] zeilen = quelle.split("[\\r\\n]+");

            //Auslesen der Quelldatei jede 2. Zeile gibt den Pfad an
            for (int i = 2; i <zeilen.length; i=i+2){
                tracks.add(new Track(zeilen[i]));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

*/
    }
}

