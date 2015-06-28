package de.leipzig.htwk.pdf.box.access;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.gui.GUI;
import de.leipzig.htwk.gui.searchThread;

/**
 * Sorgt für die Annahme der PDFDocumentElemente, den start der Kog. Suche-GUI und die Weitegerabe
 * der PDFDocuments an den Controller
 * 
 * @author Fabian Freihube
 *
 */
public class Pdfboxconnector extends Application {

  static ArrayList<PDFDocument> pdfBoxDocuments = new ArrayList<PDFDocument>();
  Controller mController;

  /**
   * Startet die GUI der Kog. Suche
   */
  public void startKogSuche() {
    launch();
  }

  public ArrayList<PDFDocument> getPdfBoxDocuments() {
    return pdfBoxDocuments;
  }

  public void setPdfBoxDocuments(ArrayList<PDFDocument> pdfBoxDocuments) {
    Pdfboxconnector.pdfBoxDocuments = pdfBoxDocuments;
  }

  @Override
  public void start(Stage arg0) throws Exception {
    // TODO Auto-generated method stub

    GUI gui = GUI.getInstance();
    gui.setStartMode(1); //StartMode 1 - PDF-Suche
    mController = gui.getController();
    mController.setPDFBoxDocuments(Pdfboxconnector.pdfBoxDocuments);
    gui.reDrawHomeScreen();

  }

}
