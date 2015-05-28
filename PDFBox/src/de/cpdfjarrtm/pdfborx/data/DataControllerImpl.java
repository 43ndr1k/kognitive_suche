package de.cpdfjarrtm.pdfborx.data;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataControllerImpl.
 * 
 * @TODO doku
 * @author  Philipp Kleinhenz
 */
public class DataControllerImpl implements DataController {
    private final DocumentModel model;
    
    public DataControllerImpl(DocumentModel model) {
        this.model = model;
    }
    
    @Override
    public long storeDocument(DocumentPdf document) {
        return model.add(document);
    }

    @Override
    public Map<DocumentPdf, Long> storeDocuments(Map<File, DocumentPdf> documents) {
        Map<DocumentPdf, Long> res = new HashMap<>();
        for (DocumentPdf e : documents.values()) {
            res.put(e, model.add(e));
        }
        return res;
    }

    @Override
    public long getId(DocumentPdf document) {
        return model.getId(document);
    }

    @Override
    public DocumentPdf getDocument(long id) {
        return model.get(id);
    }

    @Override
    public Map<Long, DocumentPdf> getAllDocuments() {
        return model.getAll();
    }

    @Deprecated
    @Override
    public Collection<DocumentPdf> getDocuments(Collection<Long> ids) {
        List<DocumentPdf> res = new ArrayList<>();
        for (long id : ids) {
            if (model.has(id)) {
                res.add(model.get(id));
            }
        }
        return res;
    }

    @Override
    public boolean hasDocument(long id) {
        return model.has(id);
    }

    @Override
    public boolean hasDocument(DocumentPdf doc) {
        return model.has(doc);
    }

    @Override
    public long getAmount() {
        return model.size();
    }

}
