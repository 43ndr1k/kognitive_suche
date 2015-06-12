package de.leipzig.htwk.pdf.box.access;

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

  private String docname;
  private String[] text;
  private int pages;

  public String[] getText() {
    return text;
  }

  public void setText(String[] text) {
    this.text = text;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public PDFDocument(String docname, String[] text, int pages) {
    this.docname = docname;
    this.text = text;
  }

  public String getDocname() {
    return docname;
  }

  public void setDocname(String docname) {
    this.docname = docname;
  }




}
