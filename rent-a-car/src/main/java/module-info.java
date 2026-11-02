module com.example.projekat.pj {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;

    opens com.example.projekat2024 to javafx.fxml;
    exports com.example.projekat2024;
    exports com.example.projekat2024.Kontroleri;
    exports com.example.projekat2024.Simulacija;
    exports com.example.projekat2024.Mapa;
    exports com.example.projekat2024.PrevoznaSredstva;
    exports com.example.projekat2024.Izvjestaji;
    opens com.example.projekat2024.Kontroleri to javafx.fxml;
}