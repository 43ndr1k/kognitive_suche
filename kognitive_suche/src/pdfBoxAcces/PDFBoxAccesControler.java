package pdfBoxAcces;

import java.util.ArrayList;

public class PDFBoxAccesControler {

  /**
   * PDF Box Acces Controler
   *
   * Creates new PDFBox Process Gets KeyWords from PDFBox
   * 
   * @param pdfbox_path absolute path to pdfbox.jar.
   * @param docname Name of PDF-File.
   * 
   * @return Object with docname and keywords
   * 
   * @author Fabian Freihube
   */

  ArrayList<PDFKeyword> Keywords = new ArrayList<PDFKeyword>();

  String docname;

  public PDFBoxAccesControler(String pdfbox_path) {

    PDFBox PDFBox = new PDFBox(pdfbox_path);

    this.Keywords = PDFBox.getKeywords();
    this.docname = PDFBox.getDoc();

  }

  public PDFDocument getDocKeywords() {
    return new PDFDocument(docname, Keywords);
  }



}
