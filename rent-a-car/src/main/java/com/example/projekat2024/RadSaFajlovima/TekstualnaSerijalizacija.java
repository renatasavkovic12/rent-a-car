package com.example.projekat2024.RadSaFajlovima;
import com.example.projekat2024.Izvjestaji.Racun;
import com.example.projekat2024.Main;
import com.example.projekat2024.PrevoznaSredstva.*;
import com.example.projekat2024.Simulacija.Iznajmljivanje;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Klasa koja predstavlja TEKSTUALNU SERIJALIZACIJU podataka u fajlove
 * @author Renata Savkovic
 * @version 1.0
 */
public class TekstualnaSerijalizacija {
    static Properties pro = MyProperties.getProperties();
    /**
     * lista svih kvarova u kompaniji
     */
    public static final List<Kvar> listaKvarova = new ArrayList<>();
    /**
     * lista svih mogucih ID-a u kompaniji
     */
    public static final List<String> idKorisnika = new ArrayList<>();
    /**
     * lista svih elektricnih automobila u simulaciji
     */
    public static final ArrayList<Automobil> listaA = new ArrayList<>();
    /**
     * lista svih elektricnih trotineta u simulaciji
     */
    public static final ArrayList<Trotinet> listaT = new ArrayList<>();
    /**
     * lista svih elektricnih bicikala u simulaciji
     */
    public static final ArrayList<Bicikl> listaB = new ArrayList<>();
    /**
     * funkacija koja cita sve automobile iz fajla s vozilima
     * @param fajl fajl iz kojeg se citaju automobili
     * @return listaA;
     */
    public static List<Automobil> procitajAutomobile(String fajl) {
        listaA.clear();
        Set<String> jedinstveniIDAuta = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
            String linija;
            while ((linija = reader.readLine()) != null) {
                String[] dijeloviLinije = linija.split(",");
                if (dijeloviLinije.length != 9) {
                    System.out.println("Neispravna linija ili format podataka: " + linija);
                    continue;
                }
                String id = dijeloviLinije[0];
                if (jedinstveniIDAuta.contains(id)) {
                    System.out.println("Duplikat ID-a pronađen: " + id);
                    continue;
                }
                if (dijeloviLinije[0].startsWith("A") && dijeloviLinije[8].equalsIgnoreCase("automobil")) {
                    try {
                        String proizvodjac = dijeloviLinije[1];
                        String model = dijeloviLinije[2];
                        String datumNabavke = dijeloviLinije[3];
                        int cijena = Integer.parseInt(dijeloviLinije[4]);
                        String opis = dijeloviLinije[7];
                        listaA.add(new Automobil(id, proizvodjac, model, datumNabavke, cijena, opis));
                        jedinstveniIDAuta.add(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Neispravan format brojeva u liniji: " + linija);
                    }
                }
            }
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
        }
        return listaA;
    }
    /**
     * funkacija koja cita sve trotinete iz fajla s vozilima
     * @param fajl fajl iz kojeg se citaju trotineti
     * @return listaT;
     */
    public static List<Trotinet> procitajTrotinete(String fajl) {
        listaT.clear();
        Set<String> jedinstveniIDTrot = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
            String linija;
            while ((linija = reader.readLine()) != null) {
                String[] dijeloviLinije = linija.split(",");
                if (dijeloviLinije.length != 9) {
                    System.out.println("Neispravna linija ili format podataka: " + linija);
                    continue;
                }
                String id = dijeloviLinije[0];
                if (jedinstveniIDTrot.contains(id)) {
                    System.out.println("Duplikat ID-a pronađen: " + id);
                    continue;
                }
                if (dijeloviLinije[0].startsWith("T") && dijeloviLinije[8].equalsIgnoreCase("trotinet")) {
                    try {
                        String proizvodjac = dijeloviLinije[1];
                        String model = dijeloviLinije[2];
                        int cijena = Integer.parseInt(dijeloviLinije[4]);
                        int maxBrzina = Integer.parseInt(dijeloviLinije[6]);
                        listaT.add(new Trotinet(id, proizvodjac, model, cijena, maxBrzina));
                        jedinstveniIDTrot.add(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Neispravan format brojeva za liniju: " + linija);
                    }
                }
            }
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
        }
        return listaT;
    }
    /**
     * funkacija koja cita sve bicikle iz fajla s vozilima
     * @param fajl fajl iz kojeg se citaju bicikli
     * @return listaB;
     */
    public static List<Bicikl> procitajBicikle(String fajl) {
        listaB.clear();
        Set<String> jedinstveniIDBicikl = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
            String linija;
            while ((linija = reader.readLine()) != null) {
                String[] dijeloviLinije = linija.split(",");
                if (dijeloviLinije.length != 9) {
                    System.out.println("Neispravna linija ili format podataka: " + linija);
                    continue;
                }
                String id = dijeloviLinije[0];
                if (jedinstveniIDBicikl.contains(id)) {
                    System.out.println("Duplikat ID-a pronađen: " + id);
                    continue;
                }
                if (dijeloviLinije[0].startsWith("B") && dijeloviLinije[8].equalsIgnoreCase("bicikl")) {
                    try {
                        String proizvodjac = dijeloviLinije[1];
                        String model = dijeloviLinije[2];
                        int cijena = Integer.parseInt(dijeloviLinije[4]);
                        int domet = Integer.parseInt(dijeloviLinije[5]);
                        listaB.add(new Bicikl(id, proizvodjac, model, cijena, domet));
                        jedinstveniIDBicikl.add(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Neispravan format brojeva za liniju: " + linija);
                    }
                }
            }
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
        }
        return listaB;
    }
    /**
     * funkacija koja cita sva iznajmljivanja iz fajla s vozilima
     * @param fajl fajl iz kojeg se citaju iznajmljivanja
     * @return listaIznajmljivanja;
     */
    public static ArrayList<Iznajmljivanje> procitajIznajmljivanjaIzFajla(String fajl, List<PrevoznoSredstvo> svaVozila) {
        ArrayList<Iznajmljivanje> listaIznajmljivanja = new ArrayList<>();
        Set<String> obradjenaIznajmljivanja = new HashSet<>();
        boolean pronadjenoVozilo = false;
        Pattern pattern = Pattern.compile("(?<=^|,)(\"[^\"]*\"|[^,\"]*)(?=,|$)");
        try (BufferedReader reader = new BufferedReader(new FileReader(fajl))) {
            String linija;
            while ((linija = reader.readLine()) != null) {
                ArrayList<String> dijeloviLinije = new ArrayList<>();
                Matcher matcher = pattern.matcher(linija);
                while (matcher.find()) {
                    String match = matcher.group(1);
                    if (match.startsWith("\"") && match.endsWith("\"")) {
                        match = match.substring(1, match.length() - 1);
                    }
                    dijeloviLinije.add(match);
                }
                if (dijeloviLinije.size() < 8) {
                    System.out.println("Neispravna linija, nedovoljno parametara: " + linija);
                    continue;
                }
                if (linija.matches("^[0-9].*")) {
                    try {
                        String datumVrijeme = dijeloviLinije.get(0);
                        String imeKorisnika = dijeloviLinije.get(1);
                        String idVozila = dijeloviLinije.get(2);
                        String lokacijaPreuzimanja = dijeloviLinije.get(3).equals("0") ? "0,0" : dijeloviLinije.get(3);
                        String lokacijaPovratka = dijeloviLinije.get(4).equals("0") ? "0,0" : dijeloviLinije.get(4);
                        int trajanje = Integer.parseInt(dijeloviLinije.get(5));
                        String kvar = dijeloviLinije.get(6);
                        String promocija = dijeloviLinije.get(7);
                        String kljuc = idVozila + "-" + datumVrijeme;
                        if (obradjenaIznajmljivanja.contains(kljuc)) {
                            System.out.println("Vozilo:" + idVozila + "je iznajmljeno na datum: " + datumVrijeme + " vise puta, tako da se iznajmljivanje ignorise.");
                            continue;
                        }
                        if (!isValidLocation(lokacijaPreuzimanja) || !isValidLocation(lokacijaPovratka)) {
                            System.out.println("Neispravan format ili opseg lokacija: " + linija);
                            continue;
                        }
                        for (PrevoznoSredstvo p : svaVozila) {
                            if (idVozila.equalsIgnoreCase(p.identifikator)) {
                                listaIznajmljivanja.add(new Iznajmljivanje(datumVrijeme, imeKorisnika, p, lokacijaPreuzimanja, lokacijaPovratka, trajanje, kvar, promocija));
                                obradjenaIznajmljivanja.add(kljuc);
                                idKorisnika.add(imeKorisnika);
                                pronadjenoVozilo = true;
                                System.out.println("Pronadjeno vozilo s ID-em: " + idVozila + " i stvaljeno je u listu iznajmljivanja.");
                                break;
                            }
                        }
                        if (!pronadjenoVozilo) {
                            System.out.println("Vozilo sa ID-em: " + idVozila + " ne postoji u kompaniji.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Neispravan format brojeva u liniji: " + linija);
                    }
                } else {
                    System.out.println("Ova linija teksta ne sadrzi podatke o iznajmljivanju: " + linija);
                }
            }
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (Iznajmljivanje iznajmljivanje : listaIznajmljivanja) {
            System.out.println("Kvar: " + iznajmljivanje.kvar + "\n" + " za iznajmljivanje: " + iznajmljivanje);
        }
        return listaIznajmljivanja;
    }
    /**
     * pomocna funkacija koja sluzi za provjeru da li je lokacija validna (u opsegu)
     * @param location informacije o lokaciji
     * @return boolean;
     */
    private static boolean isValidLocation(String location) {
        String[] parts = location.split(",");
        if (parts.length != 2) {
            return false;
        }
        try {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            return x >= 0 && x <= 19 && y >= 0 && y <= 19;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * funkacija koja upisuje racun u fajl s racunima
     * @param racun informacije o racunu koji se upisuje u fajl
     */
    public static void upisiRacunUFajl(Racun racun, Iznajmljivanje iznajmljivanje) {
        if (racun.korisnik != null) {
            File folder = new File(pro.getProperty("RacuniDirectory"), "korisnik." + racun.korisnik.idKorisnika);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File fajl = new File(folder, racun.idRacuna + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(fajl)))) {
                writer.write("Racun: " + racun.idRacuna);
                writer.write("\n-------------------------------------------------------------");
                writer.write("\n");
                writer.write("Korisnik: " + racun.korisnik.idKorisnika + ", broj vozacke:"+ racun.korisnik.brojVozacke+", dokument:"+(racun.korisnik.licnaKarta!=null?racun.korisnik.licnaKarta:racun.korisnik.pasos));
                writer.write("\n-------------------------------------------------------------");
                writer.write("\n");
                writer.write("Iznajmljivanje: " + iznajmljivanje.datumVrijeme + iznajmljivanje.vozilo);
                writer.write("Osnovna cijena: " + racun.osnovnaCijena);
                writer.write("\n-------------------------------------------------------------");
                writer.write("\n");
                writer.write("Udaljenost: " + racun.udaljenost);
                writer.write("\n-------------------------------------------------------------");
                writer.write("\n");
                writer.write("Popusti: " + racun.popust);
                writer.write("\n-------------------------------------------------------------");
                writer.write("\n");
                writer.write("Promocije: " + racun.promocije);
                writer.write("\n-------------------------------------------------------------");
                writer.write("\n-------------------------------------------------------------");
                writer.write("\n");
                writer.write("Ukupan iznos za placanje: " + racun.ukupnaCijenaZaPlacanje);
                if (iznajmljivanje.kvar) {
                    writer.write("\n-------------------------------------------------------------");
                    writer.write("\n");
                    writer.write("KORISNIK OSLOBODJEN PLACANJA ZBOG KVARA !!!");
                }
            } catch (IOException e) {
                Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
            }
        }
    }
    /**
     * funkcija koja upisuje kvar u fajl sa kvarovima
     * @param fajl fajl u koji se upisuju podaci
     * @param kvar informacije o kvaru koji se upisuje
     */
    public static void upisiKvarUFajl(String fajl, Kvar kvar) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fajl,true))) {
            bw.write(kvar.vrstaVozila + "," + kvar.idVozila + "," + kvar.datumVrijemeKvara + "," + kvar.opisKvara+"\n");
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }
    /**
     * funkcija za citanje liste kvarova iz fajla
     * @param fajl fajl iz kojeg se citaju podaci
     * @return vraca listu kvarova
     */
    public static List<Kvar> procitajKvarove(String fajl) {
        try (BufferedReader br = new BufferedReader(new FileReader(fajl))) {
            String linija;
            while ((linija = br.readLine()) != null) {
                String[] dijeloviLinije = linija.split(",");
                String vrstaVozila = dijeloviLinije[0];
                String idVozila = dijeloviLinije[1];
                String datumVrijeme = dijeloviLinije[2];
                String opisKvara = dijeloviLinije[3];
                Kvar kvar = new Kvar(vrstaVozila, idVozila, datumVrijeme, opisKvara);
                listaKvarova.add(kvar);
            }
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, e.fillInStackTrace().toString());
        }
        return listaKvarova;
    }
}