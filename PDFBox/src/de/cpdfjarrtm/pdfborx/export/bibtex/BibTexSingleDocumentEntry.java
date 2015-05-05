package de.cpdfjarrtm.pdfborx.export.bibtex;

import de.cpdfjarrtm.pdfborx.pdf.document.BibtexEntryType;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;

/**
 * Class to create a formatted BibTex entry.
 * 
 * @author Julian Goetz
 *
 */
public class BibTexSingleDocumentEntry {

    String completeEntry;
    private final String defaultType = BibtexEntryType.ARTICLE.name();
    private final String defaultKey = "nokey";
    
    public BibTexSingleDocumentEntry(){
        
    }
    
    /**
     * Creates a formatted BibTex Entry for one document.
     * 
     * @param document  Metadata of this document will be exported.
     * @return String containing the BibTex entry.
     */
    public String getcompleteEntry(DocumentPdf document){
        if (document != null){
            completeEntry = startDocumentEntry(document)+getAttributes(document);
            //substring deletes comma after last attribute to be consistent with BibTex standards
            completeEntry = completeEntry.substring(0, completeEntry.length()-2)+"\n}\n\n";
        }

        return completeEntry;
    }
    
    private String startDocumentEntry(DocumentPdf document){
        
        String entryType;
        String key;
        
        if (document.getEntryType() != null){
            entryType = document.getEntryType().toString();
        }
        else{
            entryType = defaultType;
        }
        
        if (document.getField(BibtexFieldType.KEY)!= null){
            key = document.getField(BibtexFieldType.KEY);
        }
        else{
            key = defaultKey;
        }
        
        return "@"+entryType+"{"+key+",\n";
    }
    
    private String getAttributes(DocumentPdf document){
        String attributes = "";
        String fieldValue;
        for (BibtexFieldType bib : BibtexFieldType.values()){
            if ((fieldValue = document.getField(bib)) != null && bib != BibtexFieldType.KEY){
                attributes = attributes + "\t"+bib.toString()+" = {"+fieldValue+"},\n";
            }
            
        }
        return attributes;
    }

}
