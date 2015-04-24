package de.leipzig.htwk.tests;

import de.leipzig.htwk.websearch.HTMLTools;

public class WebSearchTest {

  public static void main(String[] args) {
    HTMLTools test = new HTMLTools();
    String code = test.getHTMLSourceCode("http://openbook.rheinwerk-verlag.de/javainsel/javainsel_05_003.html");
    //System.out.println(code);
    String filtered = test.filterHTML(code);
    System.out.println(filtered);
  }
}
