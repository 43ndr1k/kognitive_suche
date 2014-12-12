package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import pdfBoxAcces.PDFBoxAccesControler;
import pdfBoxAcces.PDFDocument;
import Faroo.API;
import Faroo.ConfigFileManagement;
/**
 * Dummy Controller<br>
 * TODO: Create Dummy methods to create a simple workflow
 * 
 * The following methods are suggestions only
 * @author tim.delle@stud.htwk-leipzig.de
 */
public class Controller {

	/* CONSTANTS */
	private int test1 = 1;
    String pdfbox_path = "Data/PDFBox/pdfbox.jar"; //absolute Path to pdfbox.jar

	/* VARIABLES */
	private String Suchstring; // Die Eingabe an Farroo und pdfbox
	private String Config; //Erstmal nur zum Test, wird entweder 1 Array oder mehrere Variablen
	
	/* CONSTRUCTOR */
	public Controller(String Suchstring, String Config){//Nen Test ob wir Netz haben w�re vlt. noch n�tzlich
		this.Suchstring = Suchstring;
		this.Config = Config;
		
	}
	
	
	/* CODE - Auswahl Farroo, PDFBox, Einfacher Suchalg., etc. */
	/*switch (test1) {
	
		case1://Farroo
			API api = new API(config.getKey(Suchstring));
			break;
		case2://Pdfbox
			break;
		case3://
			break;
		case4:
			break; Schlechte IDee*/
		

	
	/* METHODS */
	private void getconfig(){
		
	}
	//------------------------------------------------
	
	private Object evaluateGUI(Object pObject){
		return null;
	}
	
	private Object queryFaroo(Object pObject){
		return null;
	}

	private Object queryPdfBox(Object pObject){
	    PDFBoxAccesControler PDFBoxAcces = new PDFBoxAccesControler(pdfbox_path); //Runs the PDFBox tool. CARE: It takes about 30 seconds until PDFBox is ready.
	    return PDFBoxAcces.getDocKeywords();
	    }
	
	private Object startSimpleAlgorithmn(Object pObject){
		return null;
	}
	
	private Object startComplexAlgorithmn(Object pObject){
		return null;
	}
	
}
