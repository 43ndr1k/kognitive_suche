package kog.suche;

import java.util.ArrayList;
import java.util.Map;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.leipzig.htwk.pdf.box.access.PDFDocument;
import de.leipzig.htwk.pdf.box.access.Pdfboxconnector;

public class KogSucheConnector {
	
	ArrayList<PDFDocument> kogSuchePDFDocuments = new ArrayList<PDFDocument> ();
	String[] pdfTexts;
	
	Pdfboxconnector connection;

	public KogSucheConnector () {
		connection = new Pdfboxconnector();
	}

	public void setSendPDFs(Map<Long, DocumentPdf> allDocuments) {
		for (Map.Entry<Long, DocumentPdf> entry : allDocuments.entrySet())
		{
			pdfTexts = entry.getValue().getText();
			kogSuchePDFDocuments.add(new PDFDocument (entry.getValue().getPath(), pdfTexts, pdfTexts.length));
			System.out.println(entry.getValue().getPath() + " " + pdfTexts.length);
		}
	}
	
	public void sendPDFs () {
		connection.setPdfBoxDocuments(kogSuchePDFDocuments);
	}
	
	public ArrayList<PDFDocument> getSendPDFs() {
		return kogSuchePDFDocuments;
	}
	
	public void startKogSuche () {
		connection.startKogSuche();
	}
	


}
