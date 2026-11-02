package com.example.projekat2024.PrevoznaSredstva;
import java.io.Serializable;
import java.util.Random;
/**
 * Klasa koja predstavlja ELEKTRICNI AUTOMOBIL i nasljedjuje klasu PREVOZNO SREDSTVO
 * @author Renata Savkovic
 * @version 1.0
 */
public class Automobil extends PrevoznoSredstvo implements Serializable {

    /**
     * informacija o datumu nabavke automobila
     */
    public String datumNabavke;
    /**
     * opis vozila
     */
    public String opis;
    /**
     * broj ljudi koje automobil prevozi
     */
    public int brojLjudi;
    public Automobil(String id, String proizvodjac, String model, String datumNabavke,int cijena, String opis)
    {
        super(id,proizvodjac,model,cijena);
        setVrstaVozila("automobil");
        setDatumNabavke(datumNabavke);
        setOpis(opis);
        Random rand = new Random();
        setBrojLjudi(rand.nextInt(1,5));
    }
    /**
     * funkcija getter() za atribut datumNabavke
     * @return datumNabavke;
     */
    public String getDatumNabavke() {
        return datumNabavke;
    }
    /**
     * funkcija setter() za atribut datumNabavke
     * @param datumNabavke predstavlja informaciju o datumu nabavke automobila
     */
    public void setDatumNabavke(String datumNabavke) {
        this.datumNabavke = datumNabavke;
    }
    /**
     * funkcija getter() za atribut opis
     * @return opis;
     */
    public String getOpis() {
        return opis;
    }
    /**
     * funkcija setter() za atribut opis
     * @param opis predstavlja dodatan opis automobila
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }
    /**
     * funkcija getter() za atribut brojLjudi
     * @return brojLjudi;
     */
    public int getBrojLjudi() {
        return brojLjudi;
    }
    /**
     * funkcija setter() za atribut brojLjudi
     * @param brojLjudi predstavlja informaciju o broju ljudi u automobilu
     */
    public void setBrojLjudi(int brojLjudi) {
        this.brojLjudi = brojLjudi;
    }
    /**
     * Override metode toString() koja pretvara informacije o klasi Automobil u String
     */
    @Override
    public String toString()
    {
        return super.toString() +", datum nabavke: "+datumNabavke+", dodatan opis: "+opis+"\n"+"Racuni za sva iznajmljivanja: "+this.racuniVozila+"\n";
    }
}
