package Controller;

import Model.Playlist;
import Model.PlaylistManager;
import Model.Track;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.IOException;

/**
 * Created by User on 08.12.2017.
 */
public class TreeTracks extends TreeView<String> {
    PlaylistManager playlistManager = new PlaylistManager();
    Playlist allSongs;
    TreeItem<String> songs = new TreeItem<>("Songs");
    TreeItem<String> playlist = new TreeItem<>("Playlist");


    public TreeTracks() {


        TreeItem<String> root = new TreeItem<>();
        root.getChildren().add(songs);
        root.getChildren().add(playlist);
        root.setExpanded(true);
        super.setRoot(root);


        playlistManager.searchPlaylists(System.getProperty("user.home").concat("//Music"));
        try {
            allSongs = playlistManager.getAllTracks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        songs.setExpanded(true);
        playlist.setExpanded(true);
        for (Track t : allSongs.getTracks()) {

            TreeItem<String> track = new TreeItem<String> (t.getTitle());
            songs.getChildren().add(track);

        }



    }
}
