package com.wraithbeam.specvuzavtomatica.controllers;

import com.wraithbeam.specvuzavtomatica.DirectoryAnalyzer;
import com.wraithbeam.specvuzavtomatica.Zipper;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class MainController {

    private File selectedDirectory;
    private double sizeDirectory = 0;
    private double filesInDirectory = 0;

    @FXML
    private ProgressBar barProgress;

    @FXML
    private Button btnSelect;

    @FXML
    private Button btnStart;

    @FXML
    private Label labelFreeSpace;

    @FXML
    private Label labelNeedSpace;

    @FXML
    private Label labelProgress;

    @FXML
    void openDirectoryChooser(MouseEvent event) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory!");
        try {
            selectedDirectory = directoryChooser.showDialog(new Stage());
            setAttributes();
        }
        catch (Exception NullPointerException){
            System.out.println("Nothing was selected!");
        }
    }

    @FXML
    void startCompression(MouseEvent event){

        //New thread which counts the percentage of completion
        Thread thread  = new Thread(new Runnable() {
            @Override
            public void run() {
                double alreadyAnalyzed = Zipper.getCountOfCompletedFiles();
                while (true){
                    if (alreadyAnalyzed != Zipper.getCountOfCompletedFiles()) {
                        System.out.println("Progress: " + alreadyAnalyzed/filesInDirectory);
                        alreadyAnalyzed = Zipper.getCountOfCompletedFiles();
                        updateProgress(alreadyAnalyzed / filesInDirectory);
                    }

                    if (filesInDirectory == alreadyAnalyzed) {
                        System.out.println("Progress: " + alreadyAnalyzed/filesInDirectory);
                        updateProgress(alreadyAnalyzed / filesInDirectory);
                        break;
                    }
                }
                System.out.println("Progress: " + alreadyAnalyzed/filesInDirectory);
                updateProgress(alreadyAnalyzed / filesInDirectory);
            }
        });
        thread.start();

        DirectoryAnalyzer directoryAnalyzer = new DirectoryAnalyzer(selectedDirectory);
        directoryAnalyzer.analyse();
    }

    private void setAttributes(){
        barProgress.setProgress(0);
        try {
            sizeDirectory = Files.walk(Path.of(selectedDirectory.getAbsolutePath()))
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();
            filesInDirectory = Files.walk(Path.of(selectedDirectory.getAbsolutePath()))
                    .filter(p -> p.toFile().isFile())
                    .count();
            System.out.println(filesInDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        labelFreeSpace.setText(formatBytes(selectedDirectory.getFreeSpace()));
        labelNeedSpace.setText(formatBytes(sizeDirectory));

        if(selectedDirectory.getFreeSpace() - sizeDirectory >= sizeDirectory){
            btnStart.setDisable(false);
        }
        else {
            labelNeedSpace.setStyle("-fx-text-fill: red");
        }
    }


    private String formatBytes(double bytes){

        if (bytes / (1024*1024*1024) >= 1) {
            return String.format("%.2f", (bytes / (1024 * 1024 * 1024))) + " Gb";
        }
        if (bytes / (1024 * 1024) >= 1){
            return String.format("%.2f", (bytes / (1024 * 1024))) + " Mb";
        }
        if (bytes / 1024 >= 1){
            return String.format("%.2f", (bytes / 1024)) + " Kb";
        }
        else {
            return String.format("%.2f", (bytes)) + " Bytes";
        }
    }

    private void updateProgress(double progress){
        barProgress.setProgress(progress);
        //labelProgress.setText(barProgress.getProgress() * 100 + "%");
    }

}
