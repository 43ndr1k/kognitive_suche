package kog.suche;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.cpdfjarrtm.pdfborx.keyword.Keyword;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import pdf.box.access.PDFBoxConnector;
import pdf.box.access.PDFDocument;
import pdf.box.access.PDFKeyword;

public class KogSucheConnector {
	
	ArrayList<PDFDocument> kogSuchePDFDocuments = new ArrayList<PDFDocument> ();
	ArrayList<PDFKeyword> kogSuchePDFDocumentKeyword = new ArrayList<PDFKeyword> ();
	List<Keyword> PDFBoxKeywords;
	
	PDFBoxConnector connection;

	public KogSucheConnector () {
		connection = new PDFBoxConnector();
	}

	public void setSendPDFs(Map<Long, DocumentPdf> allDocuments) {
		for (Map.Entry<Long, DocumentPdf> entry : allDocuments.entrySet())
		{
			PDFBoxKeywords = entry.getValue().getTags();
			
			for(int i = 0; i < PDFBoxKeywords.size(); i++)
	        {
				kogSuchePDFDocumentKeyword.add(new PDFKeyword(PDFBoxKeywords.get(i).getTerm(),PDFBoxKeywords.get(i).getWeight()));
	        }
			
			kogSuchePDFDocuments.add(new PDFDocument (entry.getValue().getFilename(), kogSuchePDFDocumentKeyword));
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
