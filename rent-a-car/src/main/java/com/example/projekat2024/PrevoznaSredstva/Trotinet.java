package com.example.projekat2024.PrevoznaSredstva;
import java.io.Serializable;
/**
 * Klasa koja predstavlja ELEKTRICNI TROTINET i nasljeduje klasu PREVOZNO SREDSTVO
 * @author Renata Savkovic
 * @version 1.0
 */
public class Trotinet extends PrevoznoSredstvo implements Serializable {
    /**
     * informacija o maksimalnoj brzini koju moze da postigne elektricni trotinet
     */
    public int maxBrzina;
    public int getMaxBrzina() {
        return maxBrzina;
    }
    public void setMaxBrzina(int maxBrzina) {
        this.maxBrzina = maxBrzina;
    }
    public Trotinet(String id, String proizvodjac, String model, int cijena, int maxBrzina)
    {
        super(id,proizvodjac,model,cijena);
        setVrstaVozila("trotinet");
        setMaxBrzina(maxBrzina);
    }
    /**
     * Override metode toString() koja pretvara informacije o klasi Trotinet u String
     */
    @Override
    public String toString()
    {
        return super.toString()+", max brzine: "+maxBrzina+"\n"+"Racuni za sva iznajmljivanja: "+this.racuniVozila+"\n";
    }
}
