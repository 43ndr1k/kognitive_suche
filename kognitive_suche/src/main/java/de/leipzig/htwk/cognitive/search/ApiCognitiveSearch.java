package de.leipzig.htwk.cognitive.search;

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

  ReturnTagList tags;
  AddTagInfos merge;

  long zstVorher;
  long zstNachher;
  WordCount count = new WordCount();
  private String[] searchText;
  private String searchWord;

  public ApiCognitiveSearch(String[] searchText, String searchWord) {
    this.searchText = searchText;
    this.searchWord = searchWord;


  }

  /**
 * 
 */
  public void doWordCount() {
    zstVorher = System.currentTimeMillis();
    count.analyseText(searchText, searchWord);

    zstNachher = System.currentTimeMillis();
    System.out.println("Zeit benötigt: WordCount: " + ((zstNachher - zstVorher)) + " millisec");
  }

  /**
   * Hier werden die Ergebnisse der
   */
  public void doMergeTagInfos() {

    merge = new AddTagInfos(searchWord);
    merge.addInfo(count.getTagFrequency());
    double[] function = {-3, 0, 5};
    merge.addInfo(count.gettagNearby(), "ax²+bx+c", function);
    tags = merge.getReturnTagList();

  }

  /**
 * 
 */
  public void doEditTags() {
    EditTags edit = new EditTags(tags);
    edit.removeTagsFromWordList();

    // edit.removePreviousTags();

    // edit.stem();
    edit.removeTagsLongerThanVar(15);
    // edit.findRepresentativeTags(findOutBlocNumbers());
    edit.removeSpaces();
    edit.removeSearchwords();
    edit.sortTagsByPriority();
    // edit.setCover(findOutBlocNumbers());
    edit.kategorisieren();
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

  public ReturnTagList getTags() {
    return tags;
  }
}
