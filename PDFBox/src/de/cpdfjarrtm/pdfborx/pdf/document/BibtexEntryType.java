package de.cpdfjarrtm.pdfborx.pdf.document;

/**
 * Bibtex Entry types according to the "official" Bibtex documentation version
 * 0.99d.
 * @author  Philipp Kleinhenz
 * @version 0.99d
 */
public enum BibtexEntryType {
    ARTICLE,        // author, title, journal, year
    BOOK,           // author | editor, title, publisher, year
    BOOKLET,        // title
    CONFERENCE,     // author, title, booktitle, year
    INBOOK,         // author | editor, title, chapter &| pages, publisher, year
    INCOLLECTION,   // author, title, booktitle, publisher, year
    INPROCEEDINGS,  // author, title, booktitle, year
    MANUAL,         // title
    MASTERTHESIS,   // author, title, school, year
    MISC,           // 
    PHDTHESIS,      // author, title, school, year
    PROCEEDINGS,    // title, year
    TECHREPORT,     // author, title, institution, year
    UNPUBLISHED     // author, title, note
}
