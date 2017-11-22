package sample;

import Controller.Controller;
import Model.MP3Player;
import Model.Playlist;
import Model.PlaylistManager;
import Model.Track;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
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


/**
 * Created by User on 06.11.2017.
 */
public class Frameapplication extends Application {
    final int WIDTH = 1200;
    final int HEIGHT = 620;
    private MP3Player mp3Player = new MP3Player();

    private boolean playing = false;

    PlaylistManager playlistManager = new PlaylistManager();

    Controller controller = new Controller();
    //GUI KOMPONENTE

    Image playicon = new Image("picture/play.png");
    Image pauseicon = new Image("picture/pause.png");
    Image previcon = new Image("picture/prev.png");
    Image nexticon = new Image("picture/next.png");
    Image stopicon = new Image("picture/stop.png");



    ImageView play = new ImageView(playicon);
    ImageView next = new ImageView(nexticon);
    ImageView prev =  new ImageView(previcon);
    Button btn_sideView_back = new Button("<");


    Label title = new Label("Title");
    //Label supertitle = new Label(" MEINE MUSIK | STORE | RADIO | MEHR |");
    Label artist = new Label("Artist");
    Label album = new Label("Album");
    Slider volume = new Slider();
    ObservableList<String> sideViewItems = FXCollections.observableArrayList ("Songs", "Playlists");
    ListView sideView = new ListView();
    ListView songView = new ListView();
    ListView playlistView = new ListView();



    public void init() {
    play.setScaleX(0.3);
    play.setScaleY(0.3);
    prev.setScaleX(0.1);
    prev.setScaleY(0.1);
    next.setScaleX(0.1);
    next.setScaleY(0.1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {
        playlistManager.searchPlaylists(System.getProperty("user.home").concat("//Music"));
        Playlist allSongs = playlistManager.getAllTracks();

        //Fenstereinstellungen

        HBox leftpane = new HBox();
        HBox rightpane = new HBox();
       // HBox toprightpane = new HBox();
        TilePane toppane = new TilePane();
        FlowPane bottompane = new FlowPane();
        StackPane centerpane = new StackPane();

        BorderPane root = new BorderPane();
        root.setTop(toppane);
        root.setBottom(bottompane);
        root.setLeft(leftpane);
        root.setRight(rightpane);
        root.setCenter(centerpane);


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(200);
        primaryStage.setTitle("3Player");

        root.setPadding(new Insets(20,0,0,0));
        root.setStyle("-fx-background-color: gold;");



        play.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (mp3Player.isPlaying() == false){
                   play.setImage(pauseicon);
                   title.setText(mp3Player.getcurrentTrack().getTitle());
                    album.setText(mp3Player.getcurrentTrack().getAlbum());
                    artist.setText(mp3Player.getcurrentTrack().getArtist());
                }else{
                    play.setImage(playicon);
                }

            }
        });


        sideView.setItems(sideViewItems);
        sideView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object itemClicked = sideView.getSelectionModel().getSelectedItem();
                if (itemClicked.equals("Songs"))
                {
                    leftpane.getChildren().remove(sideView);
                    leftpane.getChildren().add(btn_sideView_back);
                    ObservableList<String> songs = FXCollections.observableArrayList();
                    for (Track t: allSongs.getTracks())
                    {
                     songs.add(t.getTitle());
                    }
                    songView.setItems(songs);
                    leftpane.getChildren().add(songView);
                }
                if (itemClicked.equals("Playlists"))
                {
                    leftpane.getChildren().remove(sideView);
                    leftpane.getChildren().add(btn_sideView_back);
                    ObservableList<String> playlists = FXCollections.observableArrayList();
                    for (Playlist p: playlistManager.getPlaylists())
                    {
                        playlists.add(p.getName());
                    }
                    playlistView.setItems(playlists);
                    leftpane.getChildren().add(playlistView);
                }
            }
        });
        songView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object itemClicked = songView.getSelectionModel().getSelectedItem();
                for (Track t: allSongs.getTracks())
                {
                    if (itemClicked.equals(t.getTitle()))
                    {
                        mp3Player.play(t);
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











       // play.setOnAction(this);
      //  next.setOnAction(this);
      //  prev.setOnAction(this);





        //Konfiguration von bottom Pane
        bottompane.setPadding(new Insets(5));
        bottompane.setAlignment(Pos.TOP_CENTER);
        bottompane.setHgap(10);
        bottompane.setStyle("-fx-background-color: black}{");



        bottompane.getChildren().add(prev);
        bottompane.getChildren().add(play);
        bottompane.getChildren().add(next);


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










        centerpane.setStyle("-fx-background-color: gold;");

        primaryStage.show();
    }

    public void stop() {
    }









}

