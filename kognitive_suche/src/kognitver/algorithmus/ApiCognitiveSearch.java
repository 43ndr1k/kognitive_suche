package kognitver.algorithmus;

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
  ReturnTagList list;

  public ReturnTagList ApiCognitiveSearch(String[] searchText, String searchWord) {

    WordCount count = new WordCount(); // Häufigkeitsanalyse + Umgebungsanalyse
    count.addText(searchText, searchWord);

    AddTagInfos merge = new AddTagInfos(searchWord); // Zusammenführen von Tag-Infos der Analysen
    double[] function = {-3, 0, 15};
    merge.addInfo(count.gettagNearby(), "ax²+bx+c", function);
    merge.addInfo(count.getTagFrequency());

   

    list = merge.getReturnTagList();
    list.sortTags();
    for (int i = 0; i < list.getsize(); i++) {
      System.out.println(list.getTagObject(i).gettag() + " Priority: "
          + list.getTagObject(i).getPriority());
    }
    EditTags edit = new EditTags(list);

    return merge.getReturnTagList();

  }

}
