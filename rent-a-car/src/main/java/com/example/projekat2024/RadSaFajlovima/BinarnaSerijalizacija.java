package com.example.projekat2024.RadSaFajlovima;
import com.example.projekat2024.Main;
import com.example.projekat2024.PrevoznaSredstva.PrevoznoSredstvo;
import java.io.*;
import java.util.logging.Level;
/**
 * Klasa koja predstavlja BINARNU SERIJALIZACIJU podataka u fajlove
 * @author Renata Savkovic
 * @version 1.0
 */
public class BinarnaSerijalizacija {
    /**
     * funkcija za serijalizaciju podataka o vozilima sa najvecim gubicima
     * @param vozilo informacije o vozilu koje se upisuje u fajl
     */
    public static void serijalizujVozilaSNajvecimGubicima(PrevoznoSredstvo vozilo, String fajl)
    {
        try (FileOutputStream fileOut = new FileOutputStream(fajl);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(vozilo);
        } catch (IOException i) {
            Main.logger.log(Level.SEVERE, i.fillInStackTrace().toString());
        }
    }
    /**
     * funkcija za deserijalizaciju podataka o vozilima sa najvecim gubicima
     * @param fajl predstavlja fajl iz kojeg se citaju podaci o vozilima s najvise gubitaka
     */
    public static PrevoznoSredstvo deserijalizujVozilaSNajvecimGubicima(String fajl) {
        PrevoznoSredstvo prevoznoSredstvo = null;
        File file = new File(fajl);

        if (file.exists()) {
            try (FileInputStream fileIn = new FileInputStream(file);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                prevoznoSredstvo = (PrevoznoSredstvo) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
            }
        } else {
            System.out.println("Fajl " + fajl + " ne postoji.");
        }
        return prevoznoSredstvo;
    }
}