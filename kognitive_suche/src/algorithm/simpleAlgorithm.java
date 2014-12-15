package algorithm;

import java.util.HashMap;
import java.util.Map;

public class simpleAlgorithm {
  
  // Implementation eines primitiven Suchalgroithmus (Sadik)
  // Tagerstellung (Vadzim) - Entgegennahme von Sucheingaben vom Controller; - Erstellung von Tags mit Hilfe eines simplen Algorithmus; - Rückgabe der Daten an Controller
// algorithmus bekommt vom controller eine Liste mit Links, die der Suchanfrage entsprechen
  // Algorithmus folgt den Links der Reihe nach und speichert von dort alle Wörter in ein HashMap<String, Integer>
  // in den Strings sind Woerter
  // in den Integern - Haeufigkeit
  // die ersten 4 haeufigsten Woerter werden als tags genommen
  // soll die Haeufigkeit des Suchbegriffs auch beruecksichtigt werden???
  // an den Kontroller geben wir dann tags mit der Liste von Links zu jedem Tag zurueck
 
public HashMap<string tag, ArrayList<Links>> EntgegennahmeVonController (ArrayList<SchluesselObjekte>) { //ne liste von schlüssel-objekten
   
  
  
  
  
  
  
  Map<String, Integer> woertersatz = new HashMap<String, Integer>();

  eingabe = text.getText(); //hier muss einlesen von woertern realisiert werden. ins spiel kommen leerzeichen, zeilenEnde, EndOfFile
  
  
  
  
  if (eingabe.length() > 0) {   //einpacken ins Map und zaehlen der haeufigkeit
      int haufigkeit = 1;
      for (int i = 0; i < eingabe.length(); i++) {
          if (!zeichensatz.containsKey(String.valueOf(eingabe.charAt(i)))) {
              for (int j = i + 1; j < eingabe.length(); j++) {
                  if (eingabe.charAt(i) == eingabe.charAt(j)) {
                      haufigkeit++;
                  }
              }
              zeichensatz.put(String.valueOf(eingabe.charAt(i)), haufigkeit);
              haufigkeit = 1;
          }
      }
  
  return HashMap;
  }

}
