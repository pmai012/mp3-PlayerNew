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
        BorderPane root = new BorderPane();
        HBox leftpane = new HBox();
        HBox rightpane = new HBox();
        HBox toppane = new HBox();
        HBox bottompane = new HBox();
        HBox centerpane = new HBox();

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

        //BUTTON
        Button play = new Button("Play ");


        Label title = new Label("Title");


        Label album = new Label("Album");







        toppane.getChildren().add(title);
        primaryStage.show();
    }

    public void stop() {
    }

}
