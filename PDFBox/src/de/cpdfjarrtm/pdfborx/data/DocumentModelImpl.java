package de.cpdfjarrtm.pdfborx.data;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.util.HashMap;
import java.util.Map;

/**
 * DocumentModelImpl.
 * 
 * @TODO: datenbank cache, dokumente in model abgelegegt, in db speichern,
 * falls nicht im model, aus db holen. erledigt datacontroller.
 * 
 * @author  Philipp Kleinhenz
 */
public class DocumentModelImpl implements DocumentModel {
    private final Map<Long, DocumentPdf> idDoc;
    private final Map<DocumentPdf, Long> docId;
    private long id;
    
    public DocumentModelImpl() {
        idDoc = new HashMap<>();
        docId = new HashMap<>();
        id = 0;
    }

    @Override
    public long add(DocumentPdf document) {
        if (docId.containsKey(document)) {
            return docId.get(document);
        } else {
            id++;
            idDoc.put(id, document);
            docId.put(document, id);
            document.setId(id);
            return id;
        }
    }

    @Override
    public Map<Long, DocumentPdf> getAll() {
        Map<Long, DocumentPdf> res = new HashMap();
        res.putAll(idDoc);
        return res;
    }

    @Override
    public DocumentPdf get(long id) {
        return idDoc.get(id);
    }
    
    @Override
    public boolean has(long id) {
        return idDoc.containsKey(id);
    }

    @Override
    public boolean has(DocumentPdf document) {
        return docId.containsKey(document);
    }

    @Override
    public long size() {
        return idDoc.size();
    }

    @Override
    public long getId(DocumentPdf document) {
        return docId.get(document);
    }
    
}
