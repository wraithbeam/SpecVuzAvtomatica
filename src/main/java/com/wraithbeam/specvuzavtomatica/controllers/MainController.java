package com.wraithbeam.specvuzavtomatica.controllers;

import com.wraithbeam.specvuzavtomatica.DirectoryAnalyzer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainController {

    private File selectedDirectory;

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
            DirectoryAnalyzer directoryAnalyzer = new DirectoryAnalyzer(selectedDirectory);
            directoryAnalyzer.analyse();
        }
        catch (Exception NullPointerException){
            System.out.println("Nothing was selected!");
        }
    }

    @FXML
    void startCompression(MouseEvent event) {

    }

    private void setAttributes(){
        labelFreeSpace.setText(byteToGigabyte(selectedDirectory.getFreeSpace()) + " Gb");
        labelNeedSpace.setText(byteToMegabyte(selectedDirectory.length()) + " Mb");
    }

    private double byteToMegabyte(double bytes){
        return bytes / (1024*1024);
    }
    private double byteToGigabyte(double bytes){
        return bytes / (1024*1024*1024);
    }

}
