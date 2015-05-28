package de.cpdfjarrtm.pdfborx.export.bibtex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;

/**
 * Class to export all information gathered to a BibTex file.
 * @author Julian Goetz
 *
 */
public class FileExportBibTex {
	
    public FileExportBibTex(){
        
    }
    
    private FileWriter writer;
    private File bibfile;
    
    /**
     * Creates the BibTex file.
     * @param directory
     *        The directory where the BibTex file will be saved. Must include the filename.
     * @param books
     *        The information of these books will be exported.
     * @return True, if creation was successful. False, if not.
     */
    public boolean createBibFile(String directory, DocumentPdf[] books){
        
        if (books == null){
            System.out.println("DocumentPdf[] books must be non-null.");
            return false;
        }

        int iterator = 0;
        //BibTexsingleBookEntry entry = new BibTexsingleBookEntry();
        BibTexSingleDocumentEntry entryGenerator = new BibTexSingleDocumentEntry();
        bibfile = new File(directory);
        
        try {
            bibfile.createNewFile();        
            writer = new FileWriter(bibfile);
            while (iterator < books.length){
                
                if (books[iterator] != null){
                    //writer.write(entry.getAttributes(iterator, books[iterator]));
                    writer.write(entryGenerator.getcompleteEntry(books[iterator]));
                }

                iterator++;
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Logger.getLogger(FileExportBibTex.class.getName())
            .log(Level.SEVERE, null, e);
            System.out.println("IOException - Unknown Path");
            return false;
        }
        return true;
    }

}
