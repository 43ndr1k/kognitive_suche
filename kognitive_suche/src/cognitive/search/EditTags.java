package cognitive.search;

import general.functions.TxtReader;

import java.io.IOException;
import java.util.ArrayList;

import snowballstemmer.GermanStemmer;

public class EditTags {
  ReturnTagList tags;

  public EditTags(ReturnTagList returnTagList) {
    this.tags = returnTagList;
  }

  public ReturnTagList getTags() {
    return tags;
  }

  
  /** Hier wird der Kölner Phonetik Algorithmus durchlaufen. 
   * @author Steffen Schreiber
   */

  

  public void colognePhonetic(){
	  
	  ReturnTagList pruefgleich = tags;
	  
	  for (int i=0; i<=tags.getsize(); i++ ){
		  ColognePhonetic.Encoding(pruefgleich.getTagObject(i).gettag());
		  ColognePhonetic.CleaningDoubles(pruefgleich.getTagObject(i).gettag());
		  ColognePhonetic.CleaningZeroes(pruefgleich.getTagObject(i).gettag());
	  }
	   for (int n=0; n<tags.getsize(); n++){
			  for (int m=0; m<=tags.getsize(); m++){
				  if (pruefgleich.getTagObject(n).gettag()==pruefgleich.getTagObject(m).gettag() && n!=m){
				    
					 tags.renameTag(tags.getTagObject(n).gettag(), tags.getTagObject(m).gettag());
				  }
			  }
		  }
  }

  public void stem() {
    // create a new instance of PorterStemmer
    GermanStemmer stemmer = new GermanStemmer();

    for (int i = 0; i < tags.getsize(); i++) {
      // set the word to be stemmed
      stemmer.setCurrent(tags.getTagObject(i).gettag());


      // call stem() method. stem() method returns boolean value.
      if (stemmer.stem()) {
        tags.renameTag(tags.getTagObject(i).gettag(), stemmer.getCurrent());
        // If stemming is successful obtain the stem of the given word
        
      }
    }
  }

  public void removeTagsFromWordList() {
    String wordList[];
    TxtReader tr = new TxtReader();
    String tmp = "";
    try {
      tmp = tr.readFile("stoplist_de.txt");
    } catch (IOException e) {
      System.out.println("Stoplist datei nicht gefunden");
    }
    wordList = tmp.split("\n");

    for (int i = 0; i < wordList.length; i++) {
      tags.deleteTag(wordList[i]);
    }

  }
}
