package de.cpdfjarrtm.pdfborx.pdf.load;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.util.PDFTextStripper;


/**
 * Handles loading of PDF files into data objects.
 * 
 * @author  Philipp Kleinhenz
 */
class LoadModelDocumentLucene implements LoadModel {
    /**
     * Amount of threads to use.
     */
    static private final int CORES = Runtime.getRuntime().availableProcessors();
    /**
     * Textstrippers for extracting text from documents.
     */
    private final ArrayBlockingQueue<BorxTextStripper> strippers;
    
    LoadModelDocumentLucene() {
        strippers = new ArrayBlockingQueue<>(CORES*2);
        
        for (int i = 0; i < CORES*2; i++) {
            try {
                BorxTextStripper s = new BorxTextStripper();
                s.setAddMoreFormatting(true);
                strippers.offer(s);
            } catch (IOException ex) {
                Logger.getLogger(LoadController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Loads multiple PDF files.
     * 
     * @param   files
     *          A {@link Collection} of filenames of PDF files to load.
     * @return  A map containing the data objects with metadata and plain text
     *          of the PDF files with the filenames as keys.
     */
    @Override
    public Map<File, DocumentPdf> load(Collection<File> files) {        
        int amount = files.size();
        ConcurrentMap<File, DocumentPdf> results = new ConcurrentHashMap<>(amount);
        ExecutorService executor = Executors.newFixedThreadPool(CORES);
        // Submit all loading tasks to the executor
        for (File file : files) {
            executor.submit(new LoadTaskDocumentLucene(file, results, strippers));
        }
        
        executor.shutdown();
        // wait until all tasks are done
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    /**
     * Loads a PDF file. Creates its own {@link PDFTextStripper}.
     * Use {@code load(filename)} if you have strippers premade as it is faster
     * and less error prone.
     * 
     * @param   file
     *          The file to load.
     * @return  An object containing the metadata and plain text of the PDF.
     */
    @Override
    public DocumentPdf load(File file) {
        try {
            return LoadTaskDocumentLucene.load(file, new BorxTextStripper());
        } catch (IOException ex) {
            Logger.getLogger(LoadModelDocumentLucene.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Loads a PDF file.
     * 
     * @param   file
     *          The file to load.
     * @param   stripper
     *          The stripper to use for text extraction.
     * @return  An object containing the metadata and plain text of the PDF.
     */
    public DocumentPdf load(File file, BorxTextStripper stripper) {
        return LoadTaskDocumentLucene.load(file, stripper);
    }
    
}
