package Controller;

import Model.Playlist;
import Model.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class PlaylistView extends HBox{
    Button btn_sideView_back = new Button("<");
    PlaylistManager playlistManager = new PlaylistManager();
    HandleCollection handleCollection;
    ObservableList<String> sideViewItems = FXCollections.observableArrayList("Songs", "Playlists");
    ListView sideView = new ListView();
    ListView songView = new ListView();
    ListView playlistView = new ListView();
    Playlist allSongs;

    public PlaylistView(){
        btn_sideView_back.getStyleClass().addAll("buttons","text");
        playlistManager.searchPlaylists(System.getProperty("user.home").concat("//Music"));
        try {
            allSongs = playlistManager.getAllTracks();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }



}