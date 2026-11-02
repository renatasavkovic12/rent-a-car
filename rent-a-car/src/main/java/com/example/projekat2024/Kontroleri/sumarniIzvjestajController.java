package com.example.projekat2024.Kontroleri;
import com.example.projekat2024.Izvjestaji.SumarniIzvjestaj;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import com.example.projekat2024.Simulacija.ePJ2Kompanija;

public class sumarniIzvjestajController implements Initializable {
    @FXML
    private TableView<SumarniIzvjestaj> sumarniIzvjestaj;
    @FXML
    private TableColumn<SumarniIzvjestaj, Integer> ukupanPrihod;
    @FXML
    private TableColumn<SumarniIzvjestaj, Integer> ukupanPopust;
    @FXML
    private TableColumn<SumarniIzvjestaj, Integer> ukupnoPromocija;
    @FXML
    private TableColumn<SumarniIzvjestaj, Integer> iznosVoznjiUziDio;
    @FXML
    private TableColumn<SumarniIzvjestaj, Integer> ukupnoOdrzavanje;
    @FXML
    private TableColumn<SumarniIzvjestaj, Integer> popravkeKvarova;
    @FXML
    private TableColumn<SumarniIzvjestaj, Integer> ukupniTroskoviKompanije;
    @FXML
    private TableColumn<SumarniIzvjestaj, Integer> ukupanPorezKompanije;
    @FXML
    private TableColumn<SumarniIzvjestaj, Integer> iznosVoznjiSiriDio;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ukupanPopust.setCellValueFactory(new PropertyValueFactory<>("ukupanPopust"));
        ukupanPrihod.setCellValueFactory(new PropertyValueFactory<>("ukupanPrihod"));
        ukupnoPromocija.setCellValueFactory(new PropertyValueFactory<>("ukupnoPromocija"));
        iznosVoznjiUziDio.setCellValueFactory(new PropertyValueFactory<>("iznosVoznjiUziDio"));
        ukupnoOdrzavanje.setCellValueFactory(new PropertyValueFactory<>("ukupnoOdrzavanje"));
        ukupniTroskoviKompanije.setCellValueFactory(new PropertyValueFactory<>("ukupniTroskoviKompanije"));
        ukupanPorezKompanije.setCellValueFactory(new PropertyValueFactory<>("ukupanPorezKompanije"));
        popravkeKvarova.setCellValueFactory(new PropertyValueFactory<>("ukupnoPopravke"));
        iznosVoznjiSiriDio.setCellValueFactory(new PropertyValueFactory<>("iznosVoznjiSiriDio"));
        generisiTabeluSumarniIzvjestaj();
    }
    private void generisiTabeluSumarniIzvjestaj()
    {
        SumarniIzvjestaj izvjestaj = ePJ2Kompanija.sumarniIzvjestaj;
        sumarniIzvjestaj.getItems().addAll(izvjestaj);
    }
}
