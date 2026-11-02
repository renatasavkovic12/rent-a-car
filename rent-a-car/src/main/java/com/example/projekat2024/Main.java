package com.example.projekat2024;
import com.example.projekat2024.Kontroleri.MainController;
import com.example.projekat2024.RadSaFajlovima.MyProperties;
import javafx.application.Application;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * osnovna Main klasa iz koje se pokrece kompletan program
 */
public class Main extends Application {
    private static MainController mainController;
    public static Handler handler;
    public static Logger logger;

    static
    {
        try {
            handler = new FileHandler("iznajmljivanje.log", true);
            logger = Logger.getLogger(Main.class.getName());
            logger.addHandler(handler);
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        mainController = loader.getController();
        System.out.println("MainController instance inicijalizovana: " + (mainController != null));
        stage.setTitle("Simulacija");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static MainController getMainController() {
        return mainController;
    }
    public static void main (String[]args){
        MyProperties properties = new MyProperties();
        launch();
    }

}