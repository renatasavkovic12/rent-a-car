package com.example.projekat2024.Kontroleri;
import com.example.projekat2024.Main;
import com.example.projekat2024.PrevoznaSredstva.PrevoznoSredstvo;
import com.example.projekat2024.RadSaFajlovima.Konstante;
import com.example.projekat2024.Simulacija.Iznajmljivanje;
import com.example.projekat2024.Simulacija.ePJ2Kompanija;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * klasa koja sluzi za generisanje osnovnog GUI-a koji sluzi za prikaz simulacije kretanja vozila
 */
public class MainController {
    private static MainController instance;
    @FXML
    private  GridPane gridPane = new GridPane();
    @FXML
    private Pane[][] panes;
    @FXML
    private Label[][] labele;
    private static final ePJ2Kompanija kompanija = new ePJ2Kompanija();
    /**
     * FXML funkcija koja na klik dugmeta "Prikazi kvarove" prikazuje novi GUI sa svim kvarovima koji su se desili u toku simulacije
     */
    @FXML
    protected void onClickedKvarovi() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("kvarovi.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 700);
        stage.setTitle("PRIKAZ SVIH KVAROVA:");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * FXML funkcija koja na klik dugmeta "Prikazi vozila" prikazuje novi GUI sa informacijama o svim vozilima
     */
    @FXML
    protected void onClickedPrevoznaSredstva() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("prevoznaSredstva.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 700);
        stage.setTitle("PRIKAZ SVIH MOGUCIH VOZILA:");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * FXML funkcija koja na klik dugmeta "Rezultati poslovanja" prikazuje novi GUI sa rezultatima poslovanja
     */
    @FXML
    protected void onClickedRezultatiPoslovanja() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("rezultatiPoslovanja.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 700);
        stage.setTitle("PRIKAZ REZULTATA POSLOVANJA:");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * FXML funkcija koja na klik dugmeta "Vozila s najvise gubitaka" prikazuje novi GUI sa informacijama o vozilima s najvecim gubicima
     */
    @FXML
    protected void onClickedVozilaSNajvecimGubicima() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("deserijalizovaniPodaci.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 700);
        stage.setTitle("VOZILA KOJA SU DONIJELA NAJVISE GUBITAKA:");
        stage.setScene(scene);
        stage.show();
    }
    public static synchronized MainController getInstance() {
        return instance;
    }
    public MainController() {
        instance = this;
    }
    /**
     * FXML funkcija koja na klik dugmeta "Pokreni" pokrece izvrsavanje simulacije
     */
    @FXML
    protected void onClickedPokreniSimulaciju() {
        new Thread(() -> {
            try {
                kompanija.pokreniIznajmljivanja();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    @FXML
    public void initialize()
    {
        if (MainController.getInstance() == null) {
            MainController instance = new MainController();
            MainController.setInstance(instance);
        }
        panes = new Pane[Konstante.DIMENZIJE_MATRICE][Konstante.DIMENZIJE_MATRICE];
        labele = new Label[Konstante.DIMENZIJE_MATRICE][Konstante.DIMENZIJE_MATRICE];
        for(int i=0; i<Konstante.DIMENZIJE_MATRICE; i++)
        {
            for(int j=0; j<Konstante.DIMENZIJE_MATRICE; j++)
            {
                Pane pane = new Pane();
                pane.setStyle("-fx-border-color: black;");
                panes[i][j] = pane;
                Label labela = new Label();
                labele[i][j] = labela;
                pane.getChildren().addAll(labela);
                gridPane.add(pane,i,j);
            }
        }
    }
    public static synchronized void setInstance(MainController newInstance) {
        instance = newInstance;
    }
    /**
     * funkcija za dodavanje vozila na odredjenu poziciju na mapi
     * @param i koordinata na mapi po x osi
     * @param j koordinata na mapi po y osi
     * @param vozilo vozilo koje se dodaje na mapu
     * @param nivoBaterije novi baterije vozila koji se dodaje
     * @param iznajmljivanje iznajmljivanje za koje se vrsi prikaz kretanja vozila
     */
    public void dodajVoziloNaGrid(int i, int j, PrevoznoSredstvo vozilo, int nivoBaterije, Iznajmljivanje iznajmljivanje)
    {
        Platform.runLater(()->{
            Label labela = labele[i][j];
            Pane pane = panes[i][j];
            labela.setMinSize(45,40);
            String trenutniTekst = labela.getText();
            if (!trenutniTekst.isEmpty()) {
                labela.setText(trenutniTekst + "\n" + vozilo.identifikator + " " + nivoBaterije);
            } else {
                labela.setText(vozilo.identifikator + " " + nivoBaterije);
            }
            //labela.setText(vozilo.identifikator+" "+nivoBaterije);
            if(iznajmljivanje.kvar) {
                BackgroundFill backgroundFill = new BackgroundFill(Color.RED, null, null);
                Background background = new Background(backgroundFill);
                pane.setBackground(background);
            }
            else
            {
                BackgroundFill backgroundFill = new BackgroundFill(Color.GREEN, null, null);
                Background background = new Background(backgroundFill);
                pane.setBackground(background);
            }
        });
    }
    /**
     * funkcija sluzi za uklanjanje vozila sa odredjene pozicije na mapi
     * @param i koordinata po x osi na mapi
     * @param j koordinata po y osi na mapi
     */
    public void ukloniVoziloSGrida(int i, int j)
    {
        Platform.runLater(()->{
            Label labela = labele[i][j];
            Pane pane = panes[i][j];
            labela.setText("");
            pane.setBackground(null);
        });
    }
}