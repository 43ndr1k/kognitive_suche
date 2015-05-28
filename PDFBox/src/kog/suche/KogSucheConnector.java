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
	ArrayList<Pdfkeyword> kogSuchePDFDocumentKeyword = new ArrayList<Pdfkeyword> ();
	List<Keyword> PDFBoxKeywords;
	
	Pdfboxconnector connection;

	public KogSucheConnector () {
		connection = new Pdfboxconnector();
	}

	public void setSendPDFs(Map<Long, DocumentPdf> allDocuments) {
		for (Map.Entry<Long, DocumentPdf> entry : allDocuments.entrySet())
		{
			PDFBoxKeywords = entry.getValue().getTags();
			
			for(int i = 0; i < PDFBoxKeywords.size(); i++)
	        {
				kogSuchePDFDocumentKeyword.add(new Pdfkeyword(PDFBoxKeywords.get(i).getTerm(),PDFBoxKeywords.get(i).getWeight()));
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
