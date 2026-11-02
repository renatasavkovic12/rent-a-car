package com.example.projekat2024.Kontroleri;
import com.example.projekat2024.Izvjestaji.DnevniIzvjestaj;
import com.example.projekat2024.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static com.example.projekat2024.Simulacija.ePJ2Kompanija.dnevniIzvjestaji;
public class rezultatiPoslovanjaController {
    @FXML
    public ComboBox<String> comboBox= new ComboBox<>();
    @FXML
    protected void onClickedPrikaziDnevniIzvjestaj() {
        String selektovaniDatum = comboBox.getValue();
        if(selektovaniDatum !=null)
        {
            otvoriDnevniIzvjestaj(selektovaniDatum);
        }
        else
        {
            System.out.println("Nije selektovan nijedan datum.");
        }
    }
    private void otvoriDnevniIzvjestaj(String selektovaniDatum) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dnevniIzvjestaj.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 700);
            dnevniIzvjestajController controller = fxmlLoader.getController();
            controller.setSelektovaniDatum(selektovaniDatum);
            stage.setTitle("Dnevni Izvještaj");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onClickedPrikaziSumarniIzvjestaj() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sumarniIzvjestaj.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 700);
        stage.setTitle("SUMARNI IZVJEŠTAJ:");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize()
    {
        List<String> datumi = dnevniIzvjestaji.stream()
                .map(DnevniIzvjestaj::getDatumIzvjestaja)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        ObservableList<String> observableDatumi = FXCollections.observableArrayList(datumi);
        comboBox.getItems().addAll(observableDatumi);
    }
}
