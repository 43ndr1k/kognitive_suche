package de.cpdfjarrtm.pdfborx.pdf.document;

/**
 * Pdf metadata fields according to the pdf standard version 1.7.
 * 
 * @author  Philipp Kleinhenz
 * @version 1.7
 */
public enum PdfMetadataType {
    TITLE,          // string
    AUTHOR,         // string
    SUBJECT,        // string
    KEYWORDS,       // string
    CREATOR,        // string
    PRODUCER,       // string
    CREATIONDATE,   // date (D:YYYYMMDDHHmmSSOHH'mm)
    MODDATE,        // date (D:YYYYMMDDHHmmSSOHH'mm)
    TRAPPED         // true, false, unknown
}
