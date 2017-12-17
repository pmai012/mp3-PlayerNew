package sample;

import Controller.HandleCollection;
import Model.MP3Player;
import Model.Playlist;
import Model.PlaylistManager;
import Model.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

import java.util.Observable;
import java.util.Observer;

public class PlaylistView extends HBox implements Observer {

    private Button btn_addPlaylist = new Button();
    private ToggleGroup changeViewGroup = new ToggleGroup();
    private ToggleButton playlistButton = new ToggleButton("Playlist");
    private ToggleButton songsButton = new ToggleButton("Songs");
    private PlaylistManager playlistManager = new PlaylistManager();
    private HandleCollection handleCollection;
    private ListView songView = new ListView();
    private ListView playlistView = new ListView();
    private Playlist allSongs;
    private Playlist activePlaylist;
    private VBox vbox = new VBox();
    private HBox hbox = new HBox();

    public void handleCollectionreferenz(HandleCollection ref){
        this.handleCollection = ref;
    }

    public PlaylistView(){

        btn_addPlaylist.getStyleClass().addAll("buttons", "buttonAddPlaylist");
        songsButton.setToggleGroup(changeViewGroup);
        songsButton.setSelected(true);
        playlistButton.setToggleGroup(changeViewGroup);
        songsButton.getStyleClass().add("toggle-button");
        playlistButton.getStyleClass().add("toggle-button");
        changeViewGroup.getToggles().forEach(x -> getStyleClass().add("toggle-button"));

        searchPlaylists();

                try {
                    allSongs = playlistManager.getAllTracks();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        hbox.getChildren().addAll(songsButton, playlistButton);
        vbox.getChildren().add(hbox);
        getChildren().add(vbox);
        vbox.getChildren().clear();
        vbox.getChildren().add(hbox);

        songsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            vbox.getChildren().remove(playlistView);
            hbox.getChildren().remove(btn_addPlaylist);

            if (songView.getParent() == null) {
                vbox.getChildren().add(songView);
            }

            ObservableList<String> songs = FXCollections.observableArrayList();
            handleCollection.getPlayer().setPlaylist(allSongs);

            for (int i = 0; i < handleCollection.getPlayer().getPlaylist().getTracks().size(); i++){

                songs.add(handleCollection.getPlayer().getPlaylist().getTrack(i).getTitle());

            }

            songView.setItems(songs);
            }
        });
        playlistButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            vbox.getChildren().clear();
            vbox.getChildren().add(hbox);

            playlistManager.getPlaylists().clear();
            searchPlaylists();
            if (btn_addPlaylist.getParent() == null) {
                hbox.getChildren().add(btn_addPlaylist);
            }
            ObservableList<String> playlists = FXCollections.observableArrayList();
            for (Playlist p : playlistManager.getPlaylists()) {
                playlists.add(p.getName());
            }
            playlistView.setItems(playlists);
            vbox.getChildren().add(playlistView);
            }
        });

        /**
         * Song aus "Songs" abspielen
         */
        songView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object itemClicked = songView.getSelectionModel().getSelectedItem();
                MP3Player player = handleCollection.getPlayer();
                for (Track t : player.getPlaylist().getTracks()) {
                    if (itemClicked.equals(t.getTitle())) {
                        player.setCurrentNumber(songView.getSelectionModel().getSelectedIndex());
                        handleCollection.play();
                    }
                }
            }
        });
        songView.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                handleCollection.songViewMouseDragOver(event);
            }
        });
        songView.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                handleCollection.songViewMouseDragDropped(event, songView.getItems(), activePlaylist);
            }
        });

        btn_addPlaylist.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleCollection.addPlaylist();
                vbox.getChildren().clear();
                vbox.getChildren().add(hbox);

                if (btn_addPlaylist.getParent() == null) {
                    hbox.getChildren().add(btn_addPlaylist);
                }
                ObservableList<String> playlists = FXCollections.observableArrayList();
                for (Playlist p : playlistManager.getPlaylists()) {
                    playlists.add(p.getName());
                }
                playlistView.setItems(playlists);
                vbox.getChildren().add(playlistView);
            }
        });

        /**
         * Playlist aussuchen und abspielen
         */
        playlistView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object itemClicked = playlistView.getSelectionModel().getSelectedItem();
                ObservableList<String> songs = FXCollections.observableArrayList();
                for (Playlist p : playlistManager.getPlaylists()){
                    //Playlist auswählen
                    if (itemClicked.equals(p.getName())) {
                        handleCollection.getPlayer().getPlaylist().clear();
                        Playlist loader = new Playlist();
                        loader.loadPlaylist(p.getPath());
                        handleCollection.getPlayer().setPlaylist(loader);
                        activePlaylist = p;

                        for (Track t : handleCollection.getPlayer().getPlaylist().getTracks()) {
                            songs.add(t.getTitle());
                            activePlaylist.getTracks().add(t);
                        }
                        songView.setItems(songs);
                        vbox.getChildren().remove(playlistView);
                        vbox.getChildren().add(songView);
                    }
                }
            }
        });
    }

    private void searchPlaylists() {
        playlistManager.searchPlaylists(System.getProperty("user.home").concat("//Music"));
    }

    @Override
    public void update(Observable o, Object arg) {

       songView.getSelectionModel().select(handleCollection.getPlayer().getPlaylist().getIndex());

         if (handleCollection.getPlayer().isShuffle() == true) {

           songView.scrollTo(handleCollection.getPlayer().getPlaylist().getIndex());
       }
    }
}
