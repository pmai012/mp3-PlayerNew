package sample;

import Model.MP3Player;
import javafx.scene.control.Slider;

/**
 * Created by User on 16.12.2017.
 */
public class TimeSlider extends Slider {
    Thread runner = new Thread(new Runner());
    MP3Player player;
    public TimeSlider(MP3Player player){
        this.player = player;
        runner.start();
    }




   private class Runner implements Runnable{

       @Override
       public void run() {

           System.out.println("run auf Gui gestartet");
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
