package pdf.box.access;

import java.util.ArrayList;

public class Pdfboxaccesscontroller {

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

  ArrayList<PDFDocument> pdfdoc = new ArrayList<PDFDocument>();

  String docname;

  public Pdfboxaccesscontroller(String pdfbox_path) {

    PDFBox PDFBox = new PDFBox(pdfbox_path);

    this.pdfdoc = PDFBox.getKeywords();


  }

  public ArrayList<PDFDocument> getDocKeywords() {
    return pdfdoc;
  }



}
