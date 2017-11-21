package Model;

import java.io.*;
import java.util.*;


/**
 2  * Die Playlist Klasse.
 3  * Diese laed und verwaltet die rueckgabe der Tracks.
 4  *
 5
 * @author Julian
6
 * @version 1.0
7  */
public class Playlist {

    private int index = 0;
    private Track currentTrack = null;
    private ArrayList<Track> tracks;
    private ArrayList<Track> queue;
    private boolean shuffling = false;
    private String name;
    boolean repeat = true;
    final String extension = ".m3u";


    /**
     * Man kann setzen ob die Playlist nach einmaligem Durchspielen von vorne anfängt
     * @param repeat true es wiederholt sich,, false es wiederholt sich nicht
     */
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }


    /**
     * Aktueller stand ob es sich wiederholt
     * @return true es wiederholt sich, false es wieerholt sich nicht
     */
    public boolean isRepeat() {
        return repeat;
    }




    /**
     * isshuffling
     * Gibt zurueck ob der Shuffling Modus aktiviert wurde
     * @return Gibt zurück ob der shuffling Modus aktiviert ist
     */
    public boolean isShuffling() {
        return shuffling;
    }

    /**
     * Der defaultkonstruktor hat keinen Pfad und keinen Namen
     */
    public Playlist(){
    this.tracks = new ArrayList<Track>();
    this.queue = new ArrayList<Track>();
}

    /**
     * Der konstruktor benötigt Name und Pfad
     * @param name Der Name der Playlist. Frei wählbar
     * @param path Der Pfad ,wo sich die Playlist aufhält
     */
    public Playlist(String name, String path) {
        this.name = name;
        this.tracks = new ArrayList<Track>();
        this.queue = new ArrayList<Track>();
    }

    /**
     * getName
     * @return gibt den Namen der aktuellen Playlist zurueck
     */
    public String getName() {
        return this.name;
    }


    /**
     * getCurrentTrack
     * @return gibt den aktuellen Track zurueck
     */
    public Track getCurrentTrackTrack(){
    return currentTrack;
    }


    /**
     * skip
     * Springt zum naechsten Song.
     * @return gibt dden naechsten Song zurueck, sollte mkein Trck mehr vorhanden sein, wird Null zurueckgegeben.ist
     * repeat aktiviert gibt er den ersten Song wieder zuruck
     */
    public Track skip() {
        index++;
        if (index < queue.size()) {
            currentTrack = queue.get(index);
            return currentTrack;
        }
        if (repeat){
            index = 0;
            currentTrack = queue.get(index);
            return currentTrack;
        }
        return null;
    }

    /**
     * Gibt den vorherigen Track wieder
     *
     * @return Gibt den vorherigen Track wieder. ist keiner vorhanden mehr gibt er null zurueck
     * Ist repeat aktiviert gibt er den letzten Song wieder
     * */
    public Track skipback() {
        index--;
        if (index > -1) {
            currentTrack = queue.get(index);
            return currentTrack;
        }
        if (repeat){
            index = queue.size()-1;
            currentTrack = queue.get(index);
            return currentTrack;
        }
        return null;
    }

    /**
     * Merge mischt die playlist angepasst nach shuffle. Wenn shuffle aktiviert ist wird die Playlist zufaellig gemischt.
     * Der aktuelle Track  bleibt jedoch gleich. Dadurch kann man waehrend des Musik hören mischen.
     */
    public void merge(){

        queue = new ArrayList<Track>();
        for (int i = 0; i < tracks.size(); i++) {
            queue.add(tracks.get(i));

        }

        if (shuffling == true) {
            int s;

            for (int i = 0; i < queue.size(); i++){

                if (i != index) {

                    do {
                        s = s = i + (int) (Math.random() * (queue.size() - i));
                        }
                    while (s == index);


                    Track change = queue.get(i);
                    queue.set(i, queue.get(s));
                    queue.set(s, change);
                }
            }

        }
    }


    /**
     * Shuffelt die Playlist. und mischt sie im zweifel neu
     * @param on true = shuffle aktiviert, false = shuffle deaktiviert
     */
    public void shuffle(boolean on) {


            shuffling = on;
            merge();


    }


    /**
     * getTracks gibt die Playlist zurueck
     * @return Es wird die Warteschlange zurueckgegeben.
     */
    public ArrayList<Track> getTracks(){return queue;}

    /**
     * Es wird ein Track an die Warteschleife gehalten
     * @param t wird an die playlist gehaengt
     */
    public void addTrack(Track t) {
        queue.add(t);
    }

    /**
     * Es wird ein Song als naechtes eingehaengt. Nach dem aktuellen Song wuerde dieser Song sepielt werden.
     * @param t Der Track welcher als naechstes gespielt werden soll.
     */
    public void addnextTrack(Track t) {
        queue.add(null);
        for (int i =queue.size()-1 ; i > index+1; i--){
        queue.set(i,queue.get(i-1));
        }
        queue.set(index+1,t);
    }


    /**
     * loadPlaylist
     *
     * @param path Pfad der Playlist, welcher geladen werden soll.
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

       merge();
        currentTrack = queue.get(0);

    }
}

