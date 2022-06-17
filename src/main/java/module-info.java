module com.wraithbeam.specvuzavtomatica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.io;

    exports com.wraithbeam.specvuzavtomatica;
    exports com.wraithbeam.specvuzavtomatica.controllers;
    opens com.wraithbeam.specvuzavtomatica.controllers to javafx.fxml;
}