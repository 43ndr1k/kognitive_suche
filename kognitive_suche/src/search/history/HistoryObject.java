package search.history;

import java.io.Serializable;
import java.util.Date;

public class HistoryObject<searchWord, date> implements Serializable { 
  public String searchWord; 
  public Date date; 
  
  public HistoryObject(String searchWord) { 
    this.searchWord = searchWord; 
    this.date = new Date();
  } 
} 
