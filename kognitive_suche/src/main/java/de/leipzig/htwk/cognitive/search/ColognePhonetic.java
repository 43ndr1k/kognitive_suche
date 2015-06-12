package de.leipzig.htwk.cognitive.search;

/**
 * Kölner Phonetik Algorithmus zur Erkennung von Zusammenhängen zwischen ähnlichen Wörtern und
 * verbinden dieser zu einem. Die Gruppen entschprechen jeweils dem Code, der aus dem Buchstaben
 * kreiert wird. 'group0' zum Beispiel verwandelt alle darin enthaltenen Buchstaben in eine 0 um.
 *
 * @author Steffen Schreiber
 */

public class ColognePhonetic {

  public static char[] group0 = new char[] {'A', 'E', 'I', 'J', 'O', 'U', 'Y', 'Ä', 'Ö', 'Ü'};
  private static char[] group3 = new char[] {'F', 'V', 'W'};
  private static char[] group4 = new char[] {'G', 'K', 'Q',};
  private static char[] group6 = new char[] {'M', 'N'};
  private static char[] group8 = new char[] {'S', 'Z', 'ß'};
  private static char[] groupCFirst = new char[] {'A', 'H', 'K', 'L', 'O', 'Q', 'R', 'U', 'X', 'Ä', 'Ö', 'Ü'};
  private static char[] groupCNoFirst = new char[] {'A', 'H', 'K', 'O', 'Q', 'U', 'X', 'Ä', 'Ö', 'Ü'};
  private static char[] groupCPrevious = new char[] {'S', 'Z'};
  private static char[] groupDTPrevious = new char[] {'C', 'S', 'Z'};
  private static char[] groupXFollow = new char[] {'C', 'K', 'Q'};



  public static String enCoding(String hallo) {

	hallo = hallo.replaceAll("[?.!/^#:;*,~]", "");
    char[] content = new char[hallo.length()];
    {
      for (int i = 0; i < hallo.length(); i++) {
        content[i] = hallo.toUpperCase().charAt(i);
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < content.length; i++) {
      char entry = content[i];

      if (entry == 'H') {
        continue;
      }

      if (Tag.contains1(entry, group0)) {
        sb.append("0");
        continue;
      }
      if (Tag.contains1(entry, group3)) {
        sb.append("3");
        continue;
      }
      if (Tag.contains1(entry, group4)) {
        sb.append("4");
        continue;
      }
      if (Tag.contains1(entry, group6)) {
        sb.append("6");
        continue;
      }
      if (Tag.contains1(entry, group8)) {
        sb.append("8");
        continue;
      }
      if (entry == 'B') {
        sb.append("1");
        continue;
      }
      if (entry == 'L') {
        sb.append("5");
        continue;
      }
      if (entry == 'R') {
        sb.append("7");
        continue;
      }
      if (entry == 'P') {
        if (i + 1 >= content.length) {
          sb.append("1");
          continue;
        }
        char next = content[i + 1];
        if (next == 'H') {
          sb.append("3");
          continue;
        }
        sb.append("1");
        continue;
      }
      if (entry == 'X') {
        if (i == 0) {
          sb.append("48");
          continue;
        }
        char previous = content[i - 1];
        if (Tag.contains1(previous, groupXFollow)) {
          sb.append("8");
          continue;
        }
        sb.append("48");
        continue;
      }
      if (entry == 'D' || entry == 'T') {
        if (i + 1 >= content.length) {
          sb.append("2");
          continue;
        }
        char next = content[i + 1];
        if (Tag.contains1(next, groupDTPrevious)) {
          sb.append("8");
          continue;
        }

        sb.append("2");
        continue;
      }
      if (entry == 'C') {
        if (i == 0) {
          char next = content[i + 1];
          if (Tag.contains1(next, groupCFirst)) {
            sb.append("4");
            continue;
          }
          sb.append("8");
          continue;
        } else {
          if (i + 1 >= content.length) {
            continue;
          }
          char next = content[i + 1];
          char previous = content[i - 1];
          if (Tag.contains1(previous, groupCPrevious)) {
            sb.append("8");
            continue;
          } else {
            if (Tag.contains1(next, groupCNoFirst)) {
              sb.append("4");
              continue;
            }
            sb.append("8");
            continue;
          }
        }
      }
    }
    return sb.toString();
  }

  // Hier werden alle doppelten Zahlen entfernt.
  public static String cleaningDoubles(String hallo) {

    StringBuilder sb = new StringBuilder();
    char[] content = hallo.toCharArray();
    char previous = 'B';
    for (int i = 0; i < content.length; i++) {
      char entry = content[i];
      if (entry != previous) {
        sb.append(entry);
      }
      previous = entry;
    }
    return sb.toString();
  }

  // Hier werden aus dem Code alle Nullen ausßer der ersten entfernt. Nach diesem Durchgang ist der
  // Kölner Phonetik abgeschlossen.
  public static String cleaningZeroes(String hallo) {

	StringBuilder sb = new StringBuilder();
    String content = hallo;
    char firstChar = content.charAt(0);
    StringBuffer rest = new StringBuffer(content);
    rest.deleteCharAt(0);
    int i;
    while ((i=rest.indexOf("0")) > -1)
    	rest.deleteCharAt(i);
	sb.append(firstChar);
	sb.append(rest);
	return sb.toString();
  }
}
