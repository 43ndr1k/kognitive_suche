package pdf.box.access;

import java.util.ArrayList;

public class PDFDocument {

  /**
   * PDF Object
   *
   * Includes the name of the PDF and an object of typ PDFKeyword.
   * 
   * @param docname: the name of the PDF-file.
   * @param keywords: ArrayList of typ PDFKeyword.
   * 
   * @author Fabian Freihube
   */

  String docname;
  ArrayList<Pdfkeyword> keyWords = new ArrayList<Pdfkeyword>();

  public PDFDocument(String docname, ArrayList<Pdfkeyword> keywords) {
    this.docname = docname;
    this.keyWords = keywords;
  }

  public String getDocname() {
    return docname;
  }

  public void setDocname(String docname) {
    this.docname = docname;
  }

  public ArrayList<Pdfkeyword> getKeywords() {
    return keyWords;
  }

  public void setKeywords(ArrayList<Pdfkeyword> keywords) {
    keyWords = keywords;
  }


}
