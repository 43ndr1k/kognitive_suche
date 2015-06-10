package cognitive.search;

import general.functions.TxtReader;
import gui.GUI;
import snowballstemmer.GermanStemmer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

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
      ColognePhonetic.enCoding(pruefgleich.getTagObject(i).getTag());
      ColognePhonetic.cleaningDoubles(pruefgleich.getTagObject(i).getTag());
      ColognePhonetic.cleaningZeroes(pruefgleich.getTagObject(i).getTag());
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

  /**
   * @Autor Hendrik Sawade
   * Lese Txt Datei aus der Jar DAtei
   * @param filename Pfad zu der datei
   * @return sb Inhalt der Datei als langer String
   * @throws IOException
   */
  public String readFromJARFile(String filename)
      throws IOException
  {
    InputStream is = getClass().getResourceAsStream(filename);
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    StringBuffer sb = new StringBuffer();
    String line;
    while ((line = br.readLine()) != null)
    {
      sb.append(line + "\n");
    }
    br.close();
    isr.close();
    is.close();
    return sb.toString();
  }

  public void removeTagsFromWordList() {
    String wordList[];
    TxtReader tr = new TxtReader();
    String tmp = "";
    try {


      tmp = readFromJARFile("/resources/files/stoplist_de.txt");
      System.out.println("1" + tmp);

      //System.out.println(getClass().getResource("/resources/files/stoplist_de.txt").getFile().toString());
      //tmp = tr.readFile(String.valueOf(getClass().getResource("/resources/files/stoplist_de.txt").getFile()));

      //System.out.println("2" + tmp);

    } catch (IOException e) {
      System.out.println("Stoplist datei nicht gefunden");
    }
    wordList = tmp.split("\n");

    for (int i = 0; i < wordList.length; i++) {
      tags.deleteTag(wordList[i]);
    }

  }
/*
 * test.
 * 
 * @author Ivan Ivanikov
 * 
 */
  public void removePreviousTags() {
      String query = GUI.getInstance().getSuchleiste().getText().toString().toLowerCase().trim();
      ReturnTagObject tag;
      
      for(int i = tags.getSize() - 1; i >= 0; i--) {
          tag = tags.getTagObject(i);
          
           if(query.contains(tag.getTag().toString().trim().toLowerCase())) {
               tags.deleteTag(tag.getTag().toString());
          }
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
   * @param limit -gnitive_suche/Lists/Personen.txt Länge der gekürzten ReturnTagList
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
      if (!flag) {
        currentBlocs.add(blocNumbers.get(i));
      }
    }
    return currentBlocs;
  }

  public void sortTagsByPriority() {
    tags.sortTagsByPriority();
  }
//try catch verhindert kompletten Programmabsturz iv
  public void removeSpaces() {
    int i = 0;
    try {
	    while (("a").compareToIgnoreCase(tags.getTagObject(i).getTag()) > 0) {
	    	tags.deleteTag(i);
	    	i++;
	    }
    } catch(Exception e) {}
  }

  public void removeSearchwords() {
    String searchword = tags.getSearchword();
    String[] parts = searchword.split(" ");
    for (int i = 0; i < parts.length; i++) {
      tags.deleteTag(parts[i]);
    }

  }

  /**
   * @author Ivan Ivanikov
   * 
   *  Kategorien werden erstellt indem Listen aus dem Ordner Lists nach enthaltenen Begriffen
   *        durchsucht werden z.z werden einfach alle gefundenen Objekte ausgegeben -> es sollen die
   *        Dateinamen ausgegeben werden um später die Tags aus speziellen listen ziehen zu können
   * 
   * 
   */
  public void kategorisieren() {

    //URL url = getClass().getClassLoader().getResource("/resources/Lists");
    //File[] files = new File(url.getFile()).listFiles();
    File[] files = null;
    try {
      files = new File(getClass().getResource("/resources/Lists").getFile()).listFiles();
    } catch (Exception e) {
      System.out.println("konnte  File nicht lesen");
      e.printStackTrace();
    }

    // Iteration über alle Files in dem Ordner Lists
    for (File list : files) {
      String category = list.getName().replaceAll(".txt", "");
      System.out.println("File" +list.getName());

      if (list.isFile()) {
        String wordList[];

        // File öffnen
        try {
          String tmp = readFromJARFile("/resources/Lists/" + list.getName());
          System.out.println("gelesen");
          wordList = tmp.split("\n");
        } catch (IOException e) {
          System.out.println("Konnte " + list.getName() + " nicht öffnen!");
          break;
        }
        // Die Tags werden auf Vorkommen in der aktuellen WorlList untersucht
        for (int i = 0; i < tags.getSize(); i++) {
          if (isInList(wordList, tags.getTagObject(i).getTag())) {
            //System.out.println(tags.getTagObject(i).getTag() + " wurde ersetzt durch: " + category);
            tags.renameTag(tags.getTagObject(i).getTag(), category);
          }
        }

      }
    }

  }

  /**
   * Binäre Suche
   * 
   * @param wordList
   * @param tag
   * @return
   */
  //private
  public boolean isInList(String[] wordList, String tag) {
    tag = tag.replaceAll("[^a-zA-Z0-9 äöüÄÖÜß]", " ");
    int mitte;
    int links = 0;
    int rechts = wordList.length - 1;
    int tmp;

    while (links <= rechts) {
      mitte = links + ((rechts - links) / 2);
      tmp = tag.compareToIgnoreCase(wordList[mitte]);
      if (tmp == 0) {
        return true;
      } else if (tmp < 0) { // Tag befindet sich in der linken Hälfte
        rechts = mitte - 1;
      } else if (tmp > 0) { // Tag befindet sich in der rechten Hälfte
        links = mitte + 1;
      }
    }

    return false;

  }
}
