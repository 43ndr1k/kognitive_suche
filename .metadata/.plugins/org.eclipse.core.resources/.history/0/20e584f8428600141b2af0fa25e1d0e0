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
 * 
 * @author Sebastian Hügelmann
 * @version 0.2
 * Damit die JavaFX Application funktioniert, müsst ihr rechtsklick auf GUI.java/Build Path/Configure Build Path...
 * Dann auf den Reiter: Librariers und dort Add External JARs...
 * Und dann in eurem C:\Program Files\Java\jdk1.7.0_51\jre\lib\jfxrt.jar hinzufügen! Oder ähnliche 1.7.0_xx Versionen, aber nicht 1.8!
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
		
		
		
		//BorderPane pane1 = new BorderPane();
		//Pane pane1 = new Pane();
		//pane1.getChildren().addAll(sucheF,sucheP);
		
		
		HBox hbox1 = new HBox();
		pane1.setCenter(hbox1);
		
		
		//hbox1.setLayoutX(512);
		//hbox1.setLayoutY(394);
		hbox1.setAlignment(Pos.CENTER);
		
		//pane1.getChildren().addAll();
		
		
		//Scene start1 = new Scene(pane1);
		Scene start1 = new Scene(pane1);
		
		stage.setTitle("Kognitive Suche");
		stage.centerOnScreen();
		stage.setWidth(1024);
		stage.setHeight(768);
		stage.setScene(start1);
		stage.setResizable(false);
		stage.show();
		
		hbox1.setPadding(new Insets(15,30,15,30));
		hbox1.setSpacing(20);
		//hbox.setLayoutX(512);
		//hbox.setLayoutY(394);
		hbox1.setStyle("-fx-background-color: #EEEEEE;");
		
		TextField suchleiste = new TextField();
		suchleiste.setMaxWidth(200);
		
		Button sucheF = new Button("Suche in F");
		
		sucheF.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent sucheF) {
					textfield();
				}
			});
		
		Button sucheP = new Button("Suche in P");
		sucheP.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent sucheP) {
				System.out.println("Button wurde geklickt");
				/* Suche starten
				 * String an PDF Box - Textfield = "suchleiste"
				 * Abgleich des Suchstrings mit PDF Stichwörtern
				 * Rückgabe der Tags von der PDF Box
				 * Syso der Tags in die nächsten kommenden Felder
				 * Aufruf des nächsten Fensters
				 */
				
			}
			
		});
		hbox1.getChildren().addAll(suchleiste,sucheF,sucheP);
	}
	
	public void textfield(){
		TextArea textfield[] = new TextArea[25];
		//GridPane pane2 = new GridPane();
		System.out.println("Button Action ausgeführt");
		/* Suche starten
		 * String an Faroo - Textfield = "suchleiste"
		 * Faroo Rückgabe
		 * primitiver Suchalgorithmus arbeitet
		 * Rückgabe der Tags
		 * Ausgabe der Tags in die textfield[i]
		 */
	
		//int anzKat=4; /*Hier wird die Kategorieanzahl übergeben*/
		//Test mit 4 Kategorien
		for (int i = 0;i<2;i++){
			for (int j = 0;j<2;j++){
				textfield[i] = new TextArea("Ich bin das Textfeld in der Spalte "+i+" Zeile "+j+" !\n"+"Es können Tags per Hand gelöscht wurden und mit Enter die Kategorie auswählen");
				textfield[i].setEditable(true); /*Vorerst Editable,um Tags per hand rauszufiltern.*/
				textfield[i].setOnKeyPressed(new EventHandler<KeyEvent>() 
						{
					    	@Override
					    	public void handle(KeyEvent keyEvent) 
					    	{
					    		if(keyEvent.getCode() == KeyCode.ENTER)
					    		{
					    			System.out.println("Enter wurde gedrückt!");
					    			/* Tags + Suchstring an Faroo()
					    			 * Faroo NodeList Rückgabe()
					    			 * primitiver Suchalgorithmus auf NodeList()
					    			 * Tags bereit machen für Textfields()
					    			 * Kategorieanzahl übergeben welche sich ergibt()
					    			 * 
					    			 */
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
		//Scene start1 = new Scene(pane2);
		//System.out.println("Pane hinzugefügt!");
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
		}
		vbox1.setMaxSize(1000, 700);
		//vbox1.setSpacing(20);
		BorderPane pane3 = new BorderPane();
		//pane3.getChildren().add(vbox1);
		pane1.getChildren().clear();
		pane1.setCenter(vbox1);
	}
	
	
}
