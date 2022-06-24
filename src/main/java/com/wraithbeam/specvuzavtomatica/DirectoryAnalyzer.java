package com.wraithbeam.specvuzavtomatica;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DirectoryAnalyzer {
    private File directory;
    private static final int numberOfThreads = 15;

    private static final ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

    public DirectoryAnalyzer(File directory) {
        this.directory = directory;
    }

    public void analyse(){
        for (File file: Objects.requireNonNull(directory.listFiles())) {
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
