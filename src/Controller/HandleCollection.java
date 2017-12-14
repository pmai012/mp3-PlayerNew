package Controller;


import Model.MP3Player;
import Model.Playlist;
import Model.PlaylistManager;
import Model.Track;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import sample.addPlaylistView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Observable;


/**
 * Created by User on 22.11.2017.
 */
public class HandleCollection extends Observable {
    final double TOLERANZ = 0.05;
    PlaylistManager playlistManager;
    MP3Player player;
    addPlaylistView addPlaylistView = new addPlaylistView(); //WIEDER EINRÜCKEN EXCEPTIONS


    Image playicon = new Image("picture/play.png");
    Image playonselect = new Image("picture/playOnSelection.png");
    Image pauseicon = new Image("picture/pause.png");
    Image pauseonselect = new Image("picture/pauseOnSelection.png");
    Image previcon = new Image("picture/prev.png");
    Image nexticon = new Image("picture/next.png");
    Image stopicon = new Image("picture/stop.png");
    Image randomicon = new Image("picture/random.png");
    Image repeaticon = new Image("picture/repeat.png");

    String currentplay;


    public ChangeListener<Number> volume = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            float wert = (((float) newValue.intValue()) / 100);

            if (player.getcurrentTrack() != null) {
                player.volume(wert);
            }
        }
    };

    public ChangeListener<Number> position = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
     float prozent = newValue.floatValue();
     prozent = prozent/100;

     if (newValue.floatValue() != player.position()*100) {
         player.setCurrentTime((long) (player.getcurrentTrack().getLength() * prozent));
     }
        }
    };



    public EventHandler<ActionEvent> play = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            if (getPlayer().getcurrentTrack() == null) {
                getPlayer().setCurrentNumber(0);
            }

            if (!player.isPlaying()) {
                play();

                currentplay = "buttonPause";


            } else {
                pause();
                currentplay = "buttonPlay";

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
    public EventHandler<ActionEvent> repeat = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
           player.repeat(!player.isRepeat());
        }
    };



    /*
    public EventHandler<MouseEvent> onactive = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          Image bild = (Image) event.getSource();


        }
    };*/

    public void addPlaylist() {
        try {
            addPlaylistView.start(new Stage()); //WIEDER EINRÜCKEN EXCEPTIONS
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public EventHandler<ActionEvent> shuffle = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          //Damit der Wert übergeben wird und man ab er aktuellen position weiter süielen kann!

           player.shuffle(!player.isShuffle());


            setChanged();
            notifyObservers("merge");

            updaten();


        }
    };

    public void songViewMouseDragOver(final DragEvent e) {
        final Dragboard db = e.getDragboard();

        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".mp3");

        if (db.hasFiles()) {
            if (isAccepted) {
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            e.consume();
        }
    }

    public void songViewMouseDragDropped(final DragEvent e, ObservableList<String> songs, Playlist actPlaylist) {
        final Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            // Only get the first file from the list
            final File file = db.getFiles().get(0);
            Track t = new Track(file.getAbsolutePath());
            actPlaylist.getTracks().add(t);
            playlistManager.updatePlaylist(actPlaylist);
            songs.add(t.getTitle());
        }
        e.setDropCompleted(success);
        e.consume();
    }


    public void currentupdater() {
        if (isplaying()) {
            currentplay = "buttonPause";
        } else {
            currentplay = "buttonPlay";
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

        currentupdater();
        setChanged();
        notifyObservers("controller");


    }


    public void pause() {

        player.pause();
        currentupdater();
        setChanged();
        notifyObservers("controller");
    }

    public Image getCover() {
        Image cover = null;
        try{
             cover = new Image(new ByteArrayInputStream(player.getcurrentTrack().getCover()));
        }catch (java.lang.NullPointerException e){

        }


        if (cover == null){
            return new Image("picture/placeholderCover.png");
        }else {
            return cover;
        }
    }


    /**
     * Der Konstruktor
     */
    public HandleCollection() {
        player = new MP3Player();
        currentplay = "buttonPlay";
        playlistManager = new PlaylistManager();
    }

    public String getCurrentplay() {
        return currentplay;

    }

    public void updaten() {

        setChanged();
        notifyObservers("handler");

    }

}



