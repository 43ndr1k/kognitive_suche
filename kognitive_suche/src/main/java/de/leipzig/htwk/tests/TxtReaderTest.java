package de.leipzig.htwk.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import de.leipzig.htwk.general.functions.TxtReader;

/**
 * 
 * @author Sadik Ulusan
 *
 */

public class TxtReaderTest {
  
  @Test
  public void testReadFile() {
    
    TxtReader test = new TxtReader();
    try {
      String rueckgabewert = test.readFile("test.txt");
      assertEquals("T\n", rueckgabewert);

      rueckgabewert = test.readFile("test2.txt");
      assertEquals("Zeile1\nZeile2\nZeile3\n", rueckgabewert);

      rueckgabewert = test.readFile("testEmptyFile.txt");
      assertEquals("", rueckgabewert);

    } catch (IOException e) {
      e.printStackTrace();
      fail("Fehler");
    }
  }
}