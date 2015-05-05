package de.cpdfjarrtm.pdfborx.imports;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import de.cpdfjarrtm.pdfborx.imports.bibtex.BibTexImportModel;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexEntryType;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;

/**
 * Class to control the import of BibTex files.
 * 
 * @author Julian Goetz
 *
 */
public class ImportController {

    private BibTexImportModel bibTexImport;
    private HashMap <BibtexFieldType, String> fieldTypeMap;
    private HashMap <String, String> customFieldMap;
    private BibtexEntryType entryType;
    
    
    /**
     * Creates a new ImportController object.
     */
    public ImportController(){
        bibTexImport = new BibTexImportModel();
    }
    
    /**
     * This method will import and parse a BibTex file for its entries.
     * 
     * @param file File to be imported.
     * @return True, if file was correctly parsed. False, if not.
     */
    public boolean importBibTex(File file, DocumentPdf document){

        if (document != null){
            if (bibTexImport.importBibTexFile(file)){
                fieldTypeMap = bibTexImport.getfieldTypeMap();
                entryType = bibTexImport.getentryType();
                customFieldMap = bibTexImport.getcustomFieldMap();
                document.setEntryType(entryType);
                
                for (Map.Entry< BibtexFieldType,String > entry : fieldTypeMap.entrySet()) {
                    document.setField(entry.getKey(), entry.getValue());
                }
                
                for (Map.Entry< String,String > entry : customFieldMap.entrySet()) {
                    document.setCustomField(entry.getKey(), entry.getValue());
                }
                
                return true;
            }
        }

        return false;
    }
    
    /**
     * Use this after importBibTex to get the parsed BibTex values in a HashMap.
     * 
     * @return HashMap containing the BibTex fields.
     */
    public HashMap <BibtexFieldType, String> getfieldTypeMap(){
        return fieldTypeMap;
    }
    
    /**
     * Use this after importBibTex to get the parsed BibTex entry type.
     * 
     * @return The BibTex entry type.
     */
    public BibtexEntryType getentryType(){
        return entryType;
    }
    
    /**
     * Use this after importBibTex to get the parsed non-standard BibTex values.
     * 
     * @return HashMap containing each non-standard BibTex value.
     */
    public HashMap <String, String> getcustomFieldMap(){
        return customFieldMap;
    }
}
