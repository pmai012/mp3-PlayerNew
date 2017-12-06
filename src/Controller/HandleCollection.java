package Controller;


import Model.MP3Player;
import Model.PlaylistManager;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.GUI;
import sample.addPlaylistView;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.util.Observable;


/**
 * Created by User on 22.11.2017.
 */
public class HandleCollection extends Observable {
    final double TOLERANZ = 0.05;
    PlaylistManager playlistManager;
    MP3Player player;
    addPlaylistView addPlaylistView = new addPlaylistView();


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


    public ChangeListener<Number> volume = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            float wert = (((float) newValue.intValue()) / 100);

            /*float wert = newValue.intValue() /100;*/
            player.volume(wert);
        }
    };

    public ChangeListener<Number> position = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            //Position!

        }
    };


    public EventHandler<ActionEvent> play = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            if (getPlayer().getcurrentTrack() == null) {
                return;
            }

            if (!isplaying()) {
                play();

                currentplay = pauseicon;
                //centerpane.getChildren().add(albumcover);


            } else {
                pause();
                currentplay = playicon;
            }
            updaten();
        }
    };

    public EventHandler<ActionEvent> next = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            player.skip();

            updaten();
        }
    };

    public EventHandler<ActionEvent> back = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {


            if (player.getCurrentTime() <= 3000) {

                player.skipBack();


            } else {
                player.repeatSong();

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
            if (!isplaying()) {
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
            if (!isplaying()) {
                currentplay = playicon;
            } else {
                currentplay = pauseicon;
            }
            updaten();
        }
    };
    public EventHandler<MouseEvent> addPlaylist = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                addPlaylistView.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    public EventHandler<ActionEvent> shuffle = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (player.isShuffle()) {
                player.shuffle(false);
            } else {
                player.shuffle(true);
            }
            setChanged();
            notifyObservers("merge");

            updaten();
        }
    };




    public void currentupdater() {
        if (isplaying()) {
            currentplay = pauseicon;
        } else {
            currentplay = playicon;
        }
    }

    public MP3Player getPlayer() {
        return player;
    }

    public boolean isplaying() {
        return player.isPlaying();
    }


    public void play(String path) {
        //track = new Track(path);
        play();

    }

    public void play() {


        player.play();


        setChanged();
        notifyObservers("controller");


    }


    public void pause() {

        player.pause();
    }

    public Image getCover() {
        return new Image(new ByteArrayInputStream(player.getcurrentTrack().getCover()));
    }


    /**
     * Der Konstruktor
     */
    public HandleCollection() {
        player = new MP3Player();
        currentplay = playicon;
    }

    public Image getCurrentplay() {
        return currentplay;

    }

    public void updaten() {

        setChanged();
        notifyObservers("handler");

    }


}



