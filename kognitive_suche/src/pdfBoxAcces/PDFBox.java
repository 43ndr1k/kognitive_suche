package pdfBoxAcces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PDFBox {
  
  Process p;
  String doc;

  public PDFBox(String pdfbox_path) {
    /**
     * PDFBox Constructor
     *
     * Builds new PDFBox process. 
     * 
     * @author Fabian Freihube
     */

    ProcessBuilder pb = new ProcessBuilder("java", "-jar", pdfbox_path);

    try {
      p = pb.start();
      System.out.println(pb + " STARTED");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public ArrayList<PDFKeyword> getKeywords() {
    /**
     * getKeywords
     *
     * Grabs outputStream from PDFBox
     * Fills ArrayList of typ PDFKeyword
     * 
     * @author Fabian Freihube
     */
    
    ArrayList<PDFKeyword> Keywords = new ArrayList<PDFKeyword>();

    InputStream inputStream = p.getInputStream();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1);

    String doc = "";

    String line;
    try {
      
      boolean reading = false;

      while ((line = bufferedReader.readLine()) != null) {
        
        if (line.split(":")[0].equals("ENDKEY")) 
          reading = false;

        if(reading)  
          Keywords.add(new PDFKeyword(line.split(":")[0], Float.parseFloat(line.split(":")[1])));
        
        if (line.split(":")[0].equals("STARTKEY")) 
        {
          reading = true;
          doc = line.split(":")[1];
        }
        
      }

      bufferedReader.close();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    this.doc = doc;
    
    return Keywords;
  }

  public String getDoc() {
    return doc;
  }
  
  

}
