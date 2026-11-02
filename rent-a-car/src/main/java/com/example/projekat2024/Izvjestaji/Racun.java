package com.example.projekat2024.Izvjestaji;
import com.example.projekat2024.PrevoznaSredstva.Automobil;
import com.example.projekat2024.PrevoznaSredstva.Bicikl;
import com.example.projekat2024.PrevoznaSredstva.Trotinet;
import com.example.projekat2024.RadSaFajlovima.MyProperties;
import com.example.projekat2024.Simulacija.Iznajmljivanje;
import com.example.projekat2024.Simulacija.Korisnik;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;
/**
 * Klasa koja predstavlja RACUN ZA PLACANJE
 * @author Renata Savkovic
 * @version 1.0
 */
public class Racun implements Serializable {
    /**
     * iznos voznje za uzi dio grada
     */
    public double iznosUziDio;
    /**
     * iznos voznji za siri dio grada
     */
    public double iznosSiriDio;
    public double gubitak=0;
    public double pomocniIznos;
    /**
     * informacija o udaljenosti kretanja
     */
    public double udaljenost;
    /**
     * jedinstveni identifikator racuna
     */
    public String idRacuna;
    /**
     * informacija o datumu i vremenu iznajmljivanja za koje se izdaje racun
     */
    public Date datumVrijeme;
    /**
     * pomocna promjenljiva koja sluzi za numerisanje racuna
     */
    public static int count =0;
    /**
     * informacija o postojanju kvara na vozilu za koje se izdaje racun
     */
    public boolean kvarovi = false;
    /**
     * informacija o popustima za placanje
     */
    public double popust;
    /**
     * informacija o promocijama
     */
    public double promocije=0;
    /**
     * informacija o osnovnoj cijeni
     */
    public double osnovnaCijena;
    /**
     * informacija o ukupnom iznosu koji je potrebno da se plati nakon obracuna popusta i promocija
     */
    public double ukupnaCijenaZaPlacanje;
    /**
     * informacija o ukupnom iznosu koji je potrebno platiti bez popusta i promocija
     */
    public double iznos;
    /**
     * informacija o korisniku kojem se izdaje racun
     */
    public Korisnik korisnik;
    public Racun(Iznajmljivanje iznajmljivanje) {
        Properties pro = MyProperties.getProperties();
        korisnik = new Korisnik(iznajmljivanje.imeKorisnika);
        idRacuna = "racun"+count++;
        this.datumVrijeme = iznajmljivanje.datumVrijeme;
        iznajmljivanje.racunZaIznajmljivanje = this;
        if(iznajmljivanje.vozilo instanceof Automobil)
        {
            String distancaAutomobil = pro.getProperty("CAR_UNIT_PRICE");
            if(distancaAutomobil != null)
            {
                double distancaA = Double.parseDouble(distancaAutomobil);
                osnovnaCijena = iznajmljivanje.trajanjeKoristenja*distancaA;
            }
            else
            {
                throw new IllegalArgumentException("CAR_UNIT_PRICE property is not defined in config.properties");
            }
        }
        else if(iznajmljivanje.vozilo instanceof Trotinet)
        {
            String distancaTrotinet = pro.getProperty("SCOOTER_UNIT_PRICE");
            if(distancaTrotinet != null)
            {
                double distancaT = Double.parseDouble(distancaTrotinet);
                osnovnaCijena = iznajmljivanje.trajanjeKoristenja*distancaT;
            }
            else
            {
                throw new IllegalArgumentException("SCOOTER_UNIT_PRICE property is not defined in config.properties");
            }
        }
        else if(iznajmljivanje.vozilo instanceof Bicikl)
        {
            String distancaBicikl = pro.getProperty("BIKE_UNIT_PRICE");
            if (distancaBicikl != null) {
                double distancaB = Double.parseDouble(distancaBicikl);
                osnovnaCijena = iznajmljivanje.trajanjeKoristenja*distancaB;
                // System.out.println("Osnovna cijena iznamljivanja je: "+osnovnaCijena + " jer je trajanje simulacije "+iznajmljivanje.trajanjeKoristenja + " a distanca: "+ distancaB);
            } else {
                throw new IllegalArgumentException("BIKE_UNIT_PRICE property is not defined in config.properties");
            }
        }
        if (iznajmljivanje.vozilo.trenutnaPozicijaVozila != null) {
            int i = iznajmljivanje.vozilo.trenutnaPozicijaVozila.i;
            int j = iznajmljivanje.vozilo.trenutnaPozicijaVozila.j;

            if (i < 15 && i > 4 && j < 15 && j > 4) {
                setUdaljenost(osnovnaCijena*Double.parseDouble(pro.getProperty("DISTANCE_NARROW")));
                if(!iznajmljivanje.kvar) {
                    iznosUziDio = osnovnaCijena * udaljenost;
                }
            } else {
                setUdaljenost(osnovnaCijena*Double.parseDouble(pro.getProperty("DISTANCE_WIDE")));
                if(!iznajmljivanje.kvar) {
                    iznosSiriDio = osnovnaCijena * udaljenost;
                }
            }
        }
        iznos = osnovnaCijena * udaljenost;
        pomocniIznos = iznos - popust - promocije;
        if(iznajmljivanje.kvar)
        {
            setOsnovnaCijena(0);
            this.kvarovi = true;
        }
        String discountStr = pro.getProperty("DISCOUNT");
        if(korisnik.listaIznajmljivanjaKorisnika.size()%10==0 && discountStr!=null)
        {
            setPopust(osnovnaCijena * Double.parseDouble(discountStr));
        }
        else {
            popust = 0;
        }
        String discountPromStr = pro.getProperty("DISCOUNT_PROM");
        if (discountPromStr != null && iznajmljivanje.promocija) {
            promocije = osnovnaCijena*Double.parseDouble(discountPromStr);
        }
        if(kvarovi)
        {
            ukupnaCijenaZaPlacanje = 0;
            iznos = 0;
        }
        else
        {
            ukupnaCijenaZaPlacanje = pomocniIznos;
        }
        gubitak = pomocniIznos - ukupnaCijenaZaPlacanje;
    }
    public void setUdaljenost(double udaljenost) {
        this.udaljenost = udaljenost;
    }
    public boolean isKvarovi() {
        return kvarovi;
    }
    public void setKvarovi(boolean kvarovi) {
        this.kvarovi = kvarovi;
    }
    public double getPopust() {
        return popust;
    }
    public void setPopust(double popust) {
        this.popust = popust;
    }
    public double getPromocije() {
        return promocije;
    }
    public void setPromocije(double promocije) {
        this.promocije = promocije;
    }
    public double getOsnovnaCijena() {
        return osnovnaCijena;
    }
    public void setOsnovnaCijena(double osnovnaCijena) {
        this.osnovnaCijena = osnovnaCijena;
    }
    public double getUdaljenost() {
        return udaljenost;
    }
    /**
     * Override metode toString() koji objekat klase Racun pretvara u String
     */
    public String toString()
    {
        return "\n"+"RACUN:"+idRacuna+", za iznajmljivanje:"+datumVrijeme+", korisnika: "+korisnik.idKorisnika+", trebalo bi da plati: "+String.format("%.2f", pomocniIznos)+", a placa: "+String.format("%.2f", ukupnaCijenaZaPlacanje)+"\n";
    }
}
