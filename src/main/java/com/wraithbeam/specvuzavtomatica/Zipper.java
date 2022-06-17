package com.wraithbeam.specvuzavtomatica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.zip.DeflaterOutputStream;

public class Zipper implements Runnable{

    private Path path;

    @Override
    public void run() {
        File file = new File(path.toUri());

        try {
            FileInputStream fis = new FileInputStream(file);


            FileOutputStream fos = new FileOutputStream(new File(file.getParent() + "\\zipped_" + file.getName()));

            DeflaterOutputStream dos = new DeflaterOutputStream(fos);

            int data;
            while ((data = fis.read()) != -1) {
                dos.write(data);
            }

            fis.close();
            dos.close();
        }
        catch (Exception e){
            System.out.println("Случилась жопа! " + e);
        }
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
