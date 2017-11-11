package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;



/**
 * Created by User on 06.11.2017.
 */
public class Frameapplication extends Application {
    final int WIDTH = 1200;
    final int HEIGHT = 620;


    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Fenstereinstellungen
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        // primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("3Player");
        root.setPadding(new Insets(20,0,0,0));
        root.setStyle("-fx-background-color: gold;");

        //BUTTON
        Button play = new Button("Play ");
        play.setLayoutX(WIDTH*0.5);
        play.setLayoutY(HEIGHT*0.8 );

        Label title = new Label("Title");
        title.setLayoutX(WIDTH*0.5);
        title.setLayoutY(HEIGHT*0.7);

        Label album = new Label("Album");
        album.setLayoutX(WIDTH*0.5);
        album.setLayoutY(HEIGHT*0.73) ;






        root.getChildren().add(album);
        root.getChildren().add(title);
        root.getChildren().add(play);
        primaryStage.show();
    }

    public void stop() {
    }

}
