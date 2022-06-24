package com.wraithbeam.specvuzavtomatica;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import java.util.zip.DeflaterOutputStream;

public class Zipper implements Runnable{

    private File file;
    private static int countOfCompletedFiles = 0;

    public Zipper(File file) {
        this.file = file;
    }
    public void setFile(File file) {
        this.file = file;
    }


    @Override
    public void run() {

        try {
            if (!(file.getName().contains("zipped"))){
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("Start!" + countOfCompletedFiles);
                FileInputStream fis = new FileInputStream(file);

                String fileName = FilenameUtils.removeExtension(file.getName());
                String extension = FilenameUtils.getExtension(file.getName());

                FileOutputStream fos = new FileOutputStream(new File(file.getParent() + "/" +
                        fileName + "_zipped." + extension));

                DeflaterOutputStream dos = new DeflaterOutputStream(fos);

                int data;
                while ((data = fis.read()) != -1) {
                    dos.write(data);
                }

                fis.close();
                dos.close();
            }
            countOfCompletedFiles++;
            System.out.println("Stop!" + countOfCompletedFiles);
        }
        catch (Exception e){

        }
    }

    public static int getCountOfCompletedFiles() {
        return countOfCompletedFiles;
    }

    public static void setCountOfCompletedFiles(int countOfCompletedFiles) {
        Zipper.countOfCompletedFiles = countOfCompletedFiles;
    }
}
