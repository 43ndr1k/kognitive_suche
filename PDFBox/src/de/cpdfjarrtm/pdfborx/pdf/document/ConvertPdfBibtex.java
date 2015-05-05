package de.cpdfjarrtm.pdfborx.pdf.document;

/**
 * Helper functions that map/convert Pdf metadata fields to bibtex fields.
 * 
 * @author  Philipp Kleinhenz
 */
public final class ConvertPdfBibtex {
    private ConvertPdfBibtex() {}
    
    public static BibtexFieldType fieldPdfToBibtex(final PdfMetadataType type) {
        switch (type) {
            case TITLE:
                return BibtexFieldType.TITLE;
            case AUTHOR:
                return BibtexFieldType.AUTHOR;
            case SUBJECT:
            case KEYWORDS:
            case CREATOR:
            case PRODUCER:
            case CREATIONDATE:
            case MODDATE:
            case TRAPPED:
            default:
                return null;
        }
    }
    
    public static BibtexFieldType fieldPdfToBibtex(final String type) {
        PdfMetadataType realType;
        if (isMapped(type)) {
            realType = PdfMetadataType.valueOf(type.toUpperCase());
            return fieldPdfToBibtex(realType);
        }
        return null;
    }
    
    public static boolean isMapped(final String type) {
        for (BibtexFieldType b : BibtexFieldType.values()) {
            if (b.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

}
