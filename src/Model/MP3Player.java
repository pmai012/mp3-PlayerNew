package Model;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

/**
 * Created by Pascal on 25.10.2017.
 */
public class MP3Player {
    private SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;




    public MP3Player(){}

    public void play(String fileName){

        minim = new SimpleMinim();
        audioPlayer = minim.loadMP3File(fileName);
        play();
        System.out.println("Es wird " + fileName + " gespielt");

    }
    public void play(){
        audioPlayer.play();
    }
    public void pause(){
        audioPlayer.pause();
    }
    public void stop(){

    }
    public void volume(float value){
        audioPlayer.setVolume(value);
    }
    public void balance(float value){
        audioPlayer.setBalance(value);
    }
}
