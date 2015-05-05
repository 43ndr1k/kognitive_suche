package de.cpdfjarrtm.pdfborx.gui.data;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * PdfList.
 * 
 * @author  Alexander Fluegge
 */
public class Pdflist {
    
    public ArrayList<File> pdfs = new ArrayList<>();
    
    /** 
     * search (File dir, Pdflist pdfs)
     * 
     * @param   dir
     *          The directory which will be search
     */
    public void search (File dir){
        File[] list = dir.listFiles();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                //System.out.println(list[i].getAbsolutePath());
                if (list[i].isDirectory()) {
                    search(list[i]); 
                } else {
                    if (list[i].getName().matches(".*pdf")){
                            pdfs.add(list[i]);
                    }
                }
            }
        }
    }
    
    /**
     * Starts a Filechooser for PDF-Files
     * 
     * @return File-Array with choosed pdf-Files 
     * @see {@link JFileChooser}
     */
    public File[] getPDF(){

        FileFilter filter = new FileNameExtensionFilter("PDF-Dateien", "pdf");
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);

        int size = 0;
        File[] pdf;
        pdf = chooser.getSelectedFiles();
        for (int i = 0; i < pdf.length; i++) {
            if(pdf[i] == null || !pdf[i].getName().matches(".*.pdf")) {
                System.out.println("import fehler: " + pdf[i]);
                //JOptionPane.showMessageDialog(null, "Datei: " + pdf[i].getName() + " ungueltig!!");
                pdf[i] = null;
            } else {
                size++;
            }
        }

        File[] ret = new File[size];

        for (int i = 0, j = 0; i < pdf.length; i++) {
            if (pdf[i] != null) {
                ret [j] = pdf[i];
                j++;
            }
        }

        return ret;
    }

    /**
     * Starts a Filechooser for directories
     * 
     * @return File-Array with choosed directories
     * @see {@link JFileChooser}
     */
    public ArrayList<File> getDir(){
        File[] dir;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(null);

        dir = chooser.getSelectedFiles();
        for (int i = 0; i < dir.length; i++){
            search(dir[i]);
        }
        return pdfs;
    }
    
    public File getbib(){
        FileFilter filter = new FileNameExtensionFilter("Bibtex-Dateien", "bib");
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        return chooser.getSelectedFile();
    }

}
