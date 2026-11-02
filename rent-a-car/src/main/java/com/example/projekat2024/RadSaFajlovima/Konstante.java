package com.example.projekat2024.RadSaFajlovima;
import java.text.SimpleDateFormat;
/**
 * Klasa koja predstavlja KONSTANTE koristene unutar projekta
 * @author Renata Savkovic
 * @version 1.0
 */
public class Konstante {
    /**
     * dimenzija matrice
     */
    public static final int DIMENZIJE_MATRICE = 20;
    /**
     * niz mogucih razloga kvara vozila
     */
    public static final String[] razlogKvara= {
            "kvar 1",
            "kvar 2",
            "kvar 3",
            "kvar 4",
            "kvar 5"
    };
    /**
     * format na osnovu kojeg se definise prikaz datuma i vremena
     */
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("d.M.yyyy HH:mm");
    /**
     * format na osnovu kojeg se definise prikaz datuma
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
}
