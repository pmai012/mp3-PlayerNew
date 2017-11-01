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
        private Mp3File mp3file = null;

    public Track(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        mp3file = new Mp3File(path);
        if (mp3file.hasId3v1Tag()) {
            ID3v1 id3tags = mp3file.getId3v1Tag();
            title = id3tags.getTitle();
            artist = id3tags.getArtist();
            album = id3tags.getAlbum();
            genre = id3tags.getGenreDescription();
            comment = id3tags.getComment();
            year = id3tags.getYear();


        }

    }

        }
