package com.wraithbeam.specvuzavtomatica;

import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите папку!");

        try {
            File selectedDirectory = directoryChooser.showDialog(stage);

            DirectoryAnalyzer directoryAnalyzer = new DirectoryAnalyzer(selectedDirectory);
            directoryAnalyzer.analyse();
        }
        catch (Exception ignored){}
    }

    public static void main(String[] args) {
        launch();
    }
}