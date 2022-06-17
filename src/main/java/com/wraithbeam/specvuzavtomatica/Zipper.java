package com.wraithbeam.specvuzavtomatica;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.DeflaterOutputStream;

public class Zipper implements Runnable{

    private File file;

    public Zipper(File file) {
        this.file = file;
    }
    public void setFile(File file) {
        this.file = file;
    }


    @Override
    public void run() {

        try {
            if (!file.getName().contains("zipped")){
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
        }
        catch (Exception ignored){}
    }

}
