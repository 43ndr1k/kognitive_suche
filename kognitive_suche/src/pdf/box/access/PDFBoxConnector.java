package pdf.box.access;

import java.util.ArrayList;

import de.leipzig.htwk.controller.Controller;
import gui.GUI;

public class PDFBoxConnector {
	
	ArrayList<PDFDocument> pdfBoxDocuments = new ArrayList<PDFDocument> ();

	public void startKogSuche() {
		GUI gui = new GUI ();
		gui.setPDFBoxDocuments(pdfBoxDocuments);		
		gui.startKogSucheExtern();
	}

	public ArrayList<PDFDocument> getPdfBoxDocuments() {
		return pdfBoxDocuments;
	}

	public void setPdfBoxDocuments(ArrayList<PDFDocument> pdfBoxDocuments) {
		this.pdfBoxDocuments = pdfBoxDocuments;
	}
	
	

}
