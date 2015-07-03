package de.leipzig.htwk.search.history.tags;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.searchApi.Results;


public class TagListHistory {
  
  File tagListHistoryFile;
  ArrayList<TagListHistoryObject> tagListHistoryData;
  
  public TagListHistory () {
	  tagListHistoryData = new ArrayList<TagListHistoryObject>();
  }
  
  public void addStep(int pos, ReturnTagList tagList, Results results ) {
    
    ReturnTagList savedTagList =  (ReturnTagList) tagList.clone();
    
    int size = tagListHistoryData.size();
    
    if(tagListHistoryData.size() != 0)
      for(int i = pos; i < size; i++)
      {
        System.out.println("Removing Pos: " + pos + " i: " + i + " Data: " + tagListHistoryData.get(pos).getTagList().getSearchword());
		tagListHistoryData.remove(pos);
      }
		
	tagListHistoryData.add(new TagListHistoryObject (savedTagList, results));
    
   
}

public TagListHistoryObject getStep(int i) {
    return tagListHistoryData.get(i);
}

public int getStepsCount() {
    return tagListHistoryData.size();
}

public void clear() {
  tagListHistoryData.clear();
}

}
