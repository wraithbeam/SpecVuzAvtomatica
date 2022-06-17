module com.wraithbeam.specvuzavtomatica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.wraithbeam.specvuzavtomatica to javafx.fxml;
    exports com.wraithbeam.specvuzavtomatica;
    exports com.wraithbeam.specvuzavtomatica.controllers;
    opens com.wraithbeam.specvuzavtomatica.controllers to javafx.fxml;
}