package de.leipzig.htwk.searchApi;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *@Autor Hendrik Sawade
 */
public class CopyResources {
    String phad = System.getProperty("user.dir");
   // File dir = null;
    //File file = null;
    public CopyResources(String resource) {
        copy(resource);

        System.out.println(phad);
        //dir = new File("phantomjs");
        //file = new File(dir.getAbsolutePath() + "/" + resource);
    /*    if(dir.exists()) {
            //System.out.println(file.getPath());
            //System.out.println(file.exists());
           // if(!file.exists()) {
                copy(resource);
            //}
        } else {
            if (dir.mkdir()) {
                copy(resource);
            }else {
                System.out.println(dir + " konnte nicht erstellt werden");
            }

        }*/


    }

    private void copy (String resource) {

        String outPath = phad + "/phantomjs/" + resource;

        try {
            InputStream in = CopyResources.class.getResourceAsStream("/" + resource);
            File fileOut = new File(outPath);
            OutputStream out;
            out = FileUtils.openOutputStream(fileOut);
            IOUtils.copy(in, out);
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }











/*
        try {
            // Get resource as stream
            InputStream in = getClass().getResourceAsStream("/" + resource);
            //InputStream in = getClass().getResourceAsStream("home/hendrik/workspace/kognitive_suche_maven/kognitive_suche/target/repo/lib/phantomjs/" + resource);
            // Set the output path for the stream
            //OutputStream out = new FileOutputStream(dir.getAbsolutePath() + "/" + resource);
            //System.out.println(dir.getAbsolutePath() + "/" + resource);
            OutputStream out = new FileOutputStream(phad + "/phantomjs/" + resource);
            // Copy the resource to the path
            IOUtils.copy(in, out);

            // Close the streams
            in.close();
            out.close();


        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }*/
    }

}
