package GUI;
import java.awt.event.ActionListener;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GUI Erstellung
 * @author Sebastian H�gelmann
 * @version 0.3
 * Damit die JavaFX Application funktioniert, m�sst ihr rechtsklick auf GUI.java/Build Path/Configure Build Path...
 * Dann auf den Reiter: Librariers und dort Add External JARs...
 * Und dann in eurem C:\Program Files\Java\jdk1.7.0_51\jre\lib\jfxrt.jar hinzuf�gen! Oder �hnliche 1.7.0_xx Versionen, aber nicht 1.8!
 */

public class GUI extends Application{
	
	private BorderPane pane1 = new BorderPane();
	private int anzkat = 4;
	private GridPane pane2 = new GridPane();
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {

		HBox hbox1 = new HBox();
		
		pane1.setCenter(hbox1);

		hbox1.setAlignment(Pos.CENTER);
		
		Scene start1 = new Scene(pane1);
		
		stage.setTitle("Kognitive Suche");
		stage.centerOnScreen();
		stage.setWidth(1024);
		stage.setHeight(768);
		stage.setScene(start1);
		stage.setResizable(false);
		stage.show();
		
		hbox1.setPadding(new Insets(15,30,15,30));				/*Bestimmt den Abstand vom Rand nach Innen*/
		hbox1.setSpacing(20);									/*Bestimmt den Abstand der Elemente voneinander*/
		hbox1.setStyle("-fx-background-color: #EEEEEE;");		/*Bestimmt die Hintergrundfarbe*/
		
		TextField suchleiste = new TextField();					
		suchleiste.setMaxWidth(200);
		
		Button sucheF = new Button("Suche in F");
		
		sucheF.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent sucheF) {
					textfield();								/*Ruft die Methode zur Generierung Textfelder auf*/
				}
			});
		
		Button sucheP = new Button("Suche in P");
		sucheP.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent sucheP) {
<<<<<<< HEAD
				System.out.println("Button wurde geklickt");
				/* Suche starten
				 * String an PDF Box - Textfield = "suchleiste"
				 * Abgleich des Suchstrings mit PDF Stichw�rtern
				 * R�ckgabe der Tags von der PDF Box
				 * Syso der Tags in die n�chsten kommenden Felder
				 * Aufruf des n�chsten Fensters
				 */
=======
					//textfield();
>>>>>>> 91ae5f748dd2dac01df37d3c852e723fb0bc4235
				
			}
			
		});
		hbox1.getChildren().addAll(suchleiste,sucheF,sucheP);
	}
	
	public void textfield(){
		TextArea textfield[] = new TextArea[25];
<<<<<<< HEAD
		//GridPane pane2 = new GridPane();
		System.out.println("Button Action ausgef�hrt");
		/* Suche starten
		 * String an Faroo - Textfield = "suchleiste"
		 * Faroo R�ckgabe
		 * primitiver Suchalgorithmus arbeitet
		 * R�ckgabe der Tags
		 * Ausgabe der Tags in die textfield[i]
		 */
	
		//int anzKat=4; /*Hier wird die Kategorieanzahl �bergeben*/
		//Test mit 4 Kategorien
		for (int i = 0;i<2;i++){
			for (int j = 0;j<2;j++){
				textfield[i] = new TextArea("Ich bin das Textfeld in der Spalte "+i+" Zeile "+j+" !\n"+"Es k�nnen Tags per Hand gel�scht wurden und mit Enter die Kategorie ausw�hlen");
				textfield[i].setEditable(true); /*Vorerst Editable,um Tags per hand rauszufiltern.*/
=======
		System.out.println("Button Action ausgef�hrt");
		
		/* Suchbegriffe aus der Suchleiste auslesen und an den Controller �bergeben @Parameter
		 * R�ckgabe der Tags
		 * Tags sind in String Array gespeichert?
		 * Anzahl String Arrays = Anzahl Kategorie @Parameter anzKat
		 */
	
		/*Hier wird die Kategorieanzahl �bergeben*/
		/*int anzKat=4;*/
		/*Test mit 4 Kategorien*/
		for (int i = 0;i<2;i++){
			for (int j = 0;j<2;j++){
				textfield[i] = new TextArea("Ich bin das Textfeld in der Spalte "+i+" Zeile "+j+" !\n"+"Es k�nnen Tags per Hand gel�scht werden und mit Enter die Kategorie ausw�hlen");
				//textfield[i] = new TextArea(""+tagarray[i]);
				/*Vorerst Editable,um Tags per hand rauszufiltern.*/
				textfield[i].setEditable(true);
>>>>>>> 91ae5f748dd2dac01df37d3c852e723fb0bc4235
				textfield[i].setOnKeyPressed(new EventHandler<KeyEvent>() 
						{
					    	@Override
					    	public void handle(KeyEvent keyEvent) 
					    	{
					    		if(keyEvent.getCode() == KeyCode.ENTER)
					    		{
<<<<<<< HEAD
					    			System.out.println("Enter wurde gedr�ckt!");
					    			/* Tags + Suchstring an Faroo()
					    			 * Faroo NodeList R�ckgabe()
					    			 * primitiver Suchalgorithmus auf NodeList()
					    			 * Tags bereit machen f�r Textfields()
					    			 * Kategorieanzahl �bergeben welche sich ergibt()
					    			 * 
=======
					    			System.out.println("Enter wurde gedr�ckt!");
					    			/* Tags + Suchstring an Controller @Parameter
					    			 * R�ckgabe der Tags
					    			 * Tags sind in String Array gespeichert?
					    			 * Anzahl String Arrays = Anzahl Kategorie @Parameter anzKat
>>>>>>> 91ae5f748dd2dac01df37d3c852e723fb0bc4235
					    			 */
					    			/*Zum Test 1 Kategorie*/
					    			anzkat = 1;
					    			
					    			if (anzkat < 2){
					    				ergebnisausgabe(); /*Ausgabe der Webseiten*/
					    			}
					    			else	{textfield();} /*Kategorien mit Tags erstellen*/
					    			
					    		}
					    	}
						});
				pane2.add(textfield[i], i, j);
				System.out.println("Feld erstellt!");
			}
		}
<<<<<<< HEAD
		//Scene start1 = new Scene(pane2);
		//System.out.println("Pane hinzugef�gt!");
=======
>>>>>>> 91ae5f748dd2dac01df37d3c852e723fb0bc4235
		pane2.setAlignment(Pos.CENTER);
		pane1.setCenter(pane2);
	}
	
	public void ergebnisausgabe(){
		VBox vbox1 = new VBox();
		Hyperlink link[] = new Hyperlink[25];
		Label label[] = new Label[25];
		int anzsucherg = 3;
		for (int k=0; k<anzsucherg;k++){
			link[k] = new Hyperlink("www.oracle.com");	/*getHyperlink from Nodelist*/
			label[k] = new Label("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At");
			vbox1.getChildren().addAll(link[k],label[k]);
			label[k].setMaxSize(300, 300);
			label[k].setWrapText(true);
			label[k].setStyle("-fx-label-padding: 0 0 20 0;");	/*top right bottom left*/
			
			/*link[k].setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent o) {
	                    getHostServices().showDocument(link[k].getText());
	                }
			});*/
		}
		vbox1.setMaxSize(1000, 700);
		/*vbox1.setSpacing(20);*/
		pane1.getChildren().clear();
		pane1.setCenter(vbox1);
	}
	
	
}
