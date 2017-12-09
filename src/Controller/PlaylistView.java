package Controller;

import Model.MP3Player;
import Model.Playlist;
import Model.PlaylistManager;
import Model.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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
        handleCollection =  new HandleCollection();
        btn_sideView_back.getStyleClass().addAll("buttons","text");
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
                    System.out.println("ERSTER SONG: "+ allSongs.getTrack(0).getTitle());

                    for (Track t : handleCollection.getPlayer().getPlaylist().getTracks()) {
                        songs.add(t.getTitle());

                    }

                    songView.setItems(songs);
                    getChildren().add(songView);
                }
                //Playlisten werden aufgelistet
                if (itemClicked.equals("Playlists")) {
                    getChildren().remove(sideView);
                    getChildren().add(btn_sideView_back);
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


        btn_sideView_back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleCollection.getPlayer().clear();
                getChildren().clear();
                getChildren().add(sideView);
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
                        handleCollection.getPlayer().loadPlaylist(p.getPath());


                        for (Track t : handleCollection.getPlayer().getPlaylist().getTracks()) {
                            songs.add(t.getTitle());
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



}