package com.wraithbeam.specvuzavtomatica;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirectoryAnalyzer {
    private File directory;
    private int numberOfThreads = 15;

    public DirectoryAnalyzer(File directory) {
        this.directory = directory;
    }

    public void analyse(){
        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);
        for (File file: directory.listFiles()) {
            if (file.isFile()) {
                Zipper zipper = new Zipper(file);
                pool.execute(zipper);
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
