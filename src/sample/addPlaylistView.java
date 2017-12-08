package sample;

import Controller.AddPlaylistController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;

/**
 * Created by Pascal on 06.12.2017.
 */
public class addPlaylistView extends Application{
    AddPlaylistController controller = new AddPlaylistController(this);
    Label info = new Label("Wie soll Ihre Playlist heißen?");
    TextField textField = new TextField();
    StackPane secondaryLayout = new StackPane();
    Button btn_ok = new Button("Bestätigen");
    Button btn_cancel = new Button("Abbrechen");
    Scene secondScene = new Scene(secondaryLayout, 250, 100);
    Stage addPlaylistStage;

    public void init()
    {
        info.setTranslateY(-35);
        btn_ok.setTranslateX(-50);
        btn_ok.setTranslateY(35);
        btn_cancel.setTranslateX(50);
        btn_cancel.setTranslateY(35);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        addPlaylistStage = primaryStage;
        init();
        secondaryLayout.getChildren().add(info);
        secondaryLayout.getChildren().add(textField);
        secondaryLayout.getChildren().add(btn_ok);
        secondaryLayout.getChildren().add(btn_cancel);

        btn_ok.setOnMouseClicked(controller.btn_ok_mouseclick);
        btn_cancel.setOnMouseClicked(controller.btn_cancel_mouseclick);

        addPlaylistStage.resizableProperty().setValue(false);
        addPlaylistStage.setTitle("Playlist hinzufügen");
        addPlaylistStage.initModality(Modality.APPLICATION_MODAL);
        addPlaylistStage.setScene(secondScene);
        addPlaylistStage.show();
    }
    public void stop(){
        secondaryLayout.getChildren().clear();
        addPlaylistStage.close();
    }
    public void invokeErrorMessage()
    {
        info.setText(info.getText() + " [Name fehlt]");
        info.setTextFill(javafx.scene.paint.Paint.valueOf("RED"));
    }

    public String getTextFieldValue(){
        if (textField.getText() != null && !textField.getText().isEmpty())
        {
            return textField.getText();
        }
        return null;
    }
}