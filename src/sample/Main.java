package sample;

import Controller.KeyboardController;
import de.hsrm.mi.prog.util.StaticScanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {




    @Override
   public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
       primaryStage.setTitle("Hello World");
       primaryStage.setScene(new Scene(root, 300, 275));
       primaryStage.show();

    }


    public static void main(String[] args) throws IOException {

       if (args.length != 0 && args[0].isEmpty() == false && args[0].equals("key")) {
            System.out.println("MusikPlayer");



            boolean beenden = false;
            KeyboardController eingabe = new KeyboardController();
            String input = null;
            while (beenden == false) {


                System.out.println("Geben Sie einen Befehl ein: ");
                input = StaticScanner.nextString();
                eingabe.befehl(input);
            }
       //     return;

        }
            Application.launch(GUI.class, args);

    }
}
