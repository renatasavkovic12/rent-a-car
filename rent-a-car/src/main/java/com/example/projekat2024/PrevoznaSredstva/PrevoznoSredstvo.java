package com.example.projekat2024.PrevoznaSredstva;
import com.example.projekat2024.Mapa.Koordinate;
import com.example.projekat2024.Simulacija.Iznajmljivanje;
import com.example.projekat2024.Izvjestaji.Racun;
import java.io.Serializable;
import java.util.*;

/**
 * Klasa koja predstavlja PREVOZNO SREDSTVO koja je roditeljska klasa za klase Trotinet, Bicikl i Automobil
 * @author Renata Savkovic
 * @version 1.0
 */
public abstract class PrevoznoSredstvo implements Serializable {
    /**
     * ukupan iznos gubitaka prevoznog sredstva
     */
    public double gubiciVozila = 0;
    /**
     * jedinstveni identifikator vozila
     */
    public String identifikator;
    /**
     * informacija o vrsti vozila
     */
    public String vrstaVozila;
    /**
     * informacija o proizvodjacu vozila
     */
    public String proizvodjac;
    /**
     * informacija o modelu vozila
     */
    public String model;
    /**
     * cijena nabavke vozila
     */
    public int cijenaNabavke;
    /**
     * informacije o svakom iznajmljivanju posmatranog vozila
     */
    public ArrayList<Iznajmljivanje> listaIznajmljivanja;
    /**
     * informacija o trenutnom nivou baterije
     */
    public int trenutniNivoBaterije;
    /**
     * informacije o kvaru vozila, ukoliko se isti dogodio
     */
    public Kvar kvar;
    /**
     * informacija o trenutnoj poziciji vozila na mapi
     */
    public Koordinate trenutnaPozicijaVozila;
    /**
     * informacija o ukupnom trajanju simulacije za posmatrano vozilo
     */
    public long trajanjeSimulacije;
    /**
     * lista svih racuna koji se odnose na posmatrano prevozno sredstvo
     */
    public List<Racun> racuniVozila = new ArrayList<>();
    public Koordinate getTrenutnaPozicijaVozila() {
        return trenutnaPozicijaVozila;
    }
    public void setTrenutnaPozicijaVozila(Koordinate trenutnaPozicijaVozila) {
        this.trenutnaPozicijaVozila = trenutnaPozicijaVozila;
    }
    public String getVrstaVozila() {
        return vrstaVozila;
    }
    public void setVrstaVozila(String vrstaVozila) {
        this.vrstaVozila = vrstaVozila;
    }

    public Kvar getKvar() {
        return kvar;
    }

    public void setKvar(Kvar kvar) {
        this.kvar = kvar;
    }
    public String getIdentifikator() {
        return identifikator;
    }
    public void setIdentifikator(String id) {
        this.identifikator = id;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public int getCijenaNabavke() {
        return cijenaNabavke;
    }
    public void setCijenaNabavke(int cijenaNabavke) {
        this.cijenaNabavke = cijenaNabavke;
    }
    public int getTrenutniNivoBaterije() {
        return trenutniNivoBaterije;
    }
    public void setTrenutniNivoBaterije(int trenutniNivoBaterije) {
        this.trenutniNivoBaterije = trenutniNivoBaterije;
    }
    public ArrayList<Iznajmljivanje> getListaIznajmljivanja() {
        return listaIznajmljivanja;
    }
    public void setListaIznajmljivanja(ArrayList<Iznajmljivanje> listaIznajmljivanja) {
        this.listaIznajmljivanja = listaIznajmljivanja;
    }
    public long getTrajanjeSimulacije() {
        return trajanjeSimulacije;
    }
    public void setTrajanjeSimulacije(long trajanjeSimulacije) {
        this.trajanjeSimulacije = trajanjeSimulacije;
    }
    public PrevoznoSredstvo(String id, String proizvodjac, String model, int cijenaNabavke) {
        setIdentifikator(id);
        setProizvodjac(proizvodjac);
        setModel(model);
        setCijenaNabavke(cijenaNabavke);
        listaIznajmljivanja = new ArrayList<>();
    }
    /**
     * metoda koja se koristi za punjenje baterije vozila, u slucaju kada se ista isprazni
     */
    public void punjenjeBaterije() {
        while (this.trenutniNivoBaterije < 100) {
            this.trenutniNivoBaterije++;
        }
    }
    /**
     * metoda koja se koristi za praznjenje baterije vozila prilikom kretanja
     */
    public void praznjenjeBaterije()
    {
        trenutniNivoBaterije -= 10;
    }
    /**
     * Override metode toString() koja pretvara objekat klase PrevoznoSredstvo u String
     */
    @Override
    public String toString()
    {
        return "Vozilo: "+identifikator+", tipa: "+vrstaVozila + ", ciji je proizvodjac: "+proizvodjac+", te model: "+model+", sa cijenom nabavke: "+cijenaNabavke;
    }
}