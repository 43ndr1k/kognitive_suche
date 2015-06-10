package kog.suche;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.cpdfjarrtm.pdfborx.keyword.Keyword;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import pdf.box.access.Pdfboxconnector;
import pdf.box.access.PDFDocument;
import pdf.box.access.Pdfkeyword;

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
			kogSuchePDFDocuments.add(new PDFDocument (entry.getValue().getFilename(), pdfTexts, pdfTexts.length));
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
