package Visual;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;



public class Visual  {//extends Application {
	ArrayList<String> urls = new ArrayList<String>(); // Erstmal nur einen Tag �berall Schmidt 18.12
    String tags = null;
    /*
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();
        
        primaryStage.setTitle("KOgnitive Suche");
        primaryStage.setScene(new Scene(vbox));
        primaryStage.centerOnScreen();
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();

        String tagsStr;
        HBox hbox;
        Hyperlink link;
        //API api = new API(""); Auskommentiert wegen unnoetig - Schmidt 18.12
        //api.query("Superman");
        //ArrayList<HashMap<String, String>> urls = api.getResult();
        //ArrayList<ArrayList<String>> tags = new ArrayList<ArrayList<String>>(); //tag.getTag
 /*

        tag = new ArrayList<String>();//tag.getTag

        tag.add("search");
        tag.add("yahoo");
        tag.add("bing");
        tag.add("seoistdoof");

        tags.add(tag);

        tag = new ArrayList<String>();

        tag.add("im");
        tag.add("sms");
        tag.add("undso");
        tag.add("mirdochegal");
        tag.add("eigentlich können tags auch durch ein leerzeichen getrennt werden");

        tags.add(tag);

        /**
         * Check
         

        if(urls.size() != tags.size())
            throw new Exception("URLs.size() isn't Tags.size()");

        for(int i = 0; i < urls.size(); i++) {
            tagsStr = "";

            for(String str: tags.get(i))
                tagsStr += str + ", ";

            link = new Hyperlink(urls.get(i).get("url"));

            final Hyperlink finalLink = link;
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    getHostServices().showDocument(finalLink.getText());
                }
            });

            hbox = new HBox();

            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.getChildren().addAll(link, new Label(urls.get(i).get("title")));

            vbox.getChildren().add(hbox);
            vbox.getChildren().add(new Label(tagsStr));
            vbox.getChildren().add(new Separator());
        }
    }
    */
    public Visual(ArrayList<String> urls, String tags){
    	this.urls = urls;
    	this.tags = tags;
    }
    

}