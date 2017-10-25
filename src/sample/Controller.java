package sample;

import Model.MP3Player;

public class Controller {

    public void befehl(String input){
        String[] command = input.split(" ");
       for (int i = 0; i < command.length; i++){
        command[i] = command[i].toUpperCase();

        if (command[i].equals("PLAY")){
            MP3Player player = new MP3Player();

            if (i+1 <= command.length){
                player.play(command[i+1]);
                i++;
                break;
          } else {
                player.play();
            }


        }



       }
    }


}
