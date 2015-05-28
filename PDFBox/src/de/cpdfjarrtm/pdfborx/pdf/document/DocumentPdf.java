package de.cpdfjarrtm.pdfborx.pdf.document;

import de.cpdfjarrtm.pdfborx.keyword.Keyword;
import de.cpdfjarrtm.pdfborx.parser.biblio.Citation;
import de.cpdfjarrtm.pdfborx.util.Language;
import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * Data class interface for PDF documents.
 * 
 * 
 * @author  Philipp Kleinhenz
 * 
 * @version 0.4.5
 */
public interface DocumentPdf {
    public static final String VERSION = "0.4.5";
    
    /**
     * Add a citation to the document.
     * @param   cit
     *          The citation to add.
     */
    void addCitation(Citation cit);
    
    /**
     * Remove a citation from the document.
     * @param   cit
     *          The citation to remove.
     */
    void removeCitation(Citation cit);
    
    
    /**
     * Get all citations of a document.
     * 
     * @return  All citations as collection.
     */
    Collection<Citation> getCitations();
    
    /**
     * Get a standard (BibTeX) field value.
     * 
     * @param   name
     *          The field type.
     * @return  The field value or {@code null} if empty.
     */
    String getField(final BibtexFieldType name);
    
    /**
     * Set a standard (BibTeX) field value.
     * 
     * @param   name
     *          The field type.
     * @param   value
     *          The field value.
     */
    void setField(final BibtexFieldType name, final String value);
    
    /**
     * Set the document's BibTeX entry type.
     * 
     * @param   type
     *          The entry type.
     * @see de.cpdfjarrtm.pdfborx.util.BibtexEntryType
     */
    void setEntryType(final BibtexEntryType type);

    /**
     * Get the document's BibTeX entry type.
     * @return 
     */
    BibtexEntryType getEntryType();
    
    /**
     * Get a non-standard (BibTeX) field.
     * 
     * @param   type
     *          The field type.
     * @return  The field value or {@code null} if empty.
     */
    String getCustomField(final String type);
    
    /**
     * Set non-standard (BibTeX) field.
     * 
     * @param   type
     *          The field type.
     * @param   value
     *          The field value.
     */
    void setCustomField(final String type, final String value);

    /**
     * Get all user tags.
     * 
     * @return  A set containing all user tags.
     */
    List<Keyword> getTags();
    
    /**
     * Add a user tag.
     * 
     * @param   tag
     *          The user tag to add.
     */
    void addTag(final Keyword tag);
    
    /**
     * Remove a user tag.
     * 
     * @param   tag 
     *          The user tag to remove.
     */
    void removeTag(final String tag);
    
    /**
     * Get the number of pages in the PDF document.
     * 
     * @return  The number of pages.
     */
    int getNumberOfPages();
    
    /**
     * Get the PDF documents version in major.minor format.
     * 
     * @return  The PDF version in major.minor format.
     */
    String getVersion();
    
    /**
     * Gets the plain text extracted from the given page.
     * 
     * @param   pageNumber
     *          The page to get the text from.
     * @return  The plain text found on the given page.
     */
    String getText(final int pageNumber);
    
    /**
     * Get the plain text extracted from the entire document.
     * 
     * @return  The text as array with the page number as index.
     */
    String[] getText();
    
    /**
     * Get the type of all non-standard fields present in the document.
     * 
     * @return  All present non-standard fields.
     */
    Set<String> getCustomFields();
    
    /**
     * Get the type of all standard fields present in the document.
     * 
     * @return  All present standard fields.
     */
    Set<BibtexFieldType> getFields();
    
    /**
     * Check if the document is encrypted.
     * 
     * @return  {@code true} if the document is encrypted, {@code false} if not.
     */
    boolean isEncrypted();
    
    /**
     * Get the file name.
     * 
     * @return  The filename of the original document.
     */
    String getFilename();
    
    /**
     * Get the document's language.
     * 
     * @return  The documents language.
     */
    Language getLanguage();
    
    /**
     * Set the document's language.
     * 
     * @param   lang 
     *          The language to set.
     */
    void setLanguage(final Language lang);

    /**
     * Get the document's abstract, if available.
     * 
     * @return  The document's abstract, or null if not available.
     */
    String getAbstract();
    
    /**
     * Set the document's abstract.
     * 
     * @param   text
     *          The text to set as abstract.
     */
    void setAbstract(final String text);
    
    /**
     * Set the documents id.
     * 
     * @param   id
     *          The id to set.
     */
    void setId(final long id);
    
    /**
     * Get all fields that have been changed since the last call.
     * @param   consume
     *          If the the set of dirty fields should be cleared.
     * @return 
     */
    Collection<String> getDirtyFields(final boolean consume);
    
    /**
     * Add a DocumentListener that will be notified of dirty fields.
     * 
     * @param   listener 
     *          The listener to add.
     */
    void addListener(DocumentListener listener);
    
}
