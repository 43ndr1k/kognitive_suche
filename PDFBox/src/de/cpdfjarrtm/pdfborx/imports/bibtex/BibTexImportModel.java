package de.cpdfjarrtm.pdfborx.imports.bibtex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.cpdfjarrtm.pdfborx.pdf.document.BibtexEntryType;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;

/**
 * Class to import BibTex files.
 * 
 * @author Julian Goetz
 *
 */
public class BibTexImportModel {

    private BufferedReader bufferedReader = null;
    private Matcher matcher;
    private Pattern fieldTypePattern;
    private Pattern entryTypePattern;
    private HashMap <BibtexFieldType, String> fieldTypeMap;
    private HashMap <String, String> customFieldMap;

    private BibtexEntryType entryType;
    
    /**
     * Creates a new BibTexImportModel object.
     */
    public BibTexImportModel(){
        fieldTypePattern = Pattern.compile("(\\w*)(\\s=\\s\\{)(.*?)(\\})(,?)");
        entryTypePattern = Pattern.compile("@(\\w*?)\\{((\\w*),)?");
    }
    
    /**
     * This method will import a BibTex file and parse its entries.
     * Use getfieldTypeMap() and getentryType() to get parsed values.
     * 
     * @param file File to be imported.
     * @return True, if file was correctly parsed. False, if not.
     */
    public boolean importBibTexFile(File file){
            String line = null;
            String templine = null;
            boolean foundEntryType = false;
            fieldTypeMap = new HashMap <BibtexFieldType, String>();
            customFieldMap = new HashMap <String, String>();
            
            try {

                bufferedReader = new BufferedReader(new FileReader(file));

                while ((line = bufferedReader.readLine()) != null){
                    
                    if (!foundEntryType){
                        foundEntryType = parseEntryType(line);
                    }    
                    else{
                        if (!parseBibTex(line)){
                            
                            templine = line;
                            
                            while (!parseBibTex(line) && (templine = bufferedReader.readLine()) != null){
                                line = line+templine;
                            }
                            
                        }
                    }    
                    
                }
                
                bufferedReader.close();
                return true;
                
            } catch (FileNotFoundException e) {
                System.out.println("File to import was not found.");
                Logger.getLogger(BibTexImportModel.class.getName())
                .log(Level.SEVERE, null, e);
                return false;
            } catch (IOException e) {
                System.out.println("IOException.");
                Logger.getLogger(BibTexImportModel.class.getName())
                .log(Level.SEVERE, null, e);
                return false;
            }
    }
    
    private boolean parseBibTex(String line){
        boolean isCustomField = true;
        line = line.replaceAll("\\s{2,}", " ");
        
        matcher = fieldTypePattern.matcher(line);
        
        if (matcher.find()){
            
            String fieldType = matcher.group(1);
            
            for (BibtexFieldType bib : BibtexFieldType.values()){
                
                if (fieldType.equals(bib.toString())){
                    isCustomField = false;
                    fieldTypeMap.put(bib, matcher.group(3));
                }
                
            }
            
            if (isCustomField){
                customFieldMap.put(matcher.group(1), matcher.group(3));
            }
            
            return true;
        }
        
        else{
            return false;
        }
    }
    
    private boolean parseEntryType(String line){
        
        matcher = entryTypePattern.matcher(line);
        if (matcher.find()){
            
            String entryTypeString = matcher.group(1);
            
            for (BibtexEntryType bib : BibtexEntryType.values()){
                if (entryTypeString.equals(bib.toString())){
                    this.entryType = bib;
                }
            }
            
            if (matcher.group(2) != null){
                fieldTypeMap.put(BibtexFieldType.KEY, matcher.group(3));
            }
            
            return true;
            
        }
        
        return false;
    }
    
    /**
     * Use this after importBibTexFile to get the parsed BibTex values in a HashMap.
     * 
     * @return HashMap containing the BibTex fields.
     */
    public HashMap <BibtexFieldType, String> getfieldTypeMap(){
        return fieldTypeMap;
    }

    /**
     * Use this after importBibTexFile to get the parsed BibTex entry type.
     * 
     * @return The BibTex entry type.
     */
    public BibtexEntryType getentryType(){
        return entryType;
    }
    
    /**
     * Use this after importBibTexFile to get the parsed non-standard BibTex values.
     * 
     * @return HashMap containing each non-standard BibTex value.
     */
    public HashMap <String, String> getcustomFieldMap(){
        return customFieldMap;
    }
    
}
