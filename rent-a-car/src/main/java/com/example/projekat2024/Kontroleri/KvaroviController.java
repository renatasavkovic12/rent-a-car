package com.example.projekat2024.Kontroleri;
import com.example.projekat2024.PrevoznaSredstva.Kvar;
import com.example.projekat2024.RadSaFajlovima.MyProperties;
import com.example.projekat2024.RadSaFajlovima.TekstualnaSerijalizacija;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
/**
 * klasa koja sluzi za generisanje GUI koji prikazuje sve kvarove koji su se desili u toku simulacije
 */
public class KvaroviController implements Initializable {
    @FXML
    private TableView<Kvar> tabelaKvarova;
    @FXML
    private TableColumn<Kvar,Integer> vrstaVozila;
    @FXML
    private TableColumn<Kvar,Integer> idVozila;
    @FXML
    private TableColumn<Kvar, Integer> vrijemeKvara;
    @FXML
    private TableColumn<Kvar, Integer> opisKvara;
    /**
     * Override funkcije initialize() koja inicijalizuje kontroler nakon što se korisnički interfejs (GUI) učita.
     * Postavlja svojstva za kolone tabele i generiše tabelu kvarova.
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vrstaVozila.setCellValueFactory(new PropertyValueFactory<>("vrstaVozila"));
        idVozila.setCellValueFactory(new PropertyValueFactory<>("idVozila"));
        vrijemeKvara.setCellValueFactory(new PropertyValueFactory<>("datumVrijemeKvara"));
        opisKvara.setCellValueFactory(new PropertyValueFactory<>("opisKvara"));
        generisiTabeluKvarova();
    }
    private void generisiTabeluKvarova()
    {
        Properties pro = MyProperties.getProperties();
        List<Kvar> listaKvarova = TekstualnaSerijalizacija.procitajKvarove(pro.getProperty("FajlKvarovi"));
        tabelaKvarova.getItems().addAll(listaKvarova);
    }
}
