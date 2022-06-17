package com.wraithbeam.specvuzavtomatica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите папку!");

        File selectedFile = fileChooser.showOpenDialog(stage);

        Zipper zipper = new Zipper();
        zipper.setPath(Path.of(selectedFile.getPath()));

        Thread thread = new Thread(zipper);
        thread.start();
    }

    public static void main(String[] args) {
        launch();
    }
}