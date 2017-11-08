package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;


/**
 * Created by User on 06.11.2017.
 */
public class Frameapplication extends Application {

    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button knopf = new Button("Klick mich ! ");


        Pane root = new Pane();
        root.getChildren().add(knopf);

        root.setPadding(new Insets(20,0,0,0));
        root.setStyle("-fx-background-color: gold;");

        Scene scene = new Scene(root, 1200, 620);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("3Player");
        primaryStage.show();




    }

    public void stop() {
    }

}
