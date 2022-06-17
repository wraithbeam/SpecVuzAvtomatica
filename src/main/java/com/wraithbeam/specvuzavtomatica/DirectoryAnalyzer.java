package com.wraithbeam.specvuzavtomatica;

import java.io.File;

public class DirectoryAnalyzer {
    private File directory;

    public DirectoryAnalyzer(File directory) {
        this.directory = directory;
    }

    public void analyse(){
        for (File file: directory.listFiles()) {
            if (file.isFile()) {
                Zipper zipper = new Zipper(file);
                Thread thread = new Thread(zipper);
                thread.start();
            }
            if (file.isDirectory()){
                DirectoryAnalyzer directoryAnalyzer = new DirectoryAnalyzer(file);
                directoryAnalyzer.analyse();
            }
        }
    }


    public void setPath(File directory) {
        this.directory = directory;
    }
}
