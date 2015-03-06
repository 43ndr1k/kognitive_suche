package GUI;

import java.util.ArrayList;

import de.leipzig.htwk.controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import de.leipzig.htwk.faroo.api.Results;
import simpleAlgorithm.SimAlgTags;

/**
 * GUI Erstellung
 *
 * @author Sebastian Hügelmann
 * @version 0.3
 */

/**
 * Damit die JavaFX Application funktioniert, müsst ihr rechtsklick auf GUI.java/Build Path/Configure Build Path...
 * Dann auf den Reiter: Librariers und dort Add External JARs...
 * Und dann in eurem C:\Program Files\Java\jdk1.7.0_51\jre\lib\jfxrt.jar hinzufügen! Oder ähnliche 1.7.0_xx Versionen, aber nicht 1.8!
 */

public class GUI extends Application{
	private Controller mController = new Controller();
	public ArrayList<String> tags = new ArrayList<String>();
	public ArrayList<String> url = new ArrayList<String>();
	public ArrayList<String> kwic = new ArrayList<String>();
	private BorderPane pane1 = new BorderPane();
	private int anzkat = 10;
	private GridPane pane2 = new GridPane();
	final TextField suchleiste = new TextField();					/*DIESEN TEXT BRAUCH DER CONTROLLER UND FAROO*/

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		mController.setParameter("de","web",1);

		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();//schliessen box
		VBox vbox1 = new VBox();
		pane1.setStyle("-fx-background-color: #FFF;");
		pane1.setCenter(vbox1);
		pane1.setBottom(hbox2);//schliessen

		hbox1.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.BOTTOM_RIGHT);//Rechte ecke postionsbestimmung closebox
		
		hbox1.setPadding(new Insets(15,30,15,30));				/*Bestimmt den Abstand vom Rand nach Innen*/
		hbox1.setSpacing(20);									/*Bestimmt den Abstand der Elemente voneinander*/
		hbox1.setStyle("-fx-background-color: #FFF;");		/*Bestimmt die Hintergrundfarbe*/
		vbox1.setStyle("-fx-background-color: #FFF;");
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setSpacing(50);

		Scene start1 = new Scene(pane1);

		stage.setTitle("Kognitive Suche");
		stage.centerOnScreen();
		stage.setWidth(1024);
		stage.setHeight(768);
		stage.setScene(start1);
		stage.setResizable(false);
		stage.show();


		suchleiste.setMaxWidth(200);



		suchleiste.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent keyEvent)
			{
				if(keyEvent.getCode() == KeyCode.ENTER)
				{
					Daten();
					textfield();
				}
			}
		});

		Button sucheF = new Button("Suche in F");

		sucheF.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent sucheF) {
				Daten();
				textfield();								/*Ruft die Methode zur Generierung Textfelder auf*/
			}
		});

		Button sucheP = new Button("Suche in P");
		sucheP.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent sucheP) { // Bei PDFbox wird das TXT feld nicht ben�tigt - bis jetzt
				//mController.startSearchP();
				//kwic = mController.getKeywords2();
				//url = mController.getDocName();
				//tags = mController.getKeywords1(); // Ohne Sortierung soviel ich wei�
				textfield();

			}

		});
		
		Button close = new Button("Schliessen");//button zum schliessen
		
		close.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event) {
		        Platform.exit();
				
			}
			
		});
		
		
		hbox1.getChildren().addAll(suchleiste,sucheF,sucheP);
        vbox1.getChildren().addAll(homebutton(),hbox1);
        hbox2.getChildren().addAll(close);
	}

	private void Daten(){
		mController.queryFaroo(suchleiste.getText());
		Results r = mController.getResultList();
		for(int i = 0; i < r.getResults().size(); i++) {
			kwic.add(r.getResults().get(i).getKwic());
			//title.add(r.getResults().get(i).getTitle());
			url.add(r.getResults().get(i).getUrl());
		}

		ArrayList<SimAlgTags> treffer = mController.GetTags();
		for (int i = 0; i < treffer.size(); i++) {
			tags.add(treffer.get(i).gettag());
		}
	}

	public void textfield(){
		TextArea textfield[] = new TextArea[25];
		System.out.println("Button Action ausgeführt");


		//Aufruf Controller
		//GUI.suchleiste.getText(); @Parameter
		/* Suchbegriffe aus der Suchleiste auslesen und an den Controller übergeben @Parameter
		 * Rückgabe der Tags
		 * Tags sind in String Array gespeichert?
		 * Anzahl String Arrays = Anzahl Kategorie @Parameter anzKat
		 */
	
		/*Hier wird die Kategorieanzahl übergeben*/
		/*int anzKat=4;*/
		/*Test mit 4 Kategorien*/
		int feld=0;
		for (int i = 0;i<2;i++){
			for (int j = 0;j<5;j++){
				//textfield[i] = new TextArea("Ich bin das Textfeld in der Spalte "+i+" Zeile "+j+" !\n"+"Es können Tags per Hand gelöscht werden und mit Enter die Kategorie auswählen");
				textfield[feld] = new TextArea(""+tags.get(feld)); // DU MUESSTEST NOCH PRUEFEN OB DAS ARRAYLIST WAS ENTHAELT
				/*Vorerst Editable,um Tags per hand rauszufiltern.*/
				textfield[feld].setEditable(true);
				textfield[feld].setWrapText(true);
				textfield[feld].setOnKeyPressed(new EventHandler<KeyEvent>()
				{
					@Override
					public void handle(KeyEvent keyEvent)
					{
						if(keyEvent.getCode() == KeyCode.ENTER)
						{
							System.out.println("Enter wurde gedrückt!");
							//Aufruf Controller
							//GUI.suchleiste.getText(); @Parameter
							//GUI.textfield[i].getText(); @Parameter
					    			
					    			/* Tags + Suchstring an Controller @Parameter
					    			 * Rückgabe der Tags
					    			 * Tags sind in String Array gespeichert?
					    			 * Anzahl String Arrays = Anzahl Kategorie @Parameter anzKat
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
				pane2.add(textfield[feld], i, j);
				System.out.println("Feld erstellt!");
				feld++;
			}
		}
		pane2.setAlignment(Pos.CENTER);
		pane2.setPadding(new Insets(20, 30, 20, 30));
		pane2.setHgap(10);
		pane2.setVgap(10);
		pane1.setCenter(pane2);
		pane1.setTop(homebutton());
	}

	public void ergebnisausgabe(){
		VBox vbox1 = new VBox();
		Hyperlink link[] = new Hyperlink[25];
		Label label[] = new Label[25];
		int anzsucherg = 10;	/*Momentan immer 10 da nur 10 URLs von Faroo*/
		for (int k=0; k<anzsucherg;k++){
			//link[k] = new Hyperlink("www.oracle.com");	/*arraylist.get(URL); from Arraylist*/
			link[k] = new Hyperlink(url.get(k));
			/*arraylist.get(KWIC) von arraylist*/
			//label[k] = new Label("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At");
			label[k] = new Label(kwic.get(k));
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
	
	public HBox homebutton(){
		HBox hboxHOME = new HBox();
		final ImageView imv = new ImageView();
        final Image image2 = new Image("http://www.imn.htwk-leipzig.de/~shuegelm/image.jpg");
        imv.setImage(image2);
        imv.setCursor(Cursor.HAND);
        
        imv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Tile pressed");
            }
       });
        
        hboxHOME.getChildren().add(imv);
        hboxHOME.setAlignment(Pos.CENTER);
        hboxHOME.setPadding(new Insets(15,15,15,15));
		return hboxHOME;
	}


}