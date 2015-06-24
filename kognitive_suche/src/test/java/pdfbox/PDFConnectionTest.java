package pdfbox;

import java.util.ArrayList;

import de.leipzig.htwk.gui.GUI;
import de.leipzig.htwk.pdf.box.access.PDFDocument;

public class PDFConnectionTest {

  public static void main(String[] args) {

GUI gui = new GUI();
ArrayList<PDFDocument> pdfs = new ArrayList<PDFDocument>();
PDFDocument pdf = new PDFDocument(null, args, 0);
System.out.println("hier");

  }

}
