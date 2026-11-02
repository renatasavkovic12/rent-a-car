package com.example.projekat2024.RadSaFajlovima;
import com.example.projekat2024.Main;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Klasa MyPROPERTIES sadrzi sve potrebne parametre neophodne za rad simulacije
 * @author Renata Savkovic
 * @version 1.0
 */
public class MyProperties {
    String filePath = "src/main/resources/config.properties";
    static Properties pros;
    public MyProperties() {

        pros = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            pros.load(fileInputStream);
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }
    public static Properties getProperties() {
        return pros;
    }
}

