package pdfbox;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import de.leipzig.htwk.gui.GUI;
import de.leipzig.htwk.pdf.box.access.PDFDocument;

public class PDFConnectionTest extends Application {
  public static void main(String[] args) {

    System.out.println("hier");
    launch(args);
  }

  @Override
  public void start(Stage arg0) throws Exception {
    GUI gui = GUI.getInstance();
    gui.setStartMode(1); // StartMode 1 -WebSuche
  }

  private ArrayList<PDFDocument> getPDFS() {

    ArrayList<PDFDocument> pdfs = new ArrayList<PDFDocument>();
    String[] text = {"Hier", "Könnte", "Ihre", "Werbung", "Stehen"};
    PDFDocument pdf = new PDFDocument("Räuber Hotzenplotz", text, 5);
    return null;

  }

}
