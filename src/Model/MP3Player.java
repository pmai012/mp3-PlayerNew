package Model;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

/**
 * Created by Pascal on 25.10.2017.
 */
public class MP3Player {
    SimpleMinim minim;
    SimpleAudioPlayer audioPlayer;

    public MP3Player(){}

    private void play(String fileName){}
    private void play(){
        audioPlayer.play();
    }
    private void pause(){
        audioPlayer.pause();
    }
    private void stop(){

    }
    private void volume(float value){
        audioPlayer.setVolume(value);
    }
    private void balance(float value){
        audioPlayer.setBalance(value);
    }
}
