package kognitver.algorithmus;

import java.util.ArrayList;

/**
 * 
 * @author Tobias Lenz
 * 
 *         Klasse, um verschiedene Tag-Informationen zu übergeben bzw. einzufügen. Es genügt die
 *         Funktion addInfo mit verschiedenen Parametern aufzurufen.
 *
 */

public class AddTagInfos {
  private ArrayList<Tag> tmpTag;
  private ArrayList <Keywords> tmpKey;
  private ReturnTagList list;

  public AddTagInfos(String searchWord) {
    list = new ReturnTagList(searchWord);
  }

  public ReturnTagList getList() {
    return list;
  }

  public void addInfo(Keywords info) {
    list.addTagObject(info.gettag(), info.gettextBlocNumber(), info.getcount());
  }

  public void addInfo(Tag info) {
    if (info != null) {
      for (int i = 0; i < info.getsize(); i++)
        list.addTagObject(info.gettag(i), info.gettextBlocNumber());
    }
  }

  /**
   * Diese Funktion wird zur Unterscheidung von verschiedenen ArrayListen, welche Tag-Informationen
   * enthalten benötigt. Es wird ein Objekt angenommen und die Art des Objekts überprüft. Danach
   * wird die passende Funktion aufgerufen. Die try-catch-Blöcke dienen zur Fehlervermeidung, damit
   * ein Objekt nicht falsch interpretiert wird.
   * 
   * @param info
   */
  public void addInfo(Object info) {
    if (info != null) {
      try {
        if (((ArrayList<Keywords>) info).get(0).getClass().getName() == tmpKey.getClass().getName()) {
          addKeywordsInfo((ArrayList<Keywords>) info);
          System.out.println("Hier");
          return;
        }
      } catch (Exception e) {
      }
      try {

        if (((ArrayList<Tag>) info).get(0).getClass().getName() == tmpTag.getClass().getName()) {
          addTagArrayInfo((ArrayList<Tag>) info);
          System.out.println("Hier auch");
          return;
        }

      } catch (Exception e) {

      }
    }
  }

  public void addKeywordsInfo(ArrayList<Keywords> info) {
    for (int i = 0; i < info.size(); i++) {
      list.addTagObject(info.get(i).gettag(), info.get(i).gettextBlocNumber(), info.get(i)
          .getcount());
    }
  }

  private void addTagArrayInfo(ArrayList<Tag> info) {
    for (int i = 0; i < info.size(); i++) {
      addInfo(info.get(i));
    }
  }

  private ReturnTagList getReturnTagList() {
    return list;
  }


}
