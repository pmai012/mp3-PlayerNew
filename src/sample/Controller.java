package sample;

import Model.MP3Player;

public class Controller {


    public void befehl(String input){
        MP3Player player = new MP3Player();
        String[] command = input.split(" ");

        for (int i = 0; i < command.length; i++){
        command[i] = command[i].toUpperCase();



        //PLAY BEFEHL
        if (command[i].equals("PLAY")){


            if (i+1 == command.length){

               player.play();
            }else {
                String title ="";
                for(int t = i+1; t < command.length; t++){
                 title = title + command[t]+" ";
                }
                player.play(title);
            }



        }



       }
    }




}
