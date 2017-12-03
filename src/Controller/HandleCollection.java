package Controller;


import Model.Track;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.Frameapplication;

import java.io.ByteArrayInputStream;
import java.util.EventListener;

/**
 * Created by User on 22.11.2017.
 */
public class HandleCollection {


    public EventHandler<MouseEvent> play;
    Controller controller = new Controller();
    Image cover = null;




    public HandleCollection() {
                Buttoninit();
    }



    public Controller getController(){
        return controller;
    }
    private void Buttoninit() {
        play = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (controller.isplaying() == false) {
                    controller.play();
                    cover = new Image(new ByteArrayInputStream(controller.getCover()));
                   //centerpane.getChildren().add(albumcover);


                } else {
                    controller.pause();
                }
            }
        };
    }
}



