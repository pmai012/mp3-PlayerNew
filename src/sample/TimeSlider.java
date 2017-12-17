package sample;

import Controller.HandleCollection;
import Model.MP3Player;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Julian on 16.12.2017.
 */

/**
 * Die Timeslider Klasse aktualisiert sich selbstständig, man muss nur eine Mp3 klasse mitgeben!
 */
public class TimeSlider extends BorderPane implements Observer {
    Thread runner = new Thread(new Runner());
    MP3Player player;
    Slider slider = new Slider();
    Label time = new Label("--:--/--:--");
    String laenge;


    public TimeSlider(HandleCollection handleCollection) {
        runner.setName("Slider Thread");
        this.player = handleCollection.getPlayer();
        player.addObserver(this);
        slider.getStyleClass().addAll("sliderTl");
        slider.valueProperty().addListener(handleCollection.position);
        this.setTop(slider);
        this.setRight(time);
        time.setPadding(new Insets(0,20,0,0));


    }

    @Override
    public void update(Observable o, Object arg) {
        if (player.isPlaying() && !runner.isAlive()) {
            runner.start();
        }
        laenge = "/" + Zeit(player.getcurrentTrack().getLength());

    }

    private String Zeit(long millis) {
        long secs = millis / 1000;
        long mins = secs / 60;
        long restsecs = secs % 60;
        String min = "0";
        if (mins < 10) {
            min = "0" + String.valueOf(mins);
        } else {
            min = String.valueOf(mins);
        }
        String sec = "0";
        if (restsecs < 10) {
            sec = "0" + String.valueOf(restsecs);
        } else {
            sec = String.valueOf(restsecs);
        }


        return (min + ":" + sec);
    }

    private class Runner implements Runnable {

        @Override
        public void run() {

            System.out.println("run von Slider gestartet");
            while (true) {

                try {

                    Thread.sleep(500);
                    if (player.getcurrentTrack() != null) {

                        slider.setValue(player.position() * 100);
                        Platform.runLater(() -> {
                            time.setText(Zeit(player.getCurrentTime()) + laenge);
                        });
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


    }
}
