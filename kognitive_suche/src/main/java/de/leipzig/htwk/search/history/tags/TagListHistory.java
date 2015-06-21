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
import de.leipzig.htwk.searchApi.Results;


public class TagListHistory {
  
  File tagListHistoryFile;
  ArrayList<TagListHistoryObject> tagListHistoryData;
  
  public TagListHistory () {
    
    tagListHistoryFile = new File("tagHistory.bin");

    if (!tagListHistoryFile.exists() || tagListHistoryFile.length() == 0) {
        try {
            tagListHistoryFile.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        tagListHistoryData = new ArrayList<TagListHistoryObject>();
    } else {

        FileInputStream fis;
        ObjectInputStream ois;

        try {
            fis = new FileInputStream(tagListHistoryFile);
            ois = new ObjectInputStream(fis);
            tagListHistoryData = (ArrayList<TagListHistoryObject>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  }
  
  public void addStep(int pos, ReturnTagList tagList, Results results ) {
	if(tagListHistoryData.size() != pos)  {
		for(int i = pos; i < tagListHistoryData.size(); i++)
			tagListHistoryData.remove(i);
		
	    tagListHistoryData.add(new TagListHistoryObject (tagList, results));
	} else {
	    tagListHistoryData.add(new TagListHistoryObject (tagList, results));
	}

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
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public TagListHistoryObject getStep(int i) {
	System.out.println("tagListHistoryData: " + tagListHistoryData.get(i).getTagList().getSearchword());
    return tagListHistoryData.get(i);
}

public int getStepsCount() {
    return tagListHistoryData.size();
}

public void clear() {
  tagListHistoryData.clear();
  save();
}

}
