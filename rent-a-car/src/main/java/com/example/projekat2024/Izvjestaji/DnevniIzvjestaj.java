package com.example.projekat2024.Izvjestaji;
/**
 * Klasa koja predstavlja DNEVNI IZVJESTAJ
 * @author Renata Savkovic
 * @version 1.0
 */
public class DnevniIzvjestaj{
    /**
     * informacija o datumu za koji se kreira dnevni izvjestaj
     */
    public String datumIzvjestaja;
    /**
     * informacija o ukupnom prihodu na dnevnom nivou
     */
    public double ukupanPrihod;
    /**
     * informacija o ukupnim popustima na dnevnom nivou
     */
    public double ukupanPopust;
    /**
     * informacija o ukupnim promocijama na dnevnom nivou
     */
    public double ukupnoPromocija;
    /**
     * informacija o ukupnom iznosu voznji na uzem dijelu grada
     */
    public double iznosVoznjiUziDio;
    /**
     * informacija o ukupnom iznosu voznji na sirem dijelu grada
     */
    public double iznosVoznjiSiriDio;
    /**
     * informacija o ukupnom odrzavanju na dnevnom nivou
     */
    public double ukupnoOdrzavanje;
    /**
     * informacija o avim popravkama na dnevnom nivou
     */
    public double ukupnoPopravke;
    public DnevniIzvjestaj(){}
    /**
     * funkcija getter() za atribut datumIzvjestaja
     * @return datumIzvjestaja
     */
    public String getDatumIzvjestaja() {
        return datumIzvjestaja;
    }
    /**
     * funkcija setter() za atribut datumIzvjestaja
     * @param datumIzvjestaja predstavlja datum za koji se kreira izvjestaj
     */
    public void setDatumIzvjestaja(String datumIzvjestaja) {
        this.datumIzvjestaja = datumIzvjestaja;
    }

    public double getUkupanPrihod() {
        return ukupanPrihod;
    }

    public void setUkupanPrihod(double ukupanPrihod) {
        this.ukupanPrihod = ukupanPrihod;
    }

    public double getUkupanPopust() {
        return ukupanPopust;
    }

    public void setUkupanPopust(double ukupanPopust) {
        this.ukupanPopust = ukupanPopust;
    }

    public double getUkupnoPromocija() {
        return ukupnoPromocija;
    }

    public void setUkupnoPromocija(double ukupnoPromocija) {
        this.ukupnoPromocija = ukupnoPromocija;
    }

    public double getUkupnoOdrzavanje() {
        return ukupnoOdrzavanje;
    }

    public void setUkupnoOdrzavanje(double ukupnoOdrzavanje) {
        this.ukupnoOdrzavanje = ukupnoOdrzavanje;
    }

    public double getUkupnoPopravke() {
        return ukupnoPopravke;
    }

    public void setUkupnoPopravke(double ukupnoPopravke) {
        this.ukupnoPopravke = ukupnoPopravke;
    }

    public double getIznosVoznjiUziDio() {
        return iznosVoznjiUziDio;
    }

    public void setIznosVoznjiUziDio(double iznosVoznjiUziDio) {
        this.iznosVoznjiUziDio = iznosVoznjiUziDio;
    }

    public double getIznosVoznjiSiriDio() {
        return iznosVoznjiSiriDio;
    }

    public void setIznosVoznjiSiriDio(double iznosVoznjiSiriDio) {
        this.iznosVoznjiSiriDio = iznosVoznjiSiriDio;
    }

    /**
     * override metode toString za objekat klase DnevniIzvjestaj
     * @return string
     */
    @Override
    public String toString()
    {
        return "prihod: "+ukupanPrihod+" popust: "+ukupanPopust+" promocija: "+ukupnoPromocija +" popravke: "+ukupnoPopravke +" odrzavanje: "+ukupnoOdrzavanje+", iznos voznji uzi dio:"+iznosVoznjiUziDio+", iznos voznji siri dio: "+iznosVoznjiSiriDio;
    }
}
