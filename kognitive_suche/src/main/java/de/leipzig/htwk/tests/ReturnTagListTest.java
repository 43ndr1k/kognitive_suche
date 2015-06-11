package de.leipzig.htwk.tests;

import de.leipzig.htwk.cognitive.search.ReturnTagList;

public class ReturnTagListTest {

  public static void main(String[] args) {
  
    ReturnTagList tags = new ReturnTagList("Peter");
    tags.addTagObject("Maffay", 1);
    tags.addTagObject("Lustig", 3);
    tags.addTagObject("Maffay", 5);
    tags.addTagObject("Aalfelder", 7);
    tags.addTagObject("Zuse", 10);
    tags.addTagObject("", 11);
    tags.addTagObject("Charly", 15);
    tags.addTagObject("Aalfelder", 16, 5);
    tags.addTagObject(".B7Ä", 18);


    tags.testOutput(10);
    System.out.println(tags.getSize());
    
    
  }

}
