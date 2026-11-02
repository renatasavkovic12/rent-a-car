package com.example.projekat2024.Simulacija;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Klasa koja predstavlja KORISNIKA
 * @author Renata Savkovic
 * @version 1.0
 */
public class Korisnik implements Serializable{
    /**
     * broj vozacke posmatranog korisnika
     */
    public int brojVozacke;
    /**
     * informacija o tome da li je korisnik strani drzavljanin ili nije
     */
    private boolean straniDrzavljanin;
    /**
     * informacija o licnoj karti korisnika
     */
    public String licnaKarta;
    /**
     * informacija o pasosu korisnika
     */
    public String pasos;
    /**
     * jedinstveni identifikator korisnika
     */
    public String idKorisnika;
    /**
     * informacija o svim iznajmljivanjima posmatranog korisnika
     */
    public List<Iznajmljivanje> listaIznajmljivanjaKorisnika = new ArrayList<>();
    private int getBrojVozacke() {
        return brojVozacke;
    }
    private void setBrojVozacke(int brojVozacke) {
        this.brojVozacke = brojVozacke;
    }
    private boolean isStraniDrzavljanin() {
        return straniDrzavljanin;
    }
    private void setStraniDrzavljanin(boolean straniDrzavljanin) {
        this.straniDrzavljanin = straniDrzavljanin;
    }
    private String getLicnaKarta() {
        return licnaKarta;
    }
    private void setLicnaKarta(String licnaKarta) {
        this.licnaKarta = licnaKarta;
    }
    public String getPasos() {
        return pasos;
    }
    public void setPasos(String pasos) {
        this.pasos = pasos;
    }
    public String getIdKorisnika() {
        return idKorisnika;
    }
    public void setIdKorisnika(String idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public Korisnik(String idKorisnika){
        Random rand = new Random();
        setStraniDrzavljanin(rand.nextBoolean());
        setIdKorisnika(idKorisnika);
        if(straniDrzavljanin)
        {
            setPasos("Pasos:"+idKorisnika);
        }
        else
        {
            setLicnaKarta("Licna karta:"+idKorisnika);
        }
        brojVozacke = izdvojiBroj(idKorisnika);
    }
    private static int izdvojiBroj(String input) {
        Pattern pattern = Pattern.compile("(\\d+)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String brojString = matcher.group(1);
            return Integer.parseInt(brojString);
        } else {
            throw new IllegalArgumentException("String ne sadrži broj na kraju: " + input);
        }
    }
    /**
     * Override metode toString() koja pretvara informacije o klasi Korisnik u String
     */
    @Override
    public String toString()
    {
        return "Korisnik: "+getIdKorisnika() + " koji " + (isStraniDrzavljanin()?"je ":"nije "+"strani drzavljanin.");
    }
}
