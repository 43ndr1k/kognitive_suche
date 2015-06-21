package de.leipzig.htwk.search.history.tags;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import de.leipzig.htwk.cognitive.search.ReturnTagList;


public class TagListHistory {
  
  File tagListHistoryFile;
  ArrayList<ReturnTagList> tagListHistoryData;
  
  public TagListHistory () {
    
    tagListHistoryFile = new File("tagHistory.bin");

    if (!tagListHistoryFile.exists()) {
        try {
            tagListHistoryFile.createNewFile();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        tagListHistoryData = new ArrayList<ReturnTagList>();
    } else {

        FileInputStream fis;
        ObjectInputStream ois;

        try {
            fis = new FileInputStream(tagListHistoryFile);
            ois = new ObjectInputStream(fis);
            tagListHistoryData = (ArrayList<ReturnTagList>) ois.readObject();
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
  
  public void addStep(ReturnTagList tagList) {
    // TODO Auto-generated method stub

    tagListHistoryData.add(tagList);
    save();   
}

public void save() {
  FileOutputStream fos;
    ObjectOutputStream oos;

    try {
        fos = new FileOutputStream(tagListHistoryFile);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(tagListHistoryData);
        oos.close();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public ReturnTagList getStep(int i) {
    return tagListHistoryData.get(i);
}

public void clear() {
  tagListHistoryData.clear();
  save();
}

}
