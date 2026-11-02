package com.example.projekat2024.Kontroleri;
import com.example.projekat2024.Izvjestaji.DnevniIzvjestaj;
import com.example.projekat2024.Izvjestaji.Racun;
import com.example.projekat2024.RadSaFajlovima.Konstante;
import com.example.projekat2024.Simulacija.ePJ2Kompanija;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
/**
 * @author Renata Savkovic
 * @version 1.0
 * klasa dnevniIzvjestajController
 */
public class dnevniIzvjestajController implements Initializable {
    @FXML
    private TextArea taRacuni = new TextArea();
    @FXML
    private Label labelDatum = new Label();
    @FXML
    private TableView<DnevniIzvjestaj> dnevniIzvjestajTabela;
    @FXML
    private TableColumn<DnevniIzvjestaj, Integer> iznajmljivanje;
    @FXML
    private TableColumn<DnevniIzvjestaj, Integer> ukupanPrihod;
    @FXML
    private TableColumn<DnevniIzvjestaj, Integer> ukupanPopust;
    @FXML
    private TableColumn<DnevniIzvjestaj, Integer> ukupnoPromocija;
    @FXML
    private TableColumn<DnevniIzvjestaj, Integer> iznosVoznjeUziDio;
    @FXML
    private TableColumn<DnevniIzvjestaj, Integer> ukupnoOdrzavanje;
    @FXML
    private TableColumn<DnevniIzvjestaj, Integer> popravkeKvarova;
    @FXML
    private TableColumn<DnevniIzvjestaj,Integer> iznosVoznjeSiriDio;
    /**
     * Override funkcije initialize() koja inicijalizuje kontroler nakon što se korisnički interfejs (GUI) učita.
     * Postavlja svojstva za kolone tabele i generiše tabelu kvarova.
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        iznajmljivanje.setCellValueFactory(new PropertyValueFactory<>("datumIzvjestaja"));
        ukupanPopust.setCellValueFactory(new PropertyValueFactory<>("ukupanPopust"));
        ukupanPrihod.setCellValueFactory(new PropertyValueFactory<>("ukupanPrihod"));
        ukupnoPromocija.setCellValueFactory(new PropertyValueFactory<>("ukupnoPromocija"));
        iznosVoznjeUziDio.setCellValueFactory(new PropertyValueFactory<>("iznosVoznjiUziDio"));
        ukupnoOdrzavanje.setCellValueFactory(new PropertyValueFactory<>("ukupnoOdrzavanje"));
        popravkeKvarova.setCellValueFactory(new PropertyValueFactory<>("ukupnoPopravke"));
        iznosVoznjeSiriDio.setCellValueFactory(new PropertyValueFactory<>("iznosVoznjiSiriDio"));
    }
    public void setSelektovaniDatum(String selektovaniDatum) {
        labelDatum.setText(selektovaniDatum);
        List<Racun> filtriraniRacuni = ePJ2Kompanija.listaRacuna.stream()
                .filter(racun -> {
                    String datumRacuna;
                    datumRacuna = Konstante.DATE_FORMAT.format(racun.datumVrijeme);
                    return datumRacuna.equals(selektovaniDatum);
                }).toList();
        taRacuni.setText(filtriraniRacuni.toString());
        List<DnevniIzvjestaj> filtriraniIzvjestaji = ePJ2Kompanija.dnevniIzvjestaji.stream().filter(izvjestaj -> {
            try {
                return izvjestaj.getDatumIzvjestaja() != null && Konstante.DATE_FORMAT.format(Konstante.DATE_FORMAT.parse(izvjestaj.getDatumIzvjestaja())).equals(selektovaniDatum);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        dnevniIzvjestajTabela.getItems().setAll(filtriraniIzvjestaji);
    }
}
