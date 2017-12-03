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
import java.util.Observable;

/**
 * Created by User on 22.11.2017.
 */
public class HandleCollection extends Observable {



    public EventHandler<MouseEvent> play = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (controller.isplaying() == false) {
                controller.play();
                cover = new Image(new ByteArrayInputStream(controller.getCover()));
                //centerpane.getChildren().add(albumcover);


            } else {
                controller.pause();
            }
            updaten();
        }
    };
    public EventHandler<MouseEvent> playonenter = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (!getController().isplaying()) {
                currentplay = playonselect;
            }
            updaten();
        }

};




    Controller controller = new Controller();
    Image cover = null;

    Image playicon = new Image("picture/play.png");
    Image playonselect=  new Image("picture/playOnSelection.png");
    Image pauseicon = new Image("picture/pause.png");
    Image previcon = new Image("picture/prev.png");
    Image nexticon = new Image("picture/next.png");
    Image stopicon = new Image("picture/stop.png");
    Image randomicon = new Image("picture/random.png");
    Image repeaticon = new Image("picture/repeat.png");

    Image currentplay;

    /**
     * Der Konstruktor
     */
    public HandleCollection() {
        currentplay = playicon;    }

    public Image getCurrentplay(){
        return currentplay;
    }

    private void updaten(){
        setChanged();
        notifyObservers();
    }



    public Controller getController(){
        return controller;
    }


}



