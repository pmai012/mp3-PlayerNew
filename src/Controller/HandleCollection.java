package Controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.Observable;


/**
 * Created by User on 22.11.2017.
 */
public class HandleCollection extends Observable  {
    Controller controller = new Controller();
    final double TOLERANZ = 0.05;





    Image playicon = new Image("picture/play.png");
    Image playonselect = new Image("picture/playOnSelection.png");
    Image pauseicon = new Image("picture/pause.png");
    Image pauseonselect = new Image("picture/pauseOnSelection.png");
    Image previcon = new Image("picture/prev.png");
    Image nexticon = new Image("picture/next.png");
    Image stopicon = new Image("picture/stop.png");
    Image randomicon = new Image("picture/random.png");
    Image repeaticon = new Image("picture/repeat.png");

    Image currentplay;

    public EventHandler<ActionEvent> play = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            if (controller.getPlayer().getcurrentTrack() == null){
                return;
            }

            if (!controller.isplaying()) {
                controller.play();

                currentplay = pauseicon;
                //centerpane.getChildren().add(albumcover);


            } else {
                controller.pause();
                currentplay = playicon;
            }
            updaten();
        }
    };

    public EventHandler<ActionEvent> next = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.getPlayer().skip();

            updaten();
        }
    };

    public EventHandler<ActionEvent> back = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {


         if (controller.getPlayer().getCurrentTime() <= 3000){

                controller.getPlayer().skipBack();


            }else {
                controller.getPlayer().repeatSong();

            }
            updaten();
        }
    };



    /*
    public EventHandler<MouseEvent> onactive = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          Image bild = (Image) event.getSource();


        }
    };*/

    public EventHandler<MouseEvent> playonenter = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (!controller.isplaying()) {
                currentplay = playonselect;
            } else {
                currentplay = pauseonselect;
            }
            updaten();
        }

    };




    public EventHandler<MouseEvent> playonexit = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (!controller.isplaying()) {
                currentplay = playicon;
            } else {
                currentplay = pauseicon;
            }
            updaten();
        }
    };


    public void currentupdater() {
    if (controller.isplaying()){
        currentplay = pauseicon;
        } else {
        currentplay = playicon;
    }
    }


    /**
     * Der Konstruktor
     */
    public HandleCollection() {
        currentplay = playicon;
    }

    public Image getCurrentplay() {
            return currentplay;

    }

    public void updaten() {

        setChanged();
        notifyObservers("handler");

    }


    public Controller getController() {

        return controller;
    }



}



