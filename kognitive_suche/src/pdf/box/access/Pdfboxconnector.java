package pdf.box.access;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import de.leipzig.htwk.controller.Controller;
import gui.GUI;

public class Pdfboxconnector extends Application {

  ArrayList<PDFDocument> pdfBoxDocuments = new ArrayList<PDFDocument>();
  Controller mController;

  public void startKogSuche() {
    //GUI gui = GUI.getInstance();
    //gui.setPDFBoxDocuments(pdfBoxDocuments);
    //gui.startKogSucheExtern();
    
    launch();
   
    
  }

  public ArrayList<PDFDocument> getPdfBoxDocuments() {
    return pdfBoxDocuments;
  }

  public void setPdfBoxDocuments(ArrayList<PDFDocument> pdfBoxDocuments) {
    this.pdfBoxDocuments = pdfBoxDocuments;
  }

  @Override
  public void start(Stage arg0) throws Exception {
    // TODO Auto-generated method stub
    GUI gui = GUI.getInstance();
    mController = gui.getController();
    mController.setPDFBoxDocuments(pdfBoxDocuments);
  }



}
