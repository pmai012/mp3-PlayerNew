package sample;

import Controller.Controller;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * Created by User on 06.11.2017.
 */
public class Frameapplication extends Application implements EventHandler{
    final int WIDTH = 1200;
    final int HEIGHT = 620;

    private boolean playing = false;

    Controller controller = new Controller();
    //GUI KOMPONENTE
    Button play = new Button("Play ");
    Button next = new Button("next");
    Button prev = new Button("previous");
    Label title = new Label("Title");
    Label artist = new Label("Artist");
    Label album = new Label("Album");
    Image cover ;
    FlowPane centerpane = new FlowPane();


    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception  {

        //Fenstereinstellungen

        HBox leftpane = new HBox();
        HBox rightpane = new HBox();
        FlowPane toppane = new FlowPane();
        FlowPane bottompane = new FlowPane();

        BorderPane root = new BorderPane();
        root.setTop(toppane);
        root.setBottom(bottompane);
        root.setLeft(leftpane);
        root.setRight(rightpane);
        root.setCenter(centerpane);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        // primaryStage.initStyle(StageStyle.TRANSPARENT);
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
        bottompane.setStyle("-fx-background-color: black}; ");


        bottompane.getChildren().add(prev);
        bottompane.getChildren().add(play);
        bottompane.getChildren().add(next);

        //Konfiguration von toppane
        toppane.setAlignment(Pos.TOP_CENTER);
        toppane.setPadding(new Insets(1));
        toppane.getChildren().add(title);
        toppane.getChildren().add(artist);
        toppane.getChildren().add(album);
        toppane.setOrientation(Orientation.VERTICAL);








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

                byte[] coverdata = controller.getCover();
            } else{
                playing = false;
                controller.pause();
                title.setText("");
                album.setText("");
                artist.setText("");
                play.setText("Play");
            }


        }
    }
}

