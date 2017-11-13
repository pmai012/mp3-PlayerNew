package Controller;

import Model.MP3Player;
import Model.Playlist;
import Model.PlaylistManager;
import Model.Track;


import java.io.IOException;

/**
 * Created by User on 26.10.2017.
 */
public class KeyboardController {
    MP3Player player;
    PlaylistManager playlistManager;

    public KeyboardController() {
        player = new MP3Player();
        playlistManager = new PlaylistManager();
    }

    public void befehl(String input) {


        String[] command = input.split(" ");


        for (int i = 0; i < command.length; i++) {
            command[i] = command[i].toUpperCase();


            //PLAY BEFEHL
            if (command[i].equals("PLAY")) {


                if (i + 1 == command.length) {

                    player.play();
                } else {

                    String title = "";
                    for (int t = i + 1; t < command.length - 1; t++) {
                        title = title + command[t] + " ";
                    }

                    title = title + command[command.length - 1];
                    Track pfad = new Track(title);
                    player.play(pfad);
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
            if (command[i].equals("SKIP")) {
                player.skip();
            }


            if (command[i].equals("VOLUME")) {
                if (i + 1 <= command.length) {
                    float value = Float.parseFloat(command[i + 1]);
                    player.volume(value);
                } else {
                    System.out.println("Geben Sie bitte einen Wert mit!");
                }
            }

            if (command[i].equals("LOAD")) {
                if (i + 1 <= command.length) {
                    String value = command[i + 1];
                    player.loadPlaylist(value);


                } else {
                    System.out.println("Geben Sie bitte einen Wert mit!");
                }
            }

            if (command[i].equals("BALANCE")) {
                if (i + 1 <= command.length) {
                    float value = Float.parseFloat(command[i + 1]);
                    player.balance(value);
                } else {
                    System.out.println("Geben Sie bitte einen Wert mit!");
                }
            }
            if (command[i].equalsIgnoreCase("PLAYLIST")) {
                if (command[i + 1].equalsIgnoreCase("ALLES")) {
                    playlistManager.getAllTracks();
                }
                    if (command[i + 1].equalsIgnoreCase("SUCHEN")) {
                    playlistManager.findPlaylist(command[i + 2]);
                } else if (command[i + 1].equalsIgnoreCase("ERSTELLEN")) {
                    playlistManager.setPlaylist(new Playlist(command[i + 2],null));
                } else if (command[i + 1].equalsIgnoreCase("LOESCHEN")) {

                } else if (command[i + 1].equalsIgnoreCase("AKTUALISIEREN")) {

                }
            }

        }
    }


}


