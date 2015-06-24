package pdfbox;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.gui.GUI;
import de.leipzig.htwk.pdf.box.access.PDFDocument;
import de.leipzig.htwk.pdf.box.access.Pdfboxconnector;

public class PDFConnectionTest extends Application {
  public static void main(String[] args) {

    System.out.println("hier");
    String title = "C:/waslabaschdu/du/da/file.pdf";
    title = title.substring(title.lastIndexOf('/') + 1, title.lastIndexOf('.'));
    System.out.println(title);
    launch(args);
  }

  @Override
  public void start(Stage arg0) throws Exception {
    GUI gui = GUI.getInstance();
    gui.setStartMode(1); // StartMode 1 -PDFSuche
    Controller mController = gui.getController();
    mController.setPDFBoxDocuments(getPDFS());
    gui.reDrawHomeScreen();
  }

  private ArrayList<PDFDocument> getPDFS() {

    ArrayList<PDFDocument> pdfs = new ArrayList<PDFDocument>();
    String url = "/files/HTWK-WLAN-eduroam-Windows7.pdf";
    String[] text1 = {"Hier", "Könnte", "Ihre", "Werbung", "Stehen"};
    PDFDocument pdf = new PDFDocument(url, text1, 5);
    pdfs.add(pdf);

    String[] text2 = {"Das", "ist", "ein", "guter", "text"};
    pdf = new PDFDocument(url, text2, 5);
    pdfs.add(pdf);

    String[] text3 = {"Es", "Mangelt", "nicht", "an", "Wörtern"};
    pdf = new PDFDocument(url, text3, 5);
    pdfs.add(pdf);

    String[] text4 = {"Blau", "ist", "eine", "Schöne", "Farbe"};
    pdf = new PDFDocument(url, text4, 5);
    pdfs.add(pdf);

    return pdfs;

  }

}
