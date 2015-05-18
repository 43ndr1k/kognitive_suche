package cognitive.search;

import java.util.ArrayList;

public class ApiCognitiveSearch {
  /**
   * Algorithmus zur Erkennung von Schlüsselbegriffen Diese Klasse nimmt ein Array von Textblöcken,
   * optional mit Adressen (URL oder Speicheradressen) entgegen und gibt eine sich aus diesen
   * Textblöcken ergebende Auswahl an repräsentativen Schlüsselbegriffen (Tags) zurück.
   * 
   * Dies wird durch die Kombination von Häufigkeit und Nähe der Wörter zum Suchbegriff realisiert.
   * 
   * 
   * 
   * @param searchText - Ein Array von Textblöcken, welche durch eine kognitive Suche analysiert
   *        werden sollen
   * @param searchWord - Der/die gesuchte/n Begriff/e
   *
   * @author Tobias Lenz
   */
  AddTagInfos merge;
  ReturnTagList tags;


  long zstVorher;
  long zstNachher;
  WordCount count = new WordCount();
  private String[] searchText;
  private String searchWord;

  public ApiCognitiveSearch(String[] searchText, String searchWord) {
    this.searchText = searchText;
    this.searchWord = searchWord;


  }

  public void doEditTags() {
    EditTags edit = new EditTags(tags);
    edit.removeTagsFromWordList();
    edit.getTags().testOutput(50);
    
   // edit.stem();
    edit.removeTagsLongerThanVar(15);
    edit.limitTags(100);
   // edit.findRepresentativeTags(findOutBlocNumbers());

    
    edit.sortTagsByPriority();
    tags = edit.getTags();

  }

  private ArrayList<Integer> findOutBlocNumbers() {
    ArrayList<Integer> blocTmp = new ArrayList<Integer>();
    for (int i = 0; i < searchText.length; i++) {
      if (!searchText[i].isEmpty()) {
        blocTmp.add(i);
      }
    }
    return blocTmp;

  }

  public void doMergeTagInfos() {
    zstVorher = System.currentTimeMillis();
    
    AddTagInfos merge = new AddTagInfos(searchWord);
    double[] function = {-3, 0, 10};
    merge.addInfo(count.getTagFrequency());
    merge.addInfo(count.gettagNearby(), "ax²+bx+c", function);   
    tags = merge.getReturnTagList();
    System.out.println("hier");
    zstNachher = System.currentTimeMillis();
    System.out.println("Zeit benötigt: Tag-Merge: " + ((zstNachher - zstVorher)) + " millisec");
  }

  public void doWordCount() {
    zstVorher = System.currentTimeMillis();
    count.analyseText(searchText, searchWord);

    zstNachher = System.currentTimeMillis();
    System.out.println("Zeit benötigt: WordCount: " + ((zstNachher - zstVorher)) + " millisec");
  }

  public ReturnTagList getTags() {
    return tags;
  }
}
