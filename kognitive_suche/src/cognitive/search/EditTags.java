package cognitive.search;

import general.functions.TxtReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

import snowballstemmer.GermanStemmer;

public class EditTags {
  ReturnTagList tags;

  public EditTags(ReturnTagList returnTagList) {
    this.tags = returnTagList;
  }

  public ReturnTagList getTags() {
    return tags;
  }


  /**
   * Hier wird der Kölner Phonetik Algorithmus durchlaufen.
   * 
   * @author Steffen Schreiber
   */



  public void colognePhonetic() {

    ReturnTagList pruefgleich = tags;

    for (int i = 0; i <= tags.getSize(); i++) {
      ColognePhonetic.Encoding(pruefgleich.getTagObject(i).getTag());
      ColognePhonetic.CleaningDoubles(pruefgleich.getTagObject(i).getTag());
      ColognePhonetic.CleaningZeroes(pruefgleich.getTagObject(i).getTag());
    }
    for (int n = 0; n < tags.getSize(); n++) {
      for (int m = 0; m <= tags.getSize(); m++) {
        if (pruefgleich.getTagObject(n).getTag() == pruefgleich.getTagObject(m).getTag() && n != m) {

          tags.renameTag(tags.getTagObject(n).getTag(), tags.getTagObject(m).getTag());
        }
      }
    }
  }

  public void stem() {
    // create a new instance of PorterStemmer
    GermanStemmer stemmer = new GermanStemmer();

    for (int i = 0; i < tags.getSize(); i++) {
      // set the word to be stemmed
      stemmer.setCurrent(tags.getTagObject(i).getTag());


      // call stem() method. stem() method returns boolean value.
      if (stemmer.stem()) {
        tags.renameTag(tags.getTagObject(i).getTag(), stemmer.getCurrent());
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

  public void removeTagsLongerThanVar(int lengthVar) {
    for (int i = 0; i < tags.getSize(); i++) {
      if (tags.getTagObject(i).getTag().length() > lengthVar) {
        tags.deleteTag(tags.getTagObject(i).getTag());
      }
    }
  }

  /**
   * @author Tobias Lenz
   * @param limit - Länge der gekürzten ReturnTagList
   * 
   *        Hier wird aus der ArrayListe eine subList erstellt und dem ReturnTagObject übergeben.
   *        Dadurch wird die Liste auf die gewünschte Länge gekürzt !Wird nur zur
   *        Laufzeitverbesserung benötigt!
   */
  public void limitTags(int limit) {
    if (limit < tags.getSize()) {
      tags.setTagObjects(new ArrayList<ReturnTagObject>(tags.getTags().subList(0, limit)));
    }
  }

  /**
   * 
   * @param allBlocs
   */
  public void findRepresentativeTags(ArrayList<Integer> allBlocs) {
    ArrayList<Integer> currentBlocs = new ArrayList<Integer>();
    int i = 0;
    do {
      currentBlocs = addTagsBlocNumbers(tags.getTagObject(i).getBlocNumbers(), currentBlocs);
      currentBlocs = new ArrayList<Integer>(new LinkedHashSet<Integer>(currentBlocs));
      Collections.sort(currentBlocs);
      i++;
    } while (currentBlocs.size() != allBlocs.size() && i < tags.getSize());
    System.out.println(currentBlocs);
    System.out.println(allBlocs);
    System.out.println("Geht " + i + " : " + tags.getSize());
  }

  private ArrayList<Integer> addTagsBlocNumbers(ArrayList<Integer> blocNumbers,
      ArrayList<Integer> currentBlocs) {
   
    for (int i = 0; i < blocNumbers.size(); i++) {
      boolean flag = false;
      for (int j = i; j < currentBlocs.size(); j++) {
        if (blocNumbers.get(i) == currentBlocs.get(j)) {
            flag = true;
        }
      }
      if (!flag)
        currentBlocs.add(blocNumbers.get(i));
    }
    return currentBlocs;
  }

  public void sortTagsByPriority() {
    tags.sortTagsByPriority();
  }
}
