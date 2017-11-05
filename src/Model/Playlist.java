package Model;

import java.io.*;
import java.util.List;


/**
 * Created by Pascal on 29.10.2017.
 */
public class Playlist {
    private int index = 0;
    private Track currentTrack = null;

    private List<Track> tracks;


    private String name;
    final String extension = ".m3u";





    public Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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
/*
Beispiel m3u:
0 #EXTM3U
1 #EXTINF:0,2Pac - Ghetto Gospel.mp3
2 E:\Diverse Interpreten\2Pac - Ghetto Gospel.mp3
3 #EXTINF:0,50 Cent - I'll Still Kill (feat. Akon).mp3
4 E:\Diverse Interpreten\50 Cent - I'll Still Kill (feat. Akon).mp3
 */
    public void loadPlaylist(String path){
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


    }
}

