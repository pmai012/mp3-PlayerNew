package Model;


import com.mpatric.mp3agic.*;
import javafx.beans.value.ChangeListener;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Pascal on 29.10.2017.
 */


/*
 Radio : SWR3 http://swr-swr3-live.cast.addradio.de/swr/swr3/live/mp3/128/stream.mp3
 */
public class Track {
    boolean radio = false;
    private String title;
    private String artist = "unbekannter Künstler";
    private String album = "unbekanntes Album";
    private String genre = "unbekanntes Genre";
    private String comment = "";
    private String path = null;
    private String year = "unbekanntes Jahr";
    private long length = 0;
    private long currenttime = 0;
    private boolean internetpfad = false;
    byte[] cover = null;

    /**
     * Gibt zurueck ob es sich um einen Internetpfad handelt oder nicht
     * @return
     */
    public boolean isInternetpfad() {
        return internetpfad;
    }



    public long getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(long currenttime) {
        this.currenttime = currenttime;
    }









    /**
     * Get Title
     * @return gibt den Titel des Tracks als String zurueck
     */
    public String getTitle() {
        return title;
    }

    /**
     * get Artist
     * @return gibt den komponisten des Tracks als String  zurueck.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Get Album
     * @return Gibt den Namen des Albums als String zurueck
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Get Genre
     * @return Gibt das Genre des Tracks als String zurueck
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Get Comment
     * @return Gibt den kommentar von dem Stück als String zurueck.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Get Path
     * @return Gibt den Pfad des Tracks an
     */
    public String getPath() {
        return path;
    }

    /**
     * Get Year
     * @return Gibt das Entstehungsjahr des Tracks als String zurueck.
     */
    public String getYear() {
        return year;
    }

    /**
     * Get Cover
     * @return Gibt das Coverbild als ByteArray zurueck.
     */
    public byte[] getCover() {
        return cover;
    }









    /**
     *Konstruktor der Track Klasse
     * Erkennt zudem ob es sich um einen Internetpfad handelt
     * @param path Der Pfad zur Mp3 Datei. Kann auch Internetadresse sein
     */
    public Track(String path) {

        if (path.startsWith("www.") ){
            this.path = path;
            internetpfad = true;
        }else {
            internetpfad = false;
            setTrack(path);

        }
    }


    /**
     * Set Track
     * Dabei werden die Id3 Tags des Tracks ausgelesen und in den Parametern der Klasse gespeichert
     * @param path Der Pfad des Tracks muss mitgegeben werden
     */
    public void setTrack(String path) {
        Mp3File mp3file = null;
        try {
            mp3file = new Mp3File(path);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
            return;
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return;
        }



        this.path = path;

        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3tags = mp3file.getId3v2Tag();

            if (id3tags != null) {
                if (id3tags.getTitle() != null) {
                    title = id3tags.getTitle();
                } else {

                    int index = path.lastIndexOf("\\");
                    title = path.substring(index + 1);

                }

                if (id3tags.getArtist() != null) {
                    artist = id3tags.getArtist();
                }

                if (id3tags.getAlbum() != null) {
                    album = id3tags.getAlbum();
                }

                if (id3tags.getGenreDescription() != null) {
                    genre = id3tags.getGenreDescription();
                }

                if (id3tags.getComment() != null) {
                    comment = id3tags.getComment();
                }
                if (id3tags.getYear() != null) {
                    year = id3tags.getYear();
                }
                if (id3tags.getAlbumImage() != null) {
                    cover = id3tags.getAlbumImage();
                }
                length = mp3file.getLengthInMilliseconds();


            }
        }
            if (mp3file.hasId3v1Tag()) {
                ID3v1 id3v1tags = mp3file.getId3v1Tag();
                if (id3v1tags != null) {
                    if (id3v1tags.getTitle() != null) {
                        title = id3v1tags.getTitle();
                    } else {

                        int index = path.lastIndexOf("\\");
                        title = path.substring(index + 1);

                    }

                    if (id3v1tags.getArtist() != null) {
                        artist = id3v1tags.getArtist();
                    }

                    if (id3v1tags.getAlbum() != null) {
                        album = id3v1tags.getAlbum();
                    }

                    if (id3v1tags.getGenreDescription() != null) {
                        genre = id3v1tags.getGenreDescription();
                    }

                    if (id3v1tags.getComment() != null) {
                        comment = id3v1tags.getComment();
                    }
                    if (id3v1tags.getYear() != null) {
                        year = id3v1tags.getYear();
                    }
                    length = mp3file.getLengthInMilliseconds();
                }
        }
    }
}
