package Faroo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
/**
 * Created by hendrik on 09.12.14.
 */
public class ConfigFileManagement {



    private String key = "";

    public ConfigFileManagement(){


        Properties properties = new Properties();
        BufferedInputStream stream = null;
        try {

            File file = new File("config.properties");
            if (!file.exists()) {
                erstelleConfigFile();
                System.out.println("Sie haben folgenden Key eingeben: " + key + "\n");
                System.out.print("Warte...\n");
            }
            stream = new BufferedInputStream(new FileInputStream("config.properties"));
            properties.load(stream);
            stream.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        key = properties.getProperty("key");


    }
    /**
     * Diese Methode list eine Config file aus und speichert den API key in einer Variable key.
     *
     * @return key
     */
    public  String getKey(){

        return this.key;
    }

    /**
     * Erstellt ein config file sofern es das nicht gibt.
     */
    private void erstelleConfigFile(){

        String key = "";
        Writer fw = null;

        //ToDOO Einlesen des keys muss auf Grafik angepasst werden!!!

        System.out.print("Config file und der Faroo API Key fehlt!\n");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Geben Sie bitte den Faroo API Key ein: \n");
        try {
            key = console.readLine();
        } catch (IOException e) {
            // Sollte eigentlich nie passieren
            e.printStackTrace();
        }


        try
        {
          fw = new FileWriter( "config.properties" );
           fw.write( "# Dies ist der Faroo API Key\n");
          fw.write( "key = " + key);
          fw.append( System.getProperty("line.separator") ); // e.g. "\n"
        }
        catch ( IOException e ) {
          System.err.println( "Konnte Datei nicht erstellen" );
        }
        finally {
          if ( fw != null )
            try { fw.close(); } catch ( IOException e ) { e.printStackTrace(); }
        }


    }

}
