package de.leipzig.htwk.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import de.leipzig.htwk.searchApi.Result;

public class ResultTest {
  static Result newResult;
  static Result newResult2;

  @BeforeClass
  public static void prepare() {
    newResult =
        new Result("title", "kwic", "author", "votes", "isNews", "url", "domain", "imageUrl",
            "firstIndexed", "firstPublished");
    newResult2 = new Result("title", "kwic", "url");
  }

  @Test
  public void testGetTitle() {
    assertEquals("title", newResult.getTitle());
    assertEquals("title", newResult2.getTitle());

  }

  @Test
  public void testGetKwic() {
    assertEquals("kwic", newResult.getKwic());
    assertEquals("kwic", newResult2.getKwic());
  }

  @Test
  public void testGetAuthor() {
    assertEquals("author", newResult.getAuthor());
    assertEquals("", newResult2.getAuthor());
  }

  @Test
  public void testGetVotes() {
    assertEquals("votes", newResult.getVotes());
    assertEquals("", newResult2.getVotes());
  }

  @Test
  public void testGetIsNews() {
    assertEquals("isNews", newResult.getIsNews());
    assertEquals("", newResult2.getIsNews());
  }

  @Test
  public void testGetUrl() {
    assertEquals("url", newResult.getUrl());
    assertEquals("url", newResult2.getUrl());
  }

  @Test
  public void testGetDomain() {
    assertEquals("domain", newResult.getDomain());
    assertNull(newResult2.getDomain());
  }

  @Test
  public void testGetImageUrl() {
    assertEquals("imageUrl", newResult.getImageUrl());
    assertNull("", newResult2.getImageUrl());
  }

  @Test
  public void testGetFirstIndexed() {
    assertEquals("firstIndexed", newResult.getFirstIndexed());
    assertEquals("", newResult2.getFirstIndexed());
  }

  @Test
  public void testGetFirstPublished() {
    assertEquals("firstPublished", newResult.getFirstPublished());
    assertEquals("", newResult2.getFirstPublished());
  }

}
