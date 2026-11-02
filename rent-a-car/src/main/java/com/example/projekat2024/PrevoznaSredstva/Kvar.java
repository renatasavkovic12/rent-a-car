package com.example.projekat2024.PrevoznaSredstva;
import com.example.projekat2024.RadSaFajlovima.Konstante;
import java.io.Serializable;
import java.util.Random;
/**
 * Klasa koja predstavlja KVAR
 * @author Renata Savkovic
 * @version 1.0
 */
public class Kvar implements Serializable {
    /**
     * jedinstveni identifikator vozila kojem se kvar desio
     */
    public String idVozila;
    /**
     * opis kvara koji se desio
     */
    public String opisKvara;
    /**
     * datum i vrijeme kada se kvar desio
     */
    public String datumVrijemeKvara;
    /**
     * vrsta vozila kojem se kvar desio
     */
    public String vrstaVozila;
    public String getIdVozila() {
        return idVozila;
    }
    public void setIdVozila(String idVozila) {
        this.idVozila = idVozila;
    }
    public String getOpisKvara() {
        return opisKvara;
    }
    public void setOpisKvara(String opisKvara) {
        this.opisKvara = opisKvara;
    }
    public String getDatumVrijemeKvara() {
        return datumVrijemeKvara;
    }
    public void setDatumVrijemeKvara(String datumVrijemeKvara) {
        this.datumVrijemeKvara = datumVrijemeKvara;
    }
    public String getVrstaVozila() {
        return vrstaVozila;
    }
    public void setVrstaVozila(String vrstaVozila) {
        this.vrstaVozila = vrstaVozila;
    }
    public Kvar(String idVozila, String datumVrijemeKvara)
    {
        setIdVozila(idVozila);
        setDatumVrijemeKvara(datumVrijemeKvara);
        Random rand = new Random();
        setOpisKvara(Konstante.razlogKvara[rand.nextInt(0,5)]);
        if(idVozila.startsWith("A"))
        {
            setVrstaVozila("automobil");
        }
        else if(idVozila.startsWith("B"))
        {
            setVrstaVozila("bicikl");
        }
        else if(idVozila.startsWith("T"))
        {
            setVrstaVozila("trotinet");
        }
        else
        {
            setVrstaVozila("greska");
        }
    }
    public Kvar(String vrstaVozila,String idVozila,String datumVrijemeKvara, String opisKvara)
    {
        setIdVozila(idVozila);
        setDatumVrijemeKvara(datumVrijemeKvara);
        setOpisKvara(opisKvara);
        setVrstaVozila(vrstaVozila);
    }
    /**
     * Override metode toString() koja pretvara objekat klase Kvar u tip String
     * @return string
     */
    @Override
    public String toString()
    {
        return "Vozilo koje se pokvarilo: "+idVozila+" desio se kvar: "+opisKvara+" u vrijeme: " +datumVrijemeKvara+"\n";
    }
}