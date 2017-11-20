package Model;


import com.mpatric.mp3agic.*;

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

    public long getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(long currenttime) {
        this.currenttime = currenttime;
    }

    private Mp3File mp3file = null;
    byte[] cover = null;

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public String getComment() {
        return comment;
    }

    public String getPath() {
        return path;
    }

    public String getYear() {
        return year;
    }

    public byte[] getCover() {
        return cover;
    }



    public Track(String path) {


        setTrack(path);
    }



    public void setTrack(String path) {
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

        if (mp3file == null) { // überflüssig?
            return;

        }
        if (mp3file.hasId3v1Tag()) {
//          ID3v2 id3tags = mp3file.getId3v2Tag();
            ID3v1 id3tags = mp3file.getId3v1Tag();
            this.path = path;

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
//            if (id3tags.getAlbumImage() != null) {
//                cover = id3tags.getAlbumImage();
//            }
                length = mp3file.getLengthInMilliseconds();


            }
        }


    }

}
