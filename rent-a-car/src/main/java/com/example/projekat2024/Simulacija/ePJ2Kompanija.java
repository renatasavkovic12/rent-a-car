package com.example.projekat2024.Simulacija;
import com.example.projekat2024.Izvjestaji.DnevniIzvjestaj;
import com.example.projekat2024.Izvjestaji.Racun;
import com.example.projekat2024.Izvjestaji.SumarniIzvjestaj;
import com.example.projekat2024.Main;
import com.example.projekat2024.PrevoznaSredstva.*;
import com.example.projekat2024.RadSaFajlovima.BinarnaSerijalizacija;
import com.example.projekat2024.RadSaFajlovima.Konstante;
import com.example.projekat2024.RadSaFajlovima.MyProperties;
import com.example.projekat2024.RadSaFajlovima.TekstualnaSerijalizacija;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;
/**
 * Klasa koja predstavlja kompaniju za iznajmljivanje elektricnih vozila
 * @author Renata Savkovic
 * @version 1.0
 */
public class ePJ2Kompanija {
    Properties pro = MyProperties.getProperties();
    /**
     * lista svih izdanih racuna od strane kompanije
     */
    public static List<Racun> listaRacuna = new ArrayList<>();
    /**
     * informacije o svim automobilima kojim kompanija raspolaze
     */
    public static List<Automobil> automobili;
    /**
     * informacije o svim automobilima kojim kompanija raspolaze
     */
    public static List<Kvar> listaKvarova = new ArrayList<>();
    /**
     * informacije o svim trotinetima kojim kompanija raspolaze
     */
    public static List<Trotinet> trotineti;
    /**
     * informacije o svim biciklima kojim kopmanija raspolaze
     */
    public static List<Bicikl> bicikli;
    /**
     * informacije o svim iznajmljivanjima u posmatranoj kompaniji
     */
    public static List<Iznajmljivanje> iznajmljivanje;
    /**
     * lista svih vozila dostupnih u kompaniji
     */
    public List<PrevoznoSredstvo> svaVozila = new ArrayList<>();
    /**
     * informacije o svim dnevnim izvjestajima kompanije
     */
    public static List<DnevniIzvjestaj> dnevniIzvjestaji = new ArrayList<>();
    /**
     * sumarni izvjestaj kompanije
     */
    public static SumarniIzvjestaj sumarniIzvjestaj;

    public ePJ2Kompanija()
    {
        automobili = TekstualnaSerijalizacija.procitajAutomobile(pro.getProperty("FajlVozila"));
        svaVozila.addAll(automobili);
        trotineti = TekstualnaSerijalizacija.procitajTrotinete(pro.getProperty("FajlVozila"));
        svaVozila.addAll(trotineti);
        bicikli = TekstualnaSerijalizacija.procitajBicikle(pro.getProperty("FajlVozila"));
        svaVozila.addAll(bicikli);
        iznajmljivanje = TekstualnaSerijalizacija.procitajIznajmljivanjaIzFajla(pro.getProperty("FajlIznajmljivanja"), svaVozila);
        sortirajPoDatumu(iznajmljivanje);
    }
    /**
     * pomocna funkcija koja sortira iznajmljivanja po datumu
     * @param iznajmljivanje lista iznajmljivanja
     */
    public void sortirajPoDatumu(List<Iznajmljivanje> iznajmljivanje)
    {
        iznajmljivanje.sort(Comparator.comparing(Iznajmljivanje::getDatumVrijeme));
    }
    /**
     * funkcija koja pokrece sva iznajmljivanja na nivou kompanije
     */
    public void pokreniIznajmljivanja() {
        Map<String, Map<String, List<Iznajmljivanje>>> iznajmljivanjaPoDatumuIVremenu = iznajmljivanje.stream()
                .collect(Collectors.groupingBy(
                        i -> Konstante.DATE_FORMAT.format(i.getDatumVrijeme()),
                        Collectors.groupingBy(
                                i -> Konstante.DATE_TIME_FORMAT.format(i.getDatumVrijeme())
                        )
                ));
        Map<String, Map<String, List<Iznajmljivanje>>> sortedIznajmljivanjaPoDatumuIVremenu = new TreeMap<>(
                (date1, date2) -> {
                    try {
                        return Konstante.DATE_FORMAT.parse(date1).compareTo(Konstante.DATE_FORMAT.parse(date2));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        for (Map.Entry<String, Map<String, List<Iznajmljivanje>>> entry : iznajmljivanjaPoDatumuIVremenu.entrySet()) {
            Map<String, List<Iznajmljivanje>> sortedTimes = new TreeMap<>(
                    (time1, time2) -> {
                        try {
                            return Konstante.DATE_TIME_FORMAT.parse(time1).compareTo(Konstante.DATE_TIME_FORMAT.parse(time2));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            sortedTimes.putAll(entry.getValue());
            sortedIznajmljivanjaPoDatumuIVremenu.put(entry.getKey(), sortedTimes);
        }
        for (String datum : sortedIznajmljivanjaPoDatumuIVremenu.keySet()) {
            Map<String, List<Iznajmljivanje>> iznajmljivanjaPoVremenu = sortedIznajmljivanjaPoDatumuIVremenu.get(datum);
            for (String vrijeme : iznajmljivanjaPoVremenu.keySet()) {
                List<Iznajmljivanje> trenutnaIznajmljivanja = iznajmljivanjaPoVremenu.get(vrijeme);
                ExecutorService executorService = Executors.newFixedThreadPool(10);
                trenutnaIznajmljivanja.forEach(i -> {
                    executorService.execute(() -> {
                        try {
                            if (!i.isAlive()) {
                                i.start();
                            }
                            i.join();
                        } catch (InterruptedException e) {
                            Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
                        }
                    });
                });
                executorService.shutdown();
                try {
                    executorService.awaitTermination(1, TimeUnit.HOURS);
                } catch (InterruptedException e) {
                    Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
                }
                try {
                    System.out.println("++++++++++PAUZA OD 5 SEKUNDI IZMEDJU TERMINA.++++++++++++");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
                }
            }
            DnevniIzvjestaj izvjestaj = kreirajDnevniIzvjestaj(datum);
            dnevniIzvjestaji.add(izvjestaj);
        }
        sumarniIzvjestaj = kreirajSumarniIzvjestaj();
        pronadjiBicikl(bicikli);
        pronadjiTrotinet(trotineti);
        pronadjiAutomobil(automobili);
        System.out.println("-----------KRAJ SIMULACIJE!!!------------");
    }
    /**
     * funkcija koja kreira dnevni izvjestaj na osnovu datuma
     * @param datum informacija o datumu izvjestaja
     * @return izvjestaj
     */
    public DnevniIzvjestaj kreirajDnevniIzvjestaj(String datum)
    {
        DnevniIzvjestaj izvjestaj = new DnevniIzvjestaj();
        for(Iznajmljivanje i: iznajmljivanje)
        {
            if(Konstante.DATE_FORMAT.format(i.datumVrijeme).equals(datum) && i.racunZaIznajmljivanje != null)
            {
                izvjestaj.setDatumIzvjestaja(datum);
                izvjestaj.ukupanPrihod += i.racunZaIznajmljivanje.ukupnaCijenaZaPlacanje;
                izvjestaj.ukupanPopust += i.racunZaIznajmljivanje.popust;
                izvjestaj.ukupnoPromocija += i.racunZaIznajmljivanje.promocije;
                izvjestaj.ukupnoOdrzavanje = izvjestaj.ukupanPrihod * 0.2;
                izvjestaj.iznosVoznjiUziDio += i.racunZaIznajmljivanje.iznosUziDio;
                izvjestaj.iznosVoznjiSiriDio += i.racunZaIznajmljivanje.iznosSiriDio;
                if(i.vozilo instanceof Automobil)
                {
                    izvjestaj.ukupnoPopravke = 0.07*i.vozilo.cijenaNabavke;
                }
                else if(i.vozilo instanceof Bicikl)
                {
                    izvjestaj.ukupnoPopravke = 0.04*i.vozilo.cijenaNabavke;
                }
                else if(i.vozilo instanceof Trotinet)
                {
                    izvjestaj.ukupnoPopravke = 0.02*i.vozilo.cijenaNabavke;
                }
            }
        }
        return izvjestaj;
    }
    /**
     * funkcija koja kreira sumarni izvjestaj na osnovu datuma
     * @return izvjestaj
     */
    public SumarniIzvjestaj kreirajSumarniIzvjestaj()
    {
        SumarniIzvjestaj izvjestaj = new SumarniIzvjestaj();
        for(DnevniIzvjestaj di: dnevniIzvjestaji)
        {
            izvjestaj.ukupanPrihod += di.ukupanPrihod;
            izvjestaj.ukupanPopust += di.ukupanPopust;
            izvjestaj.ukupnoPromocija += di.ukupnoPromocija;
            izvjestaj.ukupnoOdrzavanje += di.ukupnoOdrzavanje;
            izvjestaj.ukupnoPopravke += di.ukupnoPopravke;
            izvjestaj.iznosVoznjiSiriDio += di.iznosVoznjiSiriDio;
            izvjestaj.iznosVoznjiUziDio += di.iznosVoznjiUziDio;
            izvjestaj.ukupniTroskoviKompanije = izvjestaj.ukupanPrihod*20/100;
            izvjestaj.ukupanPorezKompanije = (izvjestaj.ukupanPrihod - izvjestaj.ukupnoOdrzavanje - izvjestaj.ukupnoPopravke - izvjestaj.ukupniTroskoviKompanije)*10/100;
        }
        return izvjestaj;
    }

    /**
     * funkcija koja pronalazi automobil sa najvise gubitaka
     * @param automobili lista automobila u kompaniji
     */
    public void pronadjiAutomobil(List<Automobil> automobili)
    {
        if (automobili == null || automobili.isEmpty()) {
            System.out.println("Lista automobila je prazna ili null.");
            return;
        }
        Automobil automobilSaNajviseKvarova = automobili.get(0);
        boolean imaKvarova = automobilSaNajviseKvarova.gubiciVozila > 0;
        for (Automobil auto : automobili) {
            if (auto.gubiciVozila > automobilSaNajviseKvarova.gubiciVozila) {
                automobilSaNajviseKvarova = auto;
            }
            if (auto.gubiciVozila > 0) {
                imaKvarova = true;
                //System.out.println("Gubici automobila su: "+auto.gubiciVozila);
            }
        }
        if (!imaKvarova) {
            System.out.println("Nijedan automobil nije imao kvar.");
        } else {
            BinarnaSerijalizacija.serijalizujVozilaSNajvecimGubicima(automobilSaNajviseKvarova, pro.getProperty("FajlAKvar"));
        }
    }
    /**
     * funkcija koja pronalazi bicikl sa najvise gubitaka
     * @param bicikli lista svih bicikala u kompaniji
     */
    public void pronadjiBicikl(List<Bicikl> bicikli) {
        if (bicikli == null || bicikli.isEmpty()) {
            System.out.println("Lista bicikala je prazna ili null.");
            return;
        }
        Bicikl biciklSaNajviseKvarova = bicikli.get(0);
        boolean imaKvarova = biciklSaNajviseKvarova.gubiciVozila > 0;
        for (Bicikl bike : bicikli) {
            if (bike.gubiciVozila> biciklSaNajviseKvarova.gubiciVozila) {
                biciklSaNajviseKvarova = bike;
            }
            if (bike.gubiciVozila > 0) {
                //System.out.println("Gubici bicikla su: "+bike.gubiciVozila);
                imaKvarova = true;
            }
        }
        if (!imaKvarova) {
            System.out.println("Nijedan bicikl nije imao kvar.");
        } else {
            BinarnaSerijalizacija.serijalizujVozilaSNajvecimGubicima(biciklSaNajviseKvarova, pro.getProperty("FajlBKvar"));
        }
    }

    /**
     * funkcija koja pronalazi trotinet sa najvise gubitaka
     * @param trotineti lista svih trotineta u kompaniji
     */
    public void pronadjiTrotinet(List<Trotinet> trotineti) {
        if (trotineti == null || trotineti.isEmpty()) {
            System.out.println("Lista trotineta je prazna ili null.");
            return;
        }
        Trotinet trotinetSaNajviseKvarova = trotineti.get(0);
        boolean imaKvarova = trotinetSaNajviseKvarova.gubiciVozila > 0;
        for (Trotinet scot : trotineti) {
            if (scot.gubiciVozila > trotinetSaNajviseKvarova.gubiciVozila) {
                trotinetSaNajviseKvarova = scot;
            }
            if (scot.gubiciVozila > 0) {
                //System.out.println("Gubici trotineta su: "+scot.gubiciVozila);
                imaKvarova = true;
            }
        }
        if (!imaKvarova) {
            System.out.println("Nijedan trotinet nije imao kvar.");
        } else {
            BinarnaSerijalizacija.serijalizujVozilaSNajvecimGubicima(trotinetSaNajviseKvarova, pro.getProperty("fajlTKvar"));
        }
    }
}
