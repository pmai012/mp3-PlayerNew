package sample;

import Model.MP3Player;

/**
 * Created by User on 26.10.2017.
 */
public class KeyboardController {

    public void befehl(String input) {

        MP3Player player = new MP3Player();
        String[] command = input.split(" ");


        for (int i = 0; i < command.length; i++) {
            command[i] = command[i].toUpperCase();


            //PLAY BEFEHL
            if (command[i].equals("PLAY")) {


                if (i + 1 == command.length) {

                    player.play();
                } else {
                    String title = "";
                    for (int t = i + 1; t < command.length; t++) {
                        title = title + command[t] + " ";
                    }
                    player.play(title);
                }


            }

            //PAUSE BEFEHL
            if (command[i].equals("PAUSE")) {
                player.pause();
            }

            //PAUSE BEFEHL
            if (command[i].equals("STOP")) {
                player.stop();
            }

            if (command[i].equals("VOLUME")) {
                if (i + 1 == command.length) {
                    float value = Float.parseFloat(command[i + 1]);
                    player.volume(value);
                } else {
                    System.out.println("Geben Sie bitte einen Wert mit!");
                }
            }

            if (command[i].equals("BALANCE")) {
                if (i + 1 == command.length) {
                    float value = Float.parseFloat(command[i + 1]);
                    player.balance(value);
                } else {
                    System.out.println("Geben Sie bitte einen Wert mit!");
                }
            }

        }
    }


}


