package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

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
	/* VARIABLES */
	private String Suchstring; // Die Eingabe an Farroo und pdfbox
	private String Config; //Erstmal nur zum Test, wird entweder 1 Array oder mehrere Variablen
	
	/* CONSTRUCTOR */
	public Controller(String Suchstring, String Config){//Nen Test ob wir Netz haben wäre vlt. noch nützlich
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
		
	}
	
	
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
		return null;
	}
	
	private Object startSimpleAlgorithmn(Object pObject){
		return null;
	}
	
	private Object startComplexAlgorithmn(Object pObject){
		return null;
	}
	
}
