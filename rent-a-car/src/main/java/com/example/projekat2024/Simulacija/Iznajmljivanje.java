package com.example.projekat2024.Simulacija;
import com.example.projekat2024.Izvjestaji.Racun;
import com.example.projekat2024.Kontroleri.MainController;
import com.example.projekat2024.Mapa.Koordinate;
import com.example.projekat2024.PrevoznaSredstva.Kvar;
import com.example.projekat2024.PrevoznaSredstva.PrevoznoSredstvo;
import com.example.projekat2024.RadSaFajlovima.Konstante;
import com.example.projekat2024.RadSaFajlovima.MyProperties;
import com.example.projekat2024.RadSaFajlovima.TekstualnaSerijalizacija;
import javafx.application.Platform;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
/**
 * Klasa koja predstavlja IZNAJMLJIVANJE vozila u kompaniji
 * @author Renata Savkovic
 * @version 1.0
 */
public class Iznajmljivanje extends Thread implements Serializable {
    Properties pro = MyProperties.getProperties();
    private final Random rand = new Random();
    /**
     * informacija o datumu i vremenu iznajmljivanja
     */
    public Date datumVrijeme;
    /**
     * informacija o korisniku koji vrsi iznajmljivanje
     */
    public String imeKorisnika;
    /**
     * informacija o lokaciji na kojoj se preuzima vozilo (pocetna tacka koristenja vozila)
     */
    public String lokacijaPreuzimanja;
    /**
     * informacija o lokaciji na koju se vozilo vraca nakon koristenja (krajnja tacka koristenja vozila)
     */
    public String lokacijaVracanja;
    /**
     * informacija o trajanju iznajmljivanja
     */
    public long trajanjeKoristenja;
    /**
     * informacija o tome da li se desio kvar tokom iznajmljivanja
     */
    public boolean kvar;
    /**
     * informacija o tome da li postoji neka promocija za posmatrano iznajmljivanje
     */
    public boolean promocija;
    /**
     * informacije o vozilu koje se iznajmljuje
     */
    public PrevoznoSredstvo vozilo;
    /**
     * informacije sa racuna za placanje posmatranog iznamljivanja
     */
    public Racun racunZaIznajmljivanje;
    public Date getDatumVrijeme() {
        return datumVrijeme;
    }
    private void setDatumVrijeme(Date datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }
    private String getImeKorisnika() {
        return imeKorisnika;
    }
    private void setImeKorisnika(String imeKorisnika) {
        this.imeKorisnika = imeKorisnika;
    }
    private String getLokacijaPreuzimanja() {
        return lokacijaPreuzimanja;
    }
    private void setLokacijaPreuzimanja(String lokacijaPreuzimanja) {
        this.lokacijaPreuzimanja = lokacijaPreuzimanja;
    }
    private String getLokacijaVracanja() {
        return lokacijaVracanja;
    }
    private void setLokacijaVracanja(String lokacijaVracanja) {
        this.lokacijaVracanja = lokacijaVracanja;
    }
    private long getTrajanjeKoristenja() {
        return trajanjeKoristenja;
    }
    private void setTrajanjeKoristenja(int trajanjeKoristenja) {
        this.trajanjeKoristenja = trajanjeKoristenja;
    }
    public boolean isKvar() {
        return kvar;
    }
    public void setKvar(boolean kvar) {
        this.kvar = kvar;
    }
    private boolean isPromocija() {
        return promocija;
    }
    public void setPromocija(boolean promocija) {
        this.promocija = promocija;
    }
    public Iznajmljivanje(String datumIVrijeme,String idKorisnika,PrevoznoSredstvo vozilo, String pocetak, String kraj, int trajanje, String kvar, String promocija) throws ParseException {
        this.datumVrijeme = Konstante.DATE_TIME_FORMAT.parse(datumIVrijeme);
        setImeKorisnika(idKorisnika);
        setLokacijaPreuzimanja(pocetak);
        setLokacijaVracanja(kraj);
        setTrajanjeKoristenja(trajanje);
        this.vozilo = vozilo;
        setPromocija(promocija.equalsIgnoreCase("da"));
        if(kvar.equalsIgnoreCase("da"))
        {
            this.setKvar(true);
        }
        else
        {
            this.setKvar(false);
        }
    }

    /**
     * pomocna funkcija koja sluzi za definisanje datuma i vremena kada se desio kvar
     * @return datum i vrijeme
     */
    private Date definisiDatumIVrijeme() throws ParseException {
        int vrijeme = rand.nextInt((int)trajanjeKoristenja);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.datumVrijeme);
        calendar.add(Calendar.SECOND,vrijeme);
        return calendar.getTime();
    }

    /**
     * funkcija koja racuna putanju za iznajmljivanje
     * @param pocetak pocetna lokacija iznajmljivanja
     * @param kraj krajnja lokacija iznajmljivanja
     * @return niz Koordinata koji cine putanju
     */
    private static List<Koordinate> izracunajPutanju(Koordinate pocetak, Koordinate kraj) {
        List<Koordinate> putanja = new ArrayList<>();
        int x = pocetak.i;
        while (x != kraj.i) {
            putanja.add(new Koordinate(x, pocetak.j));
            if (x < kraj.i) {
                x++;
            } else {
                x--;
            }
        }
        int y = pocetak.j;
        while (y != kraj.j) {
            putanja.add(new Koordinate(kraj.i, y));
            if (y < kraj.j) {
                y++;
            } else {
                y--;
            }
        }
        putanja.add(new Koordinate(kraj.i, kraj.j));
        return putanja;
    }
    @Override
    public void run()
    {
        System.out.println("Definisem putanju za iznajmljivanje: "+this);
        String[] koordinatePocetka = this.lokacijaPreuzimanja.split(",");
        Koordinate pocetak = new Koordinate(Integer.parseInt(koordinatePocetka[0]),Integer.parseInt(koordinatePocetka[1]));
        String[] koordinateKraja = this.lokacijaVracanja.split(",");
        Koordinate kraj = new Koordinate(Integer.parseInt(koordinateKraja[0]),Integer.parseInt(koordinateKraja[1]));
        List<Koordinate> putanja = izracunajPutanju(pocetak,kraj);
        Koordinate trenutnaPozicijaVozila;
        if(putanja != null) {
            if(this.kvar)
            {
                String datumVrijeme = null;
                try {
                    datumVrijeme = String.valueOf(definisiDatumIVrijeme());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Kvar kvar1 = new Kvar(this.vozilo.identifikator, datumVrijeme);
                this.vozilo.kvar = kvar1;
                synchronized (ePJ2Kompanija.listaKvarova) {
                    ePJ2Kompanija.listaKvarova.add(kvar1);
                }
                TekstualnaSerijalizacija.upisiKvarUFajl(pro.getProperty("FajlKvarovi"), kvar1);
                System.out.println("DESIO SE KVAR NA VOZILU: "+this.vozilo.identifikator+" !!! ");
            }
            for(int i=0; i<putanja.size()-1; i++) {
                if (vozilo.trenutniNivoBaterije <= 0)
                {
                    vozilo.punjenjeBaterije();
                }
                System.out.println("Vozilo:" + this.vozilo.identifikator+" se nalazi na: "+i+" poziciji. Nivo baterije je: "+this.vozilo.trenutniNivoBaterije);
                trenutnaPozicijaVozila = putanja.get(i);
                int trenutnaI = trenutnaPozicijaVozila.i;
                int trenutnaJ = trenutnaPozicijaVozila.j;
                int finalI = i;
                Koordinate sledecaPozicija = putanja.get(i + 1);
                this.vozilo.trenutnaPozicijaVozila = trenutnaPozicijaVozila;
                synchronized (this) {
                    Platform.runLater(() -> {
                        MainController.getInstance().ukloniVoziloSGrida(trenutnaI, trenutnaJ);
                        MainController.getInstance().dodajVoziloNaGrid(sledecaPozicija.i, sledecaPozicija.j, this.vozilo, this.vozilo.trenutniNivoBaterije,this);
                    });
                    try {
                        Thread.sleep(trajanjeKoristenja/putanja.size()*1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                this.vozilo.praznjenjeBaterije();
                Koordinate poslednjaPozicija = putanja.get(putanja.size() - 1);
                Platform.runLater(() -> {
                    MainController.getInstance().ukloniVoziloSGrida(poslednjaPozicija.i, poslednjaPozicija.j);
                });
            }
            racunZaIznajmljivanje = new Racun(this);
            this.vozilo.gubiciVozila+=racunZaIznajmljivanje.gubitak;
            synchronized (this.vozilo.racuniVozila) {
                this.vozilo.racuniVozila.add(racunZaIznajmljivanje);
                ePJ2Kompanija.listaRacuna.add(racunZaIznajmljivanje);
            }
            TekstualnaSerijalizacija.upisiRacunUFajl(racunZaIznajmljivanje,this);
        }
    }
    /**
     * Override metode toString() koja pretvara informacije o klasi Iznajmljivanje u String
     */
    @Override
    public String toString()
    {
        return datumVrijeme+" "+imeKorisnika+" "+vozilo+" "+lokacijaPreuzimanja+" "+lokacijaVracanja+" "+trajanjeKoristenja+" "+kvar+" "+promocija+"\n";
    }
}
