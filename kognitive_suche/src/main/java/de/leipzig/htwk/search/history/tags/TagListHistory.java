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
	  tagListHistoryData = new ArrayList<TagListHistoryObject>();
  }
  
  public void addStep(int pos, ReturnTagList tagList, Results results ) {
	if(tagListHistoryData.size() != pos)  {
		for(int i = pos; i < tagListHistoryData.size(); i++)
			tagListHistoryData.remove(i);
		
	    tagListHistoryData.add(new TagListHistoryObject (tagList, results));
	} else {
	    tagListHistoryData.add(new TagListHistoryObject (tagList, results));
	}
   
}

public TagListHistoryObject getStep(int i) {
	
	for(int j = 0; j < tagListHistoryData.size(); j++)
		System.out.println("tagListHistoryData: " + tagListHistoryData.get(j).getTagList().getSearchword());
	
	
    return tagListHistoryData.get(i);
}

public int getStepsCount() {
    return tagListHistoryData.size();
}

public void clear() {
  tagListHistoryData.clear();
}

}
