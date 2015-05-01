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

  

  public static ArrayList<String> colognePhonetic(ArrayList<String> tags){
	  
	  ArrayList<String> pruefgleich = new ArrayList<String>();
	  pruefgleich = tags;
	  
	  for (int i=0; i<=tags.size(); i++ ){
		  ColognePhonetic.Encoding(pruefgleich.get(i));
		  ColognePhonetic.CleaningDoubles(pruefgleich.get(i));
		  ColognePhonetic.CleaningZeroes(pruefgleich.get(i));
	  }
	   for (int n=0; n<tags.size(); n++){
			  for (int m=0; m<=tags.size(); m++){
				  if (pruefgleich.get(n)==pruefgleich.get(m) && n!=m){
					 //tags.renameTag(tags.get(n), tags.get(m));
				  }
			  }
		  }
	  return tags;
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
