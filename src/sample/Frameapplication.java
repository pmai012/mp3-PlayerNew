package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;



/**
 * Created by User on 06.11.2017.
 */
public class Frameapplication extends Application {

    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1200, 620);
        primaryStage.setScene(scene);
        primaryStage.setTitle("3Player");
        primaryStage.show();



    }

    public void stop() {
    }

}
