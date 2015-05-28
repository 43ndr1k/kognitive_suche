package de.cpdfjarrtm.pdfborx.export.bibtex;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;

/**
 * Class to create a formatted BibTex entry for a single book.
 * @author Julian Goetz
 *
 */
public class BibTexsingleBookEntry {
    
    public BibTexsingleBookEntry(){
        
    }
    
    /**
     * @param iterator
     *        The number of the book.
     * @param book
     *        Metadata of this book will be exported.
     * @return Returns a string containing the BibTex entry.
     *
     */
    public String getAttributes(int iterator, DocumentPdf book){
        return startBookEntry(iterator)+writeAuthor(book)+writeTitle(book)+endBookEntry();
        //return startBookEntry(iterator)+writeAuthor(book)+writeTitle(book)+writeYear(book)+endBookEntry();
    }
    
    private String startBookEntry(int booknr){
        return "@book{"+(booknr+1)+",\n";
    }
    
    private String writeAuthor(DocumentPdf book){
        if (book != null){
            return "    author = {"+book.getField(BibtexFieldType.AUTHOR)+endAttribute();
        }
        else {
            return "    author = {"+endAttribute();
        }
    }
    
    private String writeTitle(DocumentPdf book){
        if (book != null){
            return "    title = {"+book.getField(BibtexFieldType.TITLE)+endAttribute();
        }
        else {
            return "    title = {"+endAttribute();
        }
    }
    
    /*private String writeYear(DocumentPdf book){
        return "    year = {"+book.getYear()+endAttribute();
    }*/
    
    private String endAttribute(){
        return "},\n";
    }
    
    private String endBookEntry(){
        return "}\n\n";
    }

}
