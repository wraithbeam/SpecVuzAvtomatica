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
    private int countOfFiles = 0;

    public DirectoryAnalyzer(File directory) {
        this.directory = directory;
    }

    public void analyse(){
        for (File file: Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                Zipper zipper = new Zipper(file);
                pool.execute(zipper);
                countOfFiles++;
            }
            if (file.isDirectory()){
                DirectoryAnalyzer directoryAnalyzer = new DirectoryAnalyzer(file);
                directoryAnalyzer.analyse();
            }
        }
//        while (countOfFiles != Zipper.getCountOfCompletedFiles()){
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//                System.out.println(Zipper.getCountOfCompletedFiles());
//            }
//            catch (InterruptedException e){
//                System.out.println("Application was interrupted");
//            }
//        }
//        System.out.println("Task completed");
    }


    public void setPath(File directory) {
        this.directory = directory;
    }

    public int getCountOfFiles() {
        return countOfFiles;
    }
}
