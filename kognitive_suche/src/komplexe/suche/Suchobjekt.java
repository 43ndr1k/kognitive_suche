package komplexe.suche;

public class Suchobjekt {

  private String link, titel, searchtext;

  // TODO das Kommentar unten ergänzen

  /**
   * @author Tobias Lenz Beispielhafte Implementation des Objekts Suchergebnis. Hinweis: in der
   *         Zeichenkette searchtext stehen die Begriffe, welche in dem Suchergebnis um den Suchtext
   *         herum gefunden wurden; getrennt durch Leerzeichen
   * @param link Bitte Beschreibung hinzufügen
   * @param titel Bitte Beschreibung hinzufügen
   * @param searchtext Bitte Beschreibung hinzufügen
   */
  public Suchobjekt(String link, String titel, String searchtext) {
    this.link = link;
    this.titel = titel;
    this.searchtext = searchtext;

  }

  public String getlink() {
    return link;
  }

  public String gettitel() {
    return titel;
  }

  public String getsearchtext() {
    return searchtext;
  }


}
