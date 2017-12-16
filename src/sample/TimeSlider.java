package sample;

import Model.MP3Player;
import javafx.scene.control.Slider;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by User on 16.12.2017.
 */
public class TimeSlider extends Slider  implements Observer{
    Thread runner = new Thread(new Runner());
    MP3Player player;
    public TimeSlider(MP3Player player){
        this.player = player;
        player.addObserver((Observer) this);


    }

    @Override
    public void update(Observable o, Object arg) {
        if (player.isPlaying() && !runner.isAlive()){
         runner.start();
        }

    }


    private class Runner implements Runnable {

       @Override
       public void run() {

           System.out.println("run von Slider gestartet");
           while (true) {

               try {

                   Thread.sleep(500);
                   if (player.getcurrentTrack() != null) {

                      TimeSlider.this.setValue(player.position() * 100);


                   }

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
       }


   }
}
