package sample;

import Controller.HandleCollection;
import Model.MP3Player;
import Model.Playlist;
import Model.PlaylistManager;
import Model.Track;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

//import java.io.ByteArrayInputStream;


/**
 * Created by User on 06.11.2017.
 */
public class GUI extends Application implements Observer {

    HandleCollection handleCollection;


    final int WIDTH = 1200;
    final int HEIGHT = 840;
    private MP3Player mp3Player = new MP3Player();

    private boolean playing = false;

    PlaylistManager playlistManager = new PlaylistManager();


    //GUI KOMPONENTE


    Image cover = new Image("picture/placeholderCover.png");

    ImageView albumcover = new ImageView(cover);


    Button play = new Button("");
    Button next = new Button("");
    Button prev = new Button("");
    Button random = new Button("");
    Button repeat = new Button("");

    Button btn_sideView_back = new Button("<");


    Label title = new Label("Title");
    Label artist = new Label("Artist");
    Label album = new Label("Album");
    Slider volume = new Slider();
    ObservableList<String> sideViewItems = FXCollections.observableArrayList("Songs", "Playlists");
    ListView sideView = new ListView();
    ListView songView = new ListView();
    ListView playlistView = new ListView();

    private MouseEvent mouseEvent;


    public void init() {


        play.setScaleX(0.3);
        play.setScaleY(0.3);
        prev.setScaleX(0.2);
        prev.setScaleY(0.2);
        next.setScaleX(0.2);
        next.setScaleY(0.2);
        random.setScaleX(0.2);
        random.setScaleY(0.2);
        repeat.setScaleX(0.2);
        repeat.setScaleY(0.2);


        handleCollection = new HandleCollection();
        handleCollection.addObserver(this);
        handleCollection.getPlayer().addObserver(this);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        playlistManager.searchPlaylists(System.getProperty("user.home").concat("//Music"));
        Playlist allSongs = playlistManager.getAllTracks();


        //Fenstereinstellungen

        HBox leftpane = new HBox();
        HBox rightpane = new HBox();
        // HBox toprightpane = new HBox();
        TilePane toppane = new TilePane();
        FlowPane bottompane = new FlowPane(); //VBox ?
        StackPane centerpane = new StackPane();

       centerpane.getChildren().add(albumcover);

        BorderPane root = new BorderPane();
        root.setTop(toppane);
        root.setBottom(bottompane);
        root.setLeft(leftpane);
        root.setRight(rightpane);
        root.setCenter(centerpane);


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/MP3GUI.css");
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(200);
        primaryStage.setTitle("3Player");

        root.setStyle("-fx-background-color: #151515");
        root.getStyleClass().addAll("root");
        root.setPadding(new Insets(30, 0, 0, 0));


        play.getStyleClass().addAll("buttons", "buttonPlay");
        next.getStyleClass().addAll("buttons", "buttonNext");
        prev.getStyleClass().addAll("buttons", "buttonPrev");
        random.getStyleClass().addAll("buttons", "buttonRandom");
        repeat.getStyleClass().addAll("buttons", "buttonRepeat");
        btn_sideView_back.getStyleClass().addAll("buttons","text");

        title.getStyleClass().addAll("text");
        artist.getStyleClass().addAll("text");
        album.getStyleClass().addAll("text");

        centerpane.getStyleClass().addAll("cover");

        //HANDLECOLLECTIONS

        play.setOnAction(handleCollection.play);
        next.setOnAction(handleCollection.next);
        prev.setOnAction(handleCollection.back);
        random.setOnAction(handleCollection.shuffle);
        volume.valueProperty().addListener(handleCollection.volume);
        volume.setValue(0.5);



        sideView.setItems(sideViewItems);
        sideView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object itemClicked = sideView.getSelectionModel().getSelectedItem();
                if (itemClicked.equals("Songs")) {
                    leftpane.getChildren().remove(sideView);
                    leftpane.getChildren().add(btn_sideView_back);
                    ObservableList<String> songs = FXCollections.observableArrayList();
                    MP3Player player = handleCollection.getPlayer();

                    player.setPlaylist(allSongs);
                    for (Track t : allSongs.getTracks()) {
                        songs.add(t.getTitle());
                    }
                    songView.setItems(songs);
                    leftpane.getChildren().add(songView);
                }
                if (itemClicked.equals("Playlists")) {
                    leftpane.getChildren().remove(sideView);
                    leftpane.getChildren().add(btn_sideView_back);
                    ObservableList<String> playlists = FXCollections.observableArrayList();
                    for (Playlist p : playlistManager.getPlaylists()) {
                        playlists.add(p.getName());
                    }
                    playlistView.setItems(playlists);
                    leftpane.getChildren().add(playlistView);
                }
            }
        });

        /**
         * Song aus Songs abspielen
         */
        songView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object itemClicked = songView.getSelectionModel().getSelectedItem();
                MP3Player player = handleCollection.getPlayer();
                for (Track t : player.getPlaylist().getTracks()) {
                    if (itemClicked.equals(t.getTitle())) {
                        player.setCurrentNumber(songView.getSelectionModel().getSelectedIndex());
                       System.out.println(songView.getSelectionModel().getSelectedIndex());
                         handleCollection.play();


                    }
                }
            }
        });


        btn_sideView_back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                leftpane.getChildren().clear();
                leftpane.getChildren().add(sideView);
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
                for (Playlist p : playlistManager.getPlaylists()) {
                    if (itemClicked.equals(p.getName())) {
                        p.loadPlaylist(p.getPath());
                        handleCollection.getPlayer().clear();
                        handleCollection.getPlayer().loadPlaylist(p.getPath());


                        for (Track t : handleCollection.getPlayer().getPlaylist().getTracks()) {
                            songs.add(t.getTitle());

                        }
                        handleCollection.getPlayer().getPlaylist().getTracks().clear();
                       // p.getTracks().clear();
                        songView.setItems(songs);
                        leftpane.getChildren().remove(playlistView);
                        leftpane.getChildren().add(songView);
//                        leftpane.getChildren().add(btn_sideView_back);
                    }
                }
            }
        });


        // play.setOnAction(this);
        //  next.setOnAction(this);
        //  prev.setOnAction(this);


        //Konfiguration von bottom Pane


        bottompane.getChildren().add(random);
        bottompane.getChildren().add(prev);
        bottompane.getChildren().add(play);
        bottompane.getChildren().add(next);
        bottompane.getChildren().add(repeat);

        bottompane.setPadding(new Insets(10));
        bottompane.setAlignment(Pos.TOP_CENTER);

        bottompane.setStyle("-fx-background-color: black}{");

        //Konfiguration von centerpane

        //display = new ImageView("Album.png");
        //centerpane.getChildren().add(display);


        //Konfiguration von toppane

        //Konfiguration von bottom Pane
        //toppane.setPadding(new Insets(6));
        toppane.setAlignment(Pos.TOP_CENTER);
        toppane.setOrientation(Orientation.VERTICAL);
        //toppane.setOpacity(0);

        toppane.getChildren().add(title);
        toppane.getChildren().add(artist);
        toppane.getChildren().add(album);

        /*
        supertitle.setFont(new Font(50));
        supertitle.setUnderline(true);
        centerpane.getChildren().add(supertitle);
        */

        leftpane.getChildren().add(sideView);
        bottompane.getChildren().add(volume);

        /*
         albumcover.setScaleX(0.5);
         albumcover.setScaleY(0.5);
         */
         root.setCenter(albumcover);




        centerpane.setStyle("-fx-background-color: gold;");

        primaryStage.show();
    }

    public void stop() {
        System.exit(1);
    }


    @Override
    public void update(Observable o, Object arg) {
        String von = (String) arg;


        //Meldungen vom  Collection handler



        if (von.equals("player")){
            title.setText(handleCollection.getPlayer().getTitle());
            album.setText(handleCollection.getPlayer().getAlbum());
            artist.setText(handleCollection.getPlayer().getArtist());
            albumcover.setImage(handleCollection.getCover());
            songView.getSelectionModel().select(handleCollection.getPlayer().getPlaylist().getIndex());
        }


        if (von.equals("merge")){

        }
        handleCollection.currentupdater();
        play.getStyleClass().addAll("buttons", handleCollection.getCurrentplay());

    }
}

