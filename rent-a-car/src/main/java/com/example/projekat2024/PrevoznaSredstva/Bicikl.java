package com.example.projekat2024.PrevoznaSredstva;
import java.io.Serializable;

/**
 * Klasa koja predstavlja ELEKTRICNI BICIKL i nasljedjuje klasu PREVOZNO SREDSTVO
 * @author Renata Savkovic
 * @version 1.0
 */
public class Bicikl extends PrevoznoSredstvo implements Serializable {
    /**
     * informacija o dometu bicikla s jednim punjenjem
     */
    public int dometSJednimPunjenjem;
    public int getDometSJednimPunjenjem() {
        return dometSJednimPunjenjem;
    }
    public void setDometSJednimPunjenjem(int dometSJednimPunjenjem) {
        this.dometSJednimPunjenjem = dometSJednimPunjenjem;
    }
    public Bicikl(String id, String proizvodjac, String model,int cijena, int domet)
    {
        super(id,proizvodjac,model,cijena);
        setVrstaVozila("bicikl");
        setDometSJednimPunjenjem(domet);
    }
    /**
     * Override metode toString() koja pretvara informacije o klasi Bicikl u String
     */
    @Override
    public String toString()
    {
        return super.toString()+", domet s jednim punjenjem: "+dometSJednimPunjenjem+"\n"+"Racuni za sva iznajmljivanja: "+this.racuniVozila+"\n";
    }
}
