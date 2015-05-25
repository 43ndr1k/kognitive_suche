package search.history;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchHistory {
  
  File historyFile;
  ArrayList<HistoryObject> historyData;
  
  public SearchHistory () {
    historyFile = new File("history.last");
    
    if(!historyFile.exists())
    {
      try {
        historyFile.createNewFile();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      
      historyData = new ArrayList<HistoryObject>();
    } else {
    
      FileInputStream fis;
      ObjectInputStream ois;
      
      try {
        fis = new FileInputStream(historyFile); 
        ois = new ObjectInputStream(fis);
        historyData = (ArrayList<HistoryObject>) ois.readObject();
        ois.close();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
    
  }

  public void addSearch(String searchWord) {
    // TODO Auto-generated method stub
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    for(int i = 0; i < historyData.size(); i++)
      System.out.println(historyData.get(i).searchWord + " " + dateFormat.format(historyData.get(i).date));
    
    HistoryObject newObject = new HistoryObject (searchWord);
    historyData.add(newObject);
    
    FileOutputStream fos;
    ObjectOutputStream oos;
      
    try {
        fos = new FileOutputStream(historyFile);    
        oos = new ObjectOutputStream(fos);
        oos.writeObject(historyData);
        oos.close();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
  }

}
