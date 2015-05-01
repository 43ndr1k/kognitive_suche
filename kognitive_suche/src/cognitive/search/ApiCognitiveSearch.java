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
  ReturnTagList list;

  public ReturnTagList ApiCognitiveSearch(String[] searchText, String searchWord) {

    WordCount count = new WordCount(); // Häufigkeitsanalyse + Umgebungsanalyse
    count.analyseText(searchText, searchWord);

    AddTagInfos merge = new AddTagInfos(searchWord); // Zusammenführen von Tag-Infos der Analysen
    double[] function = {-3, 0, 10};
    merge.addInfo(count.gettagNearby(), "ax²+bx+c", function);
    merge.addInfo(count.getTagFrequency());

    list = merge.getReturnTagList();

    EditTags edit = new EditTags(list);
    System.out.println("Anzahl der Tags ohne Algorithmus: " + list.getsize());
    list.sortTagsByPriority();
    list.testOutput(10); // Testausgabe der top 10 Tags
    System.out
        .println("--------------------------------------------------------------------------------------");
    edit.removeTagsFromWordList();
    System.out.println("Anzahl Tags mit Entfernen der in der Wortlist enthaltenen Wörter: "
        + list.getsize());
    list = edit.getTags();
    list.testOutput(10);
    System.out
        .println("--------------------------------------------------------------------------------------");
    edit.stem();
    System.out.println("Ausgabe der Tags mit Anwendung der Porter-Stemmer-Algorithmus: "
        + list.getsize());
    list = edit.getTags();
    list.sortTagsByPriority();
    list.testOutput(10); // Testausgabe der top 10 Tags

    // Hilfe für Steffen
    // edit.Encoding(searchWord);
    // list.testOutput(10);


    System.out.println("Anzahl der Tags mit stem-Algorithmus: " + edit.getTags().getsize());
    return list;

  }
}