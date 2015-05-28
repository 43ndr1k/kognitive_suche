package de.cpdfjarrtm.pdfborx.parser.biblio;

/**
 * This class contains one citation of a bibliography.
 * 
 * @author Julian Goetz
 */
public class Citation {

    private String authors;
    private String title;
    private String completeCit;
    
    /**
     * This will create a new citation.
     */
    public Citation(){
        
    }
    
    /**
     * This will create a new citation.
     * 
     * @param completeCitation The complete citation String.
     */
    public Citation(String completeCitation){
        completeCit = completeCitation;
    }
    
    /**
     * Returns the whole citation String
     * 
     * @return The citation String.
     */
    public String getcompleteCit(){
        return completeCit;
    }

    /**
     * Returns the cited title.
     * 
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title.
     * 
     * @param title The new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Sets the authors.
     * 
     * @param authors The new authors.
     */
    public void setAuthors(String authors){
        this.authors = authors;
    }
    
    /**
     * Returns the authors of a citation.
     * 
     * @return The authors.
     */
    public String getAuthors(){
        return authors;
    }
    
}
