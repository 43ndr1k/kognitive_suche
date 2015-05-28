package de.cpdfjarrtm.pdfborx.util;

import java.io.Reader;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FieldType;

/**
 * Text field with termvector.
 * 
 * <a href="https://stackoverflow.com/questions/11945728/how-to-use-termvector-lucene-4-0">
 *  https://stackoverflow.com/questions/11945728/how-to-use-termvector-lucene-4-0
 * </a>
 * @author  Philipp Kleinhenz
 */
public class VecTextField extends Field {

    /* Indexed, tokenized, not stored. */
    public static final FieldType TYPE_NOT_STORED = new FieldType();

    /* Indexed, tokenized, stored. */
    public static final FieldType TYPE_STORED = new FieldType();

    static {
        TYPE_NOT_STORED.setIndexed(true);
        TYPE_NOT_STORED.setTokenized(true);
        TYPE_NOT_STORED.setStoreTermVectors(true);
        TYPE_NOT_STORED.setStoreTermVectorPositions(true);
        TYPE_NOT_STORED.freeze();

        TYPE_STORED.setIndexed(true);
        TYPE_STORED.setTokenized(true);
        TYPE_STORED.setStored(true);
        TYPE_STORED.setStoreTermVectors(true);
        TYPE_STORED.setStoreTermVectorPositions(true);
        TYPE_STORED.freeze();
    }

    public VecTextField(String name, Reader reader, Store store) {
        super(name, reader, store == Store.YES ? TYPE_STORED : TYPE_NOT_STORED);
    }

    public VecTextField(String name, String value, Store store) {
        super(name, value, store == Store.YES ? TYPE_STORED : TYPE_NOT_STORED);
    }

    public VecTextField(String name, TokenStream stream) {
        super(name, stream, TYPE_NOT_STORED);
    }
}