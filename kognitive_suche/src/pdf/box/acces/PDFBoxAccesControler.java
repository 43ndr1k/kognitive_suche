package pdf.box.acces;

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

  ArrayList<PDFDocument> PDFDoc = new ArrayList<PDFDocument>();

  String docname;

  public PDFBoxAccesControler(String pdfbox_path) {

    PDFBox PDFBox = new PDFBox(pdfbox_path);

    this.PDFDoc = PDFBox.getKeywords();


  }

  public ArrayList<PDFDocument> getDocKeywords() {
    return PDFDoc;
  }



}
