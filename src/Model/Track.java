package Model;


import com.mpatric.mp3agic.*;
 import java.io.IOException;

/**
     * Created by Pascal on 29.10.2017.
     */

    public class Track {

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

    private String title = null;
        private String artist = null;
        private String album = null;
        private String genre = null;
        private String comment = null;
        private String path = null;
        private String  year = null;
        private long lenght = 0;
        private Mp3File mp3file = null;
        byte[] cover = null;

    public byte[] getCover() {
        return cover;
    }

    public Track(String path) {
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
        if (mp3file == null){
             return;

        }
        if (mp3file.hasId3v1Tag()) {
            ID3v2 id3tags= mp3file.getId3v2Tag();
            this.path = path;
            title = id3tags.getTitle();
            artist = id3tags.getArtist();
            album = id3tags.getAlbum();
            genre = id3tags.getGenreDescription();
            comment = id3tags.getComment();
            year = id3tags.getYear();
            lenght = mp3file.getLengthInMilliseconds();
            cover = id3tags.getAlbumImage();



    }







    }

        }
