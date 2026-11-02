package com.example.projekat2024.Kontroleri;
import com.example.projekat2024.PrevoznaSredstva.Automobil;
import com.example.projekat2024.PrevoznaSredstva.Bicikl;
import com.example.projekat2024.PrevoznaSredstva.Trotinet;
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

public class PrevoznaSredstvaController implements Initializable {
    Properties pro = MyProperties.getProperties();
    @FXML
    private TableView<Automobil> tabelaAutomobila;
    @FXML
    private TableView<Bicikl> tabelaBicikala;
    @FXML
    private TableView<Trotinet> tabelaTrotineta;
    @FXML
    private TableColumn<Automobil,Integer> identifikatorAuta;
    @FXML
    private TableColumn<Automobil,Integer> proizvodjacAutomobila;
    @FXML
    private TableColumn<Automobil,Integer> modelAutomobila;
    @FXML
    private TableColumn<Automobil,Integer> cijenaAutomobila;
    @FXML
    private TableColumn<Automobil,Integer> datumNabavkeAutomobila;
    @FXML
    private TableColumn<Automobil,Integer> opisAutomobila;
    @FXML
    private TableColumn<Trotinet,Integer> identifikatorTrotineta;
    @FXML
    private TableColumn<Trotinet,Integer> proizvodjacTrotineta;
    @FXML
    private TableColumn<Trotinet,Integer> modelTrotineta;
    @FXML
    private TableColumn<Trotinet,Integer> cijenaTrotineta;
    @FXML
    private TableColumn<Trotinet, Integer> maxBrzinaTrotineta;
    @FXML
    private TableColumn<Bicikl,Integer> identifikatorBicikla;
    @FXML
    private TableColumn<Bicikl,Integer> proizvodjacBicikla;
    @FXML
    private TableColumn<Bicikl,Integer> modelBicikla;
    @FXML
    private TableColumn<Bicikl,Integer> cijenaBicikla;
    @FXML
    private TableColumn<Bicikl,Integer> dometBicikla;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        identifikatorAuta.setCellValueFactory(new PropertyValueFactory<>("identifikator"));
        proizvodjacAutomobila.setCellValueFactory(new PropertyValueFactory<>("proizvodjac"));
        modelAutomobila.setCellValueFactory(new PropertyValueFactory<>("model"));
        cijenaAutomobila.setCellValueFactory(new PropertyValueFactory<>("cijenaNabavke"));
        datumNabavkeAutomobila.setCellValueFactory(new PropertyValueFactory<>("datumNabavke"));
        opisAutomobila.setCellValueFactory(new PropertyValueFactory<>("opis"));
        generisiTabeluAutomobila();

        identifikatorBicikla.setCellValueFactory(new PropertyValueFactory<>("identifikator"));
        proizvodjacBicikla.setCellValueFactory(new PropertyValueFactory<>("proizvodjac"));
        modelBicikla.setCellValueFactory(new PropertyValueFactory<>("model"));
        cijenaBicikla.setCellValueFactory(new PropertyValueFactory<>("cijenaNabavke"));
        dometBicikla.setCellValueFactory(new PropertyValueFactory<>("dometSJednimPunjenjem"));
        generisiTabeluBicikala();

        identifikatorTrotineta.setCellValueFactory(new PropertyValueFactory<>("identifikator"));
        proizvodjacTrotineta.setCellValueFactory(new PropertyValueFactory<>("proizvodjac"));
        modelTrotineta.setCellValueFactory(new PropertyValueFactory<>("model"));
        cijenaTrotineta.setCellValueFactory(new PropertyValueFactory<>("cijenaNabavke"));
        maxBrzinaTrotineta.setCellValueFactory(new PropertyValueFactory<>("maxBrzina"));
        generisiTabeluTrotineta();
    }
    private void generisiTabeluAutomobila()
    {
        List<Automobil> automobili = TekstualnaSerijalizacija.procitajAutomobile(pro.getProperty("FajlVozila"));
        tabelaAutomobila.getItems().addAll(automobili);
    }
    private void generisiTabeluBicikala()
    {
        List<Bicikl> bicikli = TekstualnaSerijalizacija.procitajBicikle(pro.getProperty("FajlVozila"));
        tabelaBicikala.getItems().addAll(bicikli);
    }
    private void generisiTabeluTrotineta()
    {
        List<Trotinet> trotineti = TekstualnaSerijalizacija.procitajTrotinete(pro.getProperty("FajlVozila"));
        tabelaTrotineta.getItems().addAll(trotineti);
    }
}
