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
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

import java.util.Observable;
import java.util.Observer;

public class PlaylistView extends  HBox implements Observer {
    Button btn_sideView_back = new Button("<");
    Button btn_addPlaylist = new Button();
    PlaylistManager playlistManager = new PlaylistManager();
    HandleCollection handleCollection;
    ObservableList<String> sideViewItems = FXCollections.observableArrayList("Songs", "Playlists");
    ListView sideView = new ListView();
    ListView songView = new ListView();
    ListView playlistView = new ListView();
    Playlist allSongs;
    Playlist activePlaylist;

    public void handleCollectionreferenz(HandleCollection ref){
        this.handleCollection = ref;
    }

    public PlaylistView(){

        btn_sideView_back.getStyleClass().addAll("buttons","text");
        btn_addPlaylist.getStyleClass().addAll("buttons", "buttonAddPlaylist");
        playlistManager.searchPlaylists(System.getProperty("user.home").concat("//Music"));
        try {
            allSongs = playlistManager.getAllTracks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn_sideView_back.getStyleClass().addAll("buttons","text");

        sideView.setItems(sideViewItems);
        sideView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Object itemClicked = sideView.getSelectionModel().getSelectedItem();
                if (itemClicked.equals("Songs")) {
                    getChildren().remove(sideView);
                    getChildren().add(btn_sideView_back);
                    ObservableList<String> songs = FXCollections.observableArrayList();
                    handleCollection.getPlayer().setPlaylist(allSongs);
                   // songs.clear();
                    //Er läd den Titel nicht!!!
                    for (int i = 0; i < handleCollection.getPlayer().getPlaylist().getTracks().size(); i++){

                        songs.add(handleCollection.getPlayer().getPlaylist().getTrack(i).getTitle());
                        System.out.println(handleCollection.getPlayer().getPlaylist().getTrack(i).getTitle());
                    }


                    songView.setItems(songs);

                    getChildren().add(songView);

                }
                //Playlisten werden aufgelistet
                if (itemClicked.equals("Playlists")) {
                    getChildren().remove(sideView);
                    getChildren().add(btn_sideView_back);
                    getChildren().add(btn_addPlaylist);
                    ObservableList<String> playlists = FXCollections.observableArrayList();
                    for (Playlist p : playlistManager.getPlaylists()) {
                        playlists.add(p.getName());
                    }
                    playlistView.setItems(playlists);
                    getChildren().add(playlistView);
                }
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


        btn_sideView_back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleCollection.getPlayer().clear();
                getChildren().clear();
                getChildren().add(sideView);
            }
        });
        btn_addPlaylist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleCollection.addPlaylist();
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
                      //  handleCollection.getPlayer().loadPlaylist(p.getPath());
                        Playlist loader = new Playlist();
                        loader.loadPlaylist(p.getPath());
                        handleCollection.getPlayer().setPlaylist(loader);
                        activePlaylist = p;

                        for (Track t : handleCollection.getPlayer().getPlaylist().getTracks()) {
                            songs.add(t.getTitle());
                            activePlaylist.getTracks().add(t);
                            System.out.println("-"+ t.getTitle());

                        }

                        // p.getTracks().clear();
                        songView.setItems(songs);
                        getChildren().remove(playlistView);
                        getChildren().add(songView);
//                        leftpane.getChildren().add(btn_sideView_back); //GIBT FEHLERMELDUNG BITTE ÄNDERN!
                    }
                }
            }
        });



        getChildren().add(sideView);


    }


    @Override
    public void update(Observable o, Object arg) {
        songView.getSelectionModel().select(handleCollection.getPlayer().getPlaylist().getIndex());
    }
}
/*
Problem liegt beim Laden der Anzeige! Falscher Titel wird angezeigt!
 */