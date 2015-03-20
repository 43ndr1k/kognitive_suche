package de.leipzig.htwk.list;

import java.util.ArrayList;

import simpleAlgorithm.SimAlgTags;
import visualize.VisControler;
import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.faroo.api.Results;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * 
 * @author Ivan Ivanikov
 *
 */


public class Listenausgabe {
	private int width;
	private int height;
	private Controller mController = new Controller();
	public ArrayList<String> tags = new ArrayList<String>();
	public ArrayList<String> url = new ArrayList<String>();
	public ArrayList<String> kwic = new ArrayList<String>();
	public ArrayList<String> title = new ArrayList<String>();

	
	/**
	 * 
	 *  Testfall: Es wird geprüft ob diese Klasse überhaupt erreicht wurde.
	 *  Gibt ein Alert falls erfolgreich
	 */
	/*public Pane test() {
		Pane pane= new Pane();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText("I have a great message for you!");

		//alert.showAndWait();
		Label label = new Label("test");
		pane.getChildren().add(label);
		return pane;
	}*/
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	/**
	 * 
	 * Aus dem Controller geholte Resultdaten
	 * Sobald Tags implementiert wurden bitte nachtragen
	 */
	public Listenausgabe(String query){
		mController.queryFaroo(query);
		Results r = mController.getResultList();
		for(int i = 0; i < r.getResults().size(); i++) {
			kwic.add(r.getResults().get(i).getKwic());
			title.add(r.getResults().get(i).getTitle());
			url.add(r.getResults().get(i).getUrl());
		}

		ArrayList<SimAlgTags> treffer = mController.GetTags();
		for (int i = 0; i < treffer.size(); i++) {
			tags.add(treffer.get(i).gettag());
		}
	
	}
			/**
			 * Schchtatlung der FX elmente um ein funktionierendes Scrollpane zu realisieren
			 * Ergebnisse mit raendern um eine bessere trennung vollziehen zu können und dennoch keine Platzverschwendung zu haben
			 * Bei Ausführung wird das Wabenpane ge-"cleard" und mit der Listenstruktur übermalt 
			 * -> dies spart das Zeichnen eine weiteren Szene
			 */
	public ScrollPane ergebnisausgabe(){
		VBox vbox1 = new VBox();
		VBox vbox2;
		BorderPane pane = new BorderPane();
		ScrollPane rol = new ScrollPane();
		Hyperlink link[] = new Hyperlink[25];
		Label label1[] = new Label[50];
		Label label[] = new Label[25];
		//Label label2[] = new Label[25];
		int anzsucherg = 10;	/*Momentan immer 10 da nur 10 URLs von Faroo*/
		for (int k=0; k<anzsucherg;k++){
			link[k] = new Hyperlink(url.get(k));
			label1[k] = new Label(kwic.get(k));
			label[k] = new Label(title.get(k));
			vbox2 = new VBox();
			vbox2.setStyle("-fx-border-width: 2;");
			vbox2.setStyle("-fx-border-color: black;");
			vbox2.getChildren().addAll(label[k],label1[k],link[k]);
			vbox1.getChildren().add(vbox2);
			label[k].setMaxSize(600, 300);
			label[k].setWrapText(true);
			label[k].setStyle("-fx-label-padding: 0 0 10 0;");
			label[k].setStyle("-fx-font-weight: bold;");
			//label1[k].setMaxSize(300, 600);
			label1[k].setWrapText(true);
			label1[k].setStyle("-fx-label-padding: 0 0 0 0;");
			
		}
		//vbox1.setMaxSize(1000, 1000);
		vbox1.setStyle("-fx-border-width: 2;");
		vbox1.setStyle("-fx-border-color: black;");
		/*vbox1.setSpacing(20);*/
		pane.getChildren().clear(); /* säubert das das pane um dann listenstruktur zu zeichnen*/
		pane.setCenter(vbox1);
		rol.setPrefSize((double)width,(double)height);
		rol.setContent(pane);
		rol.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		//pane.setStyle(-);
		//rol.setFitToHeight(true);
		//rol.setFitToWidth(true);
		return rol;
	}
	
	
}
