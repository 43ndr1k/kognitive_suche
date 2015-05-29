package de.leipzig.htwk.tests;

import cognitive.search.ReturnTagList;

public class deletTagTest {

  public static void main(String[] args) {

    ReturnTagList tags = new ReturnTagList("Heinz");
    tags.addTagObject("Erhardt", 4, 6);
    tags.addTagObject("Erhardt", 4, 6);
    tags.addTagObject("Strunk", 4, 6);
    tags.addTagObject("Erhardt", 4, 6);
    tags.addTagObject("Peter", 4, 6);
    
    tags.renameTag("Erhardt", "Gandalf");
   tags.testOutput(10);
  }

}
