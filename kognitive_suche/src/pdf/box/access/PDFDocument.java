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
  ArrayList<PDFKeyword> Keywords = new ArrayList<PDFKeyword>();

  public PDFDocument(String docname, ArrayList<PDFKeyword> Keywords) {
    this.docname = docname;
    this.Keywords = Keywords;
  }

  public String getDocname() {
    return docname;
  }

  public void setDocname(String docname) {
    this.docname = docname;
  }

  public ArrayList<PDFKeyword> getKeywords() {
    return Keywords;
  }

  public void setKeywords(ArrayList<PDFKeyword> keywords) {
    Keywords = keywords;
  }


}
