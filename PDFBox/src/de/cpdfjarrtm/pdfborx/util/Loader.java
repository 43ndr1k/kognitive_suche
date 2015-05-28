package de.cpdfjarrtm.pdfborx.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

/**
 * Helper functions for loading and saving files from/to disk.
 * 
 * @author  Philipp Kleinhenz
 */
public class Loader {
    
    /**
     * Load a file as byte array.
     * 
     * @param   path
     *          Full path of the file.
     * @return  The file as byte array or null if the file couldn't be loaded.
     */
    public static byte[] loadFile(String path) {
        Path p = Paths.get(path);
        byte[] b = null;
        try {
            b = Files.readAllBytes(p);
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
    
    /**
     * Save a byte array to a file.
     * If the file exists it will be overwritten.
     * 
     * @param   data
     *          The byte array representing the file.
     * @param   path
     *          The full path, including file name, where the file should be created.
     * @return  True if the file was successfully written, false if not.
     */
    public static boolean saveFile(byte[] data, String path) {
        boolean ret = true;
        Path p = Paths.get(path);
        try {
            Files.write(p, data, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Files.exists(p);
    }
    
    /**
     * Load a text file as CharArraySet.
     * Each line will be saved as a seperate CharArray.
     * @param   path
     *          Path to the text file to load.
     * @return  The text file as CharArraySet. One line per entry. 
     */
    public static CharArraySet loadAsCharArraySet(String path) {
        InputStream is;
        Scanner scanner;
        ArrayList<String> stoplist;
        CharArraySet stopset;
        
        is = (new Loader()).getClass().getResourceAsStream(path);
        scanner = new Scanner(is);
        stoplist = new ArrayList<>();
        while (scanner.hasNextLine()) {
            stoplist.add(scanner.nextLine());
        }
        scanner.close();
        
        try {
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stopset = new CharArraySet(Version.LUCENE_46, stoplist, true);
        stoplist.clear();
        return stopset;
    }
    
}
