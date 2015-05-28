package de.cpdfjarrtm.pdfborx.pdf.document;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import de.cpdfjarrtm.pdfborx.keyword.Keyword;
import de.cpdfjarrtm.pdfborx.parser.biblio.Citation;
import de.cpdfjarrtm.pdfborx.util.Language;
import de.cpdfjarrtm.pdfborx.util.VecTextField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;

/**
 * A pdfborx document implementation that uses a lucene document as backend.
 * 
 * @author  Philipp Kleinhenz
 */
public class DocumentLucene implements DocumentPdf {
    private final List<Citation> citations;
    private final Document doc;
    private final String[] text;
    private final String version;
    private final boolean isEncrypted;
    private BibtexEntryType entryType;
    private final Set<String> dirty;
    private final int hashCode;
    private final List<Keyword> tags;
    private final Set<DocumentListener> dirtyListeners;
    
    /**
     * Create a new document.
     * 
     * @param   bibtexfields
     *          All (at creation time) available bibtex fields.
     * @param   id
     *          The docuemnts id. (Use -1 if unknown at creation time)
     * @param   filename
     *          The documents filename, without path. Used for hashing.
     * @param   text
     *          The documents plain text as String array sorted by page.
     * @param   version
     *          The pdf version number.
     * @param   isEncrypted
     *          If the pdf is encrypted.
     */
    public DocumentLucene(
            Map<BibtexFieldType, String> bibtexfields,
            final long id,
            final String filename,
            final String[] text,
            final String version,
            final boolean isEncrypted) {
        
        this.doc = new Document();
        
        this.text = text.clone();
        this.isEncrypted = isEncrypted;
        this.version = version;
        this.dirty = new HashSet<>();

        for (Map.Entry<BibtexFieldType, String> e : bibtexfields.entrySet()) {
            this.doc.add(new TextField(e.getKey().name(), e.getValue(), Field.Store.YES));
        }
        
        String fullText = "";
        for (String t : text) {
            fullText += t;
        }

        this.doc.add(new LongField(DocumentFieldType.ID_TYPE.name(), id, Field.Store.YES));
        this.doc.add(new StringField(DocumentFieldType.FILENAME_TYPE.name(), filename, Field.Store.YES));
        this.doc.add(new VecTextField(DocumentFieldType.FULL_TEXT_TYPE.name(), fullText, Field.Store.NO));
        this.doc.add(new StringField(BibtexFieldType.KEY.name(), filename, Field.Store.YES));
        
        this.doc.add(new StringField(DocumentFieldType.LANGUAGE_TYPE.name(),
                Language.detectLanguage(fullText).name(), Field.Store.YES));
        
        String hashing = fullText;
        hashing += getField(BibtexFieldType.AUTHOR);
        hashing += getField(BibtexFieldType.TITLE);
        hashing += getFilename();
        this.hashCode = hashing.hashCode();
        this.doc.add(new StringField(DocumentFieldType.HASH_TYPE.name(), String.valueOf(hashCode), Field.Store.YES));
        
        // @TODO: tags weight momentan keine lucene payload, also keine auswirkung auf suche!
        tags = PdfBorx.getInstance().getKeywordGenerator().getKeywords(this);
        for (Keyword k : tags) {
            this.doc.add(new VecTextField(DocumentFieldType.USER_TAG_TYPE.name(), k.getTerm(), Field.Store.NO));
        }
        
        this.citations = new ArrayList<>();
        this.dirtyListeners = new HashSet<>();
    }
    
    @Override
    public void addCitation(Citation cit) {
        removeCitation(cit);
        citations.add(cit);
        String citconstr = "";
        citconstr = cit.getTitle() + " " + cit.getAuthors();
        doc.add(new TextField(DocumentFieldType.CITATION_TYPE.name(), citconstr, Field.Store.YES));
        _addDirty(DocumentFieldType.CITATION_TYPE.name());
    }
    
    @Override
    public void removeCitation(Citation cit) {
        String complete = cit.getcompleteCit();
        List<Citation> toRemove = new ArrayList<>();
        doc.removeFields(DocumentFieldType.CITATION_TYPE.name());
        for (Citation c : citations) {
            if (complete.equals(c.getcompleteCit())) {
                toRemove.add(c);
            } else {
                doc.add(new TextField(DocumentFieldType.CITATION_TYPE.name(),
                        c.getTitle() + " " + c.getAuthors(), Field.Store.YES));
            }
        }
        
        for (Citation c : toRemove) {
            citations.remove(c);
        }
        _addDirty(DocumentFieldType.CITATION_TYPE.name());
    }
    
    @Override
    public Collection<Citation> getCitations() {
        ArrayList<Citation> ret = new ArrayList<>(citations.size());
        for (Citation cit : citations) {
            ret.add(cit);
        }
        return ret;
    }
    
    @Override
    public String getAbstract() {
        return _getField(DocumentFieldType.ABSTRACT_TYPE.name());
    }
    
    @Override
    public void setAbstract(final String text) {
        _setField(DocumentFieldType.ABSTRACT_TYPE.name(), text);
    } 
    
    @Override
    public int getNumberOfPages() {
        return text.length; 
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getText(final int pageNumber) {
        if (pageNumber > 0 && pageNumber <= text.length) {
            return text[pageNumber-1];
        } else {
            return null;
        }
    }

    @Override
    public String[] getText() {
        return text.clone();
    }

    @Override
    public Set<BibtexFieldType> getFields() {
        HashSet<BibtexFieldType> ret = new HashSet<>();
        for (IndexableField f : doc.getFields()) {
            for (BibtexFieldType e : BibtexFieldType.values()) {
                if (f.name().equals(e.name())) {
                    ret.add(BibtexFieldType.valueOf(f.name()));
                }
            }
        }
        return ret;
    }

    @Override
    public String getField(final BibtexFieldType field) {
        return _getField(field.name());
    }

    @Override
    public boolean isEncrypted() {
        return isEncrypted;
    }

    @Override
    public String getFilename() {
        return _getField(DocumentFieldType.FILENAME_TYPE.name());
    }

    @Override
    public Language getLanguage() {
        return Language.valueOf(_getField(DocumentFieldType.LANGUAGE_TYPE.name()));
    }

    @Override
    public void setField(final BibtexFieldType field, String value) {
        _setField(field.name(), value);
        
    }

    @Override
    public void setEntryType(final BibtexEntryType type) {
        entryType = type;
    }
    
    @Override
    public BibtexEntryType getEntryType() {
        return entryType;
    }

    @Override
    public String getCustomField(final String type) {
        return _getField(DocumentFieldType.CUSTOM_TYPE_PREFIX.name() + type);
    }

    @Override
    public void setCustomField(final String type, final String value) {
        String realType = DocumentFieldType.CUSTOM_TYPE_PREFIX.name() + type;
        _setField(realType, value);
    }

    @Override
    public List<Keyword> getTags() {
        return Collections.unmodifiableList(tags);
    }

    @Override
    public void addTag(final Keyword tag) {
        for (Keyword k : tags) {
            if (k.getTerm().equals(tag.getTerm())) {
                tags.remove(k);
            }
        }
        tags.add(tag);
        doc.removeFields(DocumentFieldType.USER_TAG_TYPE.name());
        for (Keyword k : tags) {
            doc.add(new VecTextField(DocumentFieldType.USER_TAG_TYPE.name(), k.getTerm(), Field.Store.NO));
        }
        _addDirty(DocumentFieldType.USER_TAG_TYPE.name());
    }
    
    @Override
    public void removeTag(final String tag) {
        for (Keyword k : tags) {
            if (k.getTerm().equals(tag)) {
                tags.remove(k);
            }
        }
        doc.removeFields(DocumentFieldType.USER_TAG_TYPE.name());
        for (Keyword k : tags) {
            doc.add(new VecTextField(DocumentFieldType.USER_TAG_TYPE.name(), k.getTerm(), Field.Store.NO));
        }
        _addDirty(DocumentFieldType.USER_TAG_TYPE.name());
    }
    
    private void _addDirty(String fieldname) {
        dirty.add(fieldname);
        for (DocumentListener o : dirtyListeners) {
            o.update(this);
        }
        getDirtyFields(true);
    }

    @Override
    public Set<String> getCustomFields() {
        HashSet<String> ret = new HashSet<>();
        for (IndexableField f : doc.getFields()) {
            for (BibtexFieldType e : BibtexFieldType.values()) {
                if (!f.name().equals(e.name())) {
                    ret.add(f.name());
                }
            }
        }
        return ret;
    }

    @Override
    public void setLanguage(final Language lang) {
        _setField(DocumentFieldType.LANGUAGE_TYPE.name(), lang.name());
    }
    
    @Override
    public Collection<String> getDirtyFields(boolean consume) {
        Collection<String> ret = new HashSet<>(dirty);
        if (consume) {
            dirty.clear();
        }
        return ret;
    }
    
    public Document getLuceneDocument() {
        return doc;
    }

    private void _setField(final String name, final String value) {
        doc.removeField(name);
        doc.add(new TextField(name, value, Field.Store.YES));
        _addDirty(name);
        this.hashCode();
    }
    
    private String _getField(final String name) {
        IndexableField f = doc.getField(name);
        if (f != null) {
            return f.stringValue();
        }
        return null;
    }
    
    @Override
    public void setId(long id) {
        doc.removeField(DocumentFieldType.ID_TYPE.name());
        doc.add(new LongField(DocumentFieldType.ID_TYPE.name(), id, Field.Store.YES));
        _addDirty(DocumentFieldType.ID_TYPE.name());
    }
    
    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocumentLucene other = (DocumentLucene) obj;
        if (this.hashCode != other.hashCode) {
            return false;
        }
        return true;
    }

    @Override
    public void addListener(DocumentListener listener) {
        dirtyListeners.add(listener);
    }
    
}
