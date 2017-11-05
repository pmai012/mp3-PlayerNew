package Model;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


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
        this.tracks = new List<Track>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Track> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Track track) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Track> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Track> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Track get(int index) {
                return null;
            }

            @Override
            public Track set(int index, Track element) {
                return null;
            }

            @Override
            public void add(int index, Track element) {

            }

            @Override
            public Track remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Track> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Track> listIterator(int index) {
                return null;
            }

            @Override
            public List<Track> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
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
    public void addTrack(Track t){
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

