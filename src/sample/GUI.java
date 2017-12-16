package sample;

import Controller.HandleCollection;
import Model.MP3Player;
import Model.Playlist;
import Model.PlaylistManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

//import java.io.ByteArrayInputStream;


/**
 * Created by User on 06.11.2017.
 */
public class GUI extends Application implements Observer {

    HandleCollection handleCollection;


    final int WIDTH = 940;
    final int HEIGHT = 720;
    private MP3Player mp3Player = new MP3Player();

    private boolean playing = false;

    PlaylistManager playlistManager = new PlaylistManager();
    Thread timeslider = new Thread(new Timeslider());

    //GUI KOMPONENTE

    VBox leftpane;
    VBox toprightpane;
    TilePane toppane;
    VBox bottompane;
    HBox controlpane;
    HBox pogresspane;
    StackPane centerpane;
    BorderPane root;


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
    Slider timeline = new Slider();

    ObservableList<String> sideViewItems = FXCollections.observableArrayList("Songs", "Playlists");
    ListView sideView = new ListView();
    ListView songView = new ListView();
    Playlist allSongs;
    PlaylistView playlistview = new PlaylistView();


    private MouseEvent mouseEvent;


    public void init() {

        handleCollection = new HandleCollection();
        handleCollection.addObserver(this);
        handleCollection.getPlayer().addObserver(this);



    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        playlistManager.searchPlaylists(System.getProperty("user.home").concat("//Music"));
        allSongs = playlistManager.getAllTracks();

        //Fenstereinstellungen

        leftpane = new VBox();
        toprightpane = new VBox(10);
        toppane = new TilePane();
        bottompane = new VBox(5);
        controlpane = new HBox(80);
        pogresspane = new HBox();
        centerpane = new StackPane();

        playlistview = new PlaylistView();
        playlistview.handleCollectionreferenz(handleCollection);
        handleCollection.addObserver(playlistview);
        handleCollection.getPlayer().addObserver(playlistview);

        centerpane.getChildren().add(albumcover);
        albumcover.setFitWidth(400);
        albumcover.setFitHeight(400);

        root = new BorderPane();

        root.setTop(toppane);
        root.setBottom(bottompane);
        root.setLeft(playlistview);


        //root.setRight(versuch);
    //root.setCenter(centerpane);


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/MP3GUI.css");
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(800);
        primaryStage.setTitle("3Player");

        root.setStyle("-fx-background-color: " + "#151515");
        root.getStyleClass().addAll("root");
        root.setPadding(new Insets(30, 0, 30, 0));

        centerpane.getStyleClass().addAll("cover");
        controlpane.getStyleClass().addAll("controlpane");
        toppane.getStyleClass().addAll("toppane");
        bottompane.getStyleClass().addAll("bottompane");

        play.getStyleClass().addAll("buttons", "buttonPlay");
        next.getStyleClass().addAll("buttons", "buttonNext");
        prev.getStyleClass().addAll("buttons", "buttonPrev");
        random.getStyleClass().addAll("buttons", "buttonRandom");
        repeat.getStyleClass().addAll("buttons", "buttonRepeat");

        title.getStyleClass().addAll("text");
        artist.getStyleClass().addAll("text");
        album.getStyleClass().addAll("text");

        timeline.getStyleClass().addAll("sliderTl");

        //HANDLECOLLECTIONS

        play.setOnAction(handleCollection.play);
        next.setOnAction(handleCollection.next);
        prev.setOnAction(handleCollection.back);
        random.setOnAction(handleCollection.shuffle);
        repeat.setOnAction(handleCollection.repeat);
        volume.valueProperty().addListener(handleCollection.volume);
        volume.setValue(50);
        timeline.valueProperty().addListener(handleCollection.position);

        //timeline.setMax(1000);
        //timeline.setBlockIncrement(1000);

        // play.setOnAction(this);
        //  next.setOnAction(this);
        //  prev.setOnAction(this);


        //Konfiguration von bottom Pane

        bottompane.getChildren().add(pogresspane);
        bottompane.getChildren().add(controlpane);

        pogresspane.setAlignment(Pos.CENTER);
        pogresspane.getChildren().add(timeline);

        controlpane.getChildren().add(random);
        controlpane.getChildren().add(prev);
        controlpane.getChildren().add(play);
        controlpane.getChildren().add(next);
        controlpane.getChildren().add(repeat);
        controlpane.getChildren().add(volume);
        //controlpane.setPadding(new Insets(0, 0, 20, 0));
        controlpane.setAlignment(Pos.CENTER);

        //Konfiguration von centerpane

        //Konfiguration von toppane

        //Konfiguration von bottom Pane
        toppane.setPadding(new Insets(6));
        toppane.setAlignment(Pos.CENTER);
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

         root.setCenter(albumcover);




        timeslider.start();

        primaryStage.show();

    }

    public void stop() {
        System.exit(1);
    }


    @Override
    public void update(Observable o, Object arg) {
        String von ="";
        if (arg != null){
            von = (String) arg;
        }


        if (von.equals("player")){
            title.setText(handleCollection.getPlayer().getTitle());
            album.setText(handleCollection.getPlayer().getAlbum());
            artist.setText(handleCollection.getPlayer().getArtist());
            albumcover.setImage(handleCollection.getCover());
            if(handleCollection.getPlayer().getcurrentTrack().getCover() == null){
                root.setStyle("-fx-background-color: " + "#151515");
            }else {
                root.setStyle("-fx-background-color: "
                        + handleCollection.getPixel(handleCollection.getPlayer().getcurrentTrack().getCover(), 200, 50));
            }
        }



        if (handleCollection.getPlayer().isShuffle()) {
            random.getStyleClass().addAll("buttons", "buttonRandomOnPress");
        }

        if(handleCollection.getPlayer().isShuffle() != true){

                random.getStyleClass().setAll("buttons", "buttonRandom");
            }

        handleCollection.currentupdater();
        play.getStyleClass().addAll("buttons", handleCollection.getCurrentplay());


    }

    private class Timeslider implements Runnable{

            @Override public void run() {
                System.out.println("run auf Gui gestartet");
                while (true) {

                    try {

                        Thread.sleep(500);
                        if (handleCollection.getPlayer().getcurrentTrack() != null) {

                            timeline.setValue(handleCollection.getPlayer().position()*100);


                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
    }

}

