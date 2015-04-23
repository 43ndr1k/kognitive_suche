package de.leipzig.htwk.list;

import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.faroo.api.Results;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import simple.algorithm.SimAlgTags;

import java.util.ArrayList;

public class Listenausgabe {
	private int width;
	private int height;
	private Controller mController = new Controller();
	public ArrayList<String> tags = new ArrayList<String>();
	public ArrayList<String> url = new ArrayList<String>();
	public ArrayList<String> kwic = new ArrayList<String>();
	public ArrayList<String> title = new ArrayList<String>();

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
	
	public Listenausgabe(String query){
		mController.queryFaroo(query);
		Results r = mController.getResultList();
		for(int i = 0; i < r.getResults().size(); i++) {
			kwic.add(r.getResults().get(i).getKwic());
			title.add(r.getResults().get(i).getTitle());
			url.add(r.getResults().get(i).getUrl());
		}

		ArrayList<SimAlgTags> treffer = mController.getTags();
		for (int i = 0; i < treffer.size(); i++) {
			tags.add(treffer.get(i).gettag());
		}
	
	}

	public ScrollPane ergebnisausgabe(){
		//HBox hbox;
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
			//link[k] = new Hyperlink("www.oracle.com");	/*arraylist.get(URL); from Arraylist*/
			link[k] = new Hyperlink(url.get(k));
			/*arraylist.get(KWIC) von arraylist*/
			//label[k] = new Label("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At");
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
			/*top right bottom left*/
			
			/*link[k].setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent o) {
	                    getHostServices().showDocument(link[k].getText());
	                }
			});*/
		}
		//vbox1.setMaxSize(1000, 1000);
		vbox1.setStyle("-fx-border-width: 2;");
		vbox1.setStyle("-fx-border-color: black;");
		/*vbox1.setSpacing(20);*/
		pane.getChildren().clear();
		pane.setCenter(vbox1);
		rol.setPrefSize((double)width,(double)height);
		rol.setContent(pane);
		rol.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		//pane.setStyle(-);
		//rol.setFitToHeight(true);
		//rol.setFitToWidth(true);
		
		
		
		/**
		 * @author Sadik Ulusan
		 */
		//Wenn die Hyperlinks angeklickt werden erkennt es das Program. Links werden ausgef�hrt und durch den
		//Standardbrowser des Nutzers gestartet. PDF sind ebenso ausf�hrbar.
		/*public void ergebnisausgabe(){
	        
	        VBox vbox1 = new VBox();
	        Hyperlink link[] = new Hyperlink[25];
	        Label label[] = new Label[25];
	        int anzsucherg = 3;
	        
	        final WebView browser = new WebView();
	        final WebEngine webEngine = browser.getEngine();
	        
	        for (int k=0; k<anzsucherg;k++){
	            final String url = "http://www.oracle.com";
	            //final String url = "H:/Dokumente/Eigene Bilder/NeueWegeMD.pdf";
	            //final String url = "http://www.uefa.com/MultimediaFiles/Download/Regulations/uefaorg/Regulations/02/09/88/17/2098817_DOWNLOAD.pdf";
	            Hyperlink h = new Hyperlink(url);    /*getHyperlink from Nodelist
	            h.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	                    System.out.println("Hyperlink geklickt!");
	                    //webEngine.load(url);
	                    
	                    //Runtime.getRuntime().exec( "rundll32 url.dll,FileProtocolHandler " +
	                    //        "javascript:location.href=' " + url + " ' " );
	                    try {
	                        Runtime.getRuntime().exec( "rundll32 url.dll,FileProtocolHandler " + url ); 
	                    } catch (IOException e1) {
	                        e1.printStackTrace();
	                    }
	                    
	                    
	                    
	                    
	                            
	                }
	            });
	            link[k] = h;
	            label[k] = new Label("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At");
	            vbox1.getChildren().addAll(link[k],label[k]);
	            label[k].setMaxSize(300, 300);
	            label[k].setWrapText(true);
	            label[k].setStyle("-fx-label-padding: 0 0 20 0;");    /*top right bottom left
	        }*/
		
		return rol;
		
		
		
	}
	
	
}
