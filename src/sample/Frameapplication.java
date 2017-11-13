package sample;

import Controller.Controller;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.*;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * Created by User on 06.11.2017.
 */
public class Frameapplication extends Application implements EventHandler{
    final int WIDTH = 1200;
    final int HEIGHT = 620;

    private boolean playing = false;

    Controller controller;
    //GUI KOMPONENTE
    Button play = new Button("Play ");
    Button next = new Button("next");
    Button prev = new Button("previous");
    Label title = new Label("Title");
    Label artist = new Label("Artist");
    Label album = new Label("Album");
    Image cover ;
    ImageView display = null;
    Pane centerpane = new Pane();
    Slider volume = new Slider();
    ListView tracksview = new ListView();


    public void init() {
        controller = new Controller();
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {

        //Fenstereinstellungen

        HBox leftpane = new HBox();
        HBox rightpane = new HBox();
        TilePane toppane = new TilePane();
        FlowPane bottompane = new FlowPane();

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




        play.setStyle("-fx-color: blue");





        play.setOnAction(this);
        next.setOnAction(this);
        prev.setOnAction(this);





        //Konfiguration von bottom Pane
        bottompane.setPadding(new Insets(10));
        bottompane.setAlignment(Pos.TOP_CENTER);
        bottompane.setHgap(20);
        bottompane.setStyle("-fx-background-color: black}; ");


        bottompane.getChildren().add(prev);
        bottompane.getChildren().add(play);
        bottompane.getChildren().add(next);


        //Konfiguration von toppane

        //Konfiguration von bottom Pane
        //toppane.setPadding(new Insets(6));
        toppane.setAlignment(Pos.TOP_CENTER);
        toppane.setOrientation(Orientation.VERTICAL);

        toppane.getChildren().add(title);
        toppane.getChildren().add(artist);
        toppane.getChildren().add(album);


        leftpane.getChildren().add(tracksview);
        bottompane.getChildren().add(volume);







        centerpane.setStyle("-fx-background-color: gold;");

        primaryStage.show();
    }

    public void stop() {
    }








    @Override
    public void handle(Event event) {


        if(event.getSource() == play){

            if (playing == false) {
                playing = true;
                play.setText("Pause");
                controller.play();
                title.setText(controller.getTitle());
                album.setText(controller.getAlbum());
                artist.setText(controller.getArtist());
              //  play.setStyle("-fx-color: red");



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

