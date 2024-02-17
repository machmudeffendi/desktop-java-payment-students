package dev.machmudeffendi.uas_pbo;

import dev.machmudeffendi.uas_pbo.utils.Alerts;
import dev.machmudeffendi.uas_pbo.utils.JDBC;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        if(JDBC.isOK()) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 960, 540);
            stage.setTitle("Sistem Pembayaran SPP SMP JAKENAN!");
            stage.setScene(scene);
            stage.show();
        } else {
            Alerts.error(
                    "Database error",
                    "Could not load database",
                    "Error loading Mysql database. See log. Quitting..."
            ).showAndWait();
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}