package de.cpdfjarrtm.pdfborx.pdf.document;

/**
 * Bibtex field types according to the "official" Bibtex documentation version
 * 0.99d.
 * @author  Philipp Kleinhenz
 * @version 0.99d
 */
public enum BibtexFieldType {
    /**
     * Usually the address of the {@code PUBLISHER} or other type of institution. For
     * major publishing houses, van Leunen recommends omitting the informa-
     * tion entirely. For small publishers, on the other hand, you can help the
     * reader by giving the complete address.
     */
    ADRESS,
    /**
     * An annotation. It is not used by the standard bibliography styles, but
     * may be used by others that produce an annotated bibliography.
     */
    ANNOTE,
    /**
     * The name(s) of the author(s).
     */
    AUTHOR,
    /**
     * Title of a book, part of which is being cited.
     * For book entries, use the {@code TITLE} field instead.
     */
    BOOKTITLE,
    /**
     * A chapter (or section or whatever) number.
     */
    CHAPTER,
    /**
     * The database key of the entry being cross referenced.
     */
    CROSSREF,
    /**
     * The edition of a book - for example, "Second". This should be an
     * ordinal, and should have the first letter capitalized, as shown here; the
     * standard styles convert to lower case when necessary.
     */
    EDITION,
    /**
     * Name(s) of editor(s).
     * If there is also an {@code AUTHOR} field, then the {@code EDITOR} field
     * gives the editor of the book or collection in which the reference appears.
     */
    EDITOR,
    /**
     * How something strange has been published. The first word
     * should be capitalized.
     */
    HOWPUBLISHED,
    /**
     * The sponsoring institution of a technical report.
     */
    INSTITUTION,
    /**
     * A journal name.
     */
    JOURNAL,
    /**
     * Used for alphabetizing, cross referencing, and creating a label when the
     * {@code AUTHOR} information is missing.
     * This field should not be confused with the key that appears in the
     * \cite command and at the beginning of the database entry.
     */
    KEY,
    /**
     * The month in which the work was published or, for an unpublished
     * work, in which it was written. You should use the standard three-letter
     * abbreviation.
     */
    MONTH,
    /**
     * Any additional information that can help the reader. The first word
     * should be capitalized.
     */
    NOTE,
    /**
     * The number of a journal, magazine, technical report, or of a work in a
     * series. An issue of a journal or magazine is usually identified by its volume
     * and number; the organization that issues a technical report usually gives
     * it a number; and sometimes books are given numbers in a named series.
     */
    NUMBER,
    /**
     * The organization that sponsors a conference or that publishes a manual.
     */
    ORGANIZATION,
    /**
     * One or more page numbers or range of numbers, such as 42--111 or
     * 7,41,73--97 or 43+ (the '+' in this last example indicates pages following
     * that don't form a simple range). To make it easier to maintain Scribe-
     * compatible databases, the standard styles convert a single dash (as in
     * 7-33) to the double dash used in TeX to denote number ranges (as in
     * 7--33).
     */
    PAGES,
    /**
     * The publisher's name.
     */
    PUBLISHER,
    /**
     * The name of the school where a thesis was written.
     */
    SCHOOL,
    /**
     * The name of a series or set of books. When citing an entire book, the
     * the title field gives its title and an optional series field gives the name
     * of a series or multi-volume set in which the book is published.
     */
    SERIES,
    /**
     * The work's title.
     */
    TITLE,
    /**
     * The type of a technical report - for example, "Research Note".
     */
    TYPE,
    /**
     * The volume of a journal or multivolume book.
     */
    VOLUME,
    /**
     * The year of publication or, for an unpublished work, the year it was
     * written. Generally it should consist of four numerals, such as 1984,
     * although the standard styles can handle any year whose last four
     * nonpunctuation characters are numerals, such as '(about 1984)'.
     */
    YEAR;   
    
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
