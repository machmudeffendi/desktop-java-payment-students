module dev.machmudeffendi.uas_pbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    requires com.dlsc.formsfx;

    opens dev.machmudeffendi.uas_pbo to javafx.fxml;
    opens dev.machmudeffendi.uas_pbo.models to javafx.base;
    exports dev.machmudeffendi.uas_pbo;
    exports dev.machmudeffendi.uas_pbo.controllers;
    opens dev.machmudeffendi.uas_pbo.controllers to javafx.fxml;
}