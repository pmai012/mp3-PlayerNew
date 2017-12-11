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

        VBox leftpane = new VBox();

        VBox toprightpane = new VBox(10);
        TilePane toppane = new TilePane();
        HBox bottompane = new HBox(80); //HBox ?
        StackPane centerpane = new StackPane();

        playlistview = new PlaylistView();
        playlistview.handleCollectionreferenz(handleCollection);
        handleCollection.addObserver(playlistview);

        centerpane.getChildren().add(albumcover);
        albumcover.setFitWidth(400);
        albumcover.setFitHeight(400);

        BorderPane root = new BorderPane();
        root.setTop(toppane);
        root.setBottom(bottompane);
        root.setLeft(playlistview);


        //root.setRight(versuch);
    //    root.setCenter(centerpane);


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("CSS/MP3GUI.css");
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(200);
        primaryStage.setTitle("3Player");

        root.setStyle("-fx-background-color: #151515");
        root.getStyleClass().addAll("root");
        root.setPadding(new Insets(30, 0, 30, 0));

        centerpane.getStyleClass().addAll("cover");
        bottompane.getStyleClass().addAll("bottompane");
        toppane.getStyleClass().addAll("toppane");

        play.getStyleClass().addAll("buttons", "buttonPlay");
        next.getStyleClass().addAll("buttons", "buttonNext");
        prev.getStyleClass().addAll("buttons", "buttonPrev");
        random.getStyleClass().addAll("buttons", "buttonRandom");
        repeat.getStyleClass().addAll("buttons", "buttonRepeat");

        title.getStyleClass().addAll("text");
        artist.getStyleClass().addAll("text");
        album.getStyleClass().addAll("text");



        //HANDLECOLLECTIONS

        play.setOnAction(handleCollection.play);
        next.setOnAction(handleCollection.next);
        prev.setOnAction(handleCollection.back);
        random.setOnAction(handleCollection.shuffle);
        volume.valueProperty().addListener(handleCollection.volume);
        volume.setValue(0.5);




        // play.setOnAction(this);
        //  next.setOnAction(this);
        //  prev.setOnAction(this);


        //Konfiguration von bottom Pane


        bottompane.getChildren().add(random);
        bottompane.getChildren().add(prev);
        bottompane.getChildren().add(play);
        bottompane.getChildren().add(next);
        bottompane.getChildren().add(repeat);
        bottompane.getChildren().add(volume);

        //bottompane.setPadding(new Insets(0, 0, 20, 0));
        bottompane.setAlignment(Pos.CENTER);

        //Konfiguration von centerpane

        //display = new ImageView("Album.png");
        //centerpane.getChildren().add(display);

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


         centerpane.setScaleX(0.5);
         centerpane.setScaleY(0.5);

         root.setCenter(albumcover);



//TEst
       // rightpane.getChildren().add(versuch);
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

        }



            if (handleCollection.getPlayer().isShuffle()) {
                random.getStyleClass().addAll("buttons", "buttonRandomOnPress");
            }

            if(handleCollection.getPlayer().isShuffle() == false){

                random.getStyleClass().addAll("buttons", "buttonRandom");
            }
        System.out.println(handleCollection.getPlayer().isShuffle());

        handleCollection.currentupdater();
        play.getStyleClass().addAll("buttons", handleCollection.getCurrentplay());


    }
}

