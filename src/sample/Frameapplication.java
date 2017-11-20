package sample;

import Controller.Controller;
import Model.Playlist;
import Model.PlaylistManager;
import Model.Track;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * Created by User on 06.11.2017.
 */
public class Frameapplication extends Application implements EventHandler{
    final int WIDTH = 1200;
    final int HEIGHT = 620;

    private boolean playing = false;

    PlaylistManager playlistManager = new PlaylistManager();

    Controller controller = new Controller();
    //GUI KOMPONENTE

    Image playico = new Image("picture/play.jpg");
    Image pauseico = new Image("picture/play.jpg");
    Image previco = new Image("picture/play.jpg");
    Image nextico = new Image("picture/play.jpg");
    Image stopico = new Image("picture/play.jpg");

    Button play = new Button("", new ImageView(playico));
    Button next = new Button("",new ImageView(nextico));
    Button prev = new Button("", new ImageView(previco));
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

    }

    @Override
    public void start(Stage primaryStage) throws Exception  {
        playlistManager.loadPlaylists(System.getProperty("user.home").concat("//Music"));

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
                    for (Track t: playlistManager.getAllTracks().getTracks())
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

        btn_sideView_back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                leftpane.getChildren().clear();
                leftpane.getChildren().add(sideView);
            }
        });

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(200);
        primaryStage.setTitle("3Player");

        root.setPadding(new Insets(20,0,0,0));
        root.setStyle("-fx-background-color: gold;");









        play.setOnAction(this);
        next.setOnAction(this);
        prev.setOnAction(this);





        //Konfiguration von bottom Pane
        bottompane.setPadding(new Insets(10));
        bottompane.setAlignment(Pos.TOP_CENTER);
        bottompane.setHgap(20);
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








    @Override
    public void handle(Event event) {


        if(event.getSource() == play){
            System.out.println("Button wurde betaetigt");


            if (playing == false) {
                playing = true;
                play.setText("Pause");
                controller.play();
                title.setText(controller.getTitle());
                album.setText(controller.getAlbum());
                artist.setText(controller.getArtist());




                byte[] coverdata = controller.getCover();
            } else{
                playing = false;
                controller.pause();
                title.setText("");
                album.setText("");
                artist.setText("");
                play.setText("Play");
               // play.setStyle("-fx-color: blue");
            }


        }
    }
}

