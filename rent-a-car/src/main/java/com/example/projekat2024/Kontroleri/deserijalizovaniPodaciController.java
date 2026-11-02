package com.example.projekat2024.Kontroleri;
import com.example.projekat2024.PrevoznaSredstva.Automobil;
import com.example.projekat2024.PrevoznaSredstva.Bicikl;
import com.example.projekat2024.PrevoznaSredstva.Trotinet;
import com.example.projekat2024.RadSaFajlovima.BinarnaSerijalizacija;
import com.example.projekat2024.RadSaFajlovima.MyProperties;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.util.Properties;

public class deserijalizovaniPodaciController {
    @FXML
    private TextArea taAutomobil = new TextArea();
    @FXML
    private TextArea taTrotinet = new TextArea();
    @FXML
    private TextArea taBicikl = new TextArea();

    public void initialize()
    {
        Properties pro = MyProperties.getProperties();
        Automobil auto = (Automobil)BinarnaSerijalizacija.deserijalizujVozilaSNajvecimGubicima(pro.getProperty("FajlAKvar"));
        Bicikl bike = (Bicikl)BinarnaSerijalizacija.deserijalizujVozilaSNajvecimGubicima(pro.getProperty("FajlBKvar"));
        Trotinet trotinet =(Trotinet)BinarnaSerijalizacija.deserijalizujVozilaSNajvecimGubicima(pro.getProperty("FajlTKvar"));
        if(auto != null) {
            taAutomobil.setText(auto.toString());
        }
        if(trotinet != null) {
            taTrotinet.setText(trotinet.toString());
        }
        if(bike != null) {
            taBicikl.setText(bike.toString());
        }
    }
}
