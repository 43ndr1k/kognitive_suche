package de.leipzig.htwk.searchApi;

import java.io.Serializable;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Hier wird ein Objekt der Suchergebnisse generiert mir den entsprechenden getter Methoden.
 * Parameter sind folgende: * @param title
 * 
 * @param kwic
 * @param author
 * @param votes
 * @param isNews
 * @param url
 * @param domain
 * @param imageUrl
 * @param firstIndexed
 * @param firstPublished
 */
public class Result  {


  String title = "", kwic = "", author = "", votes = "", isNews = "";
  String url = null, domain = null, imageUrl = null;

  String firstIndexed = "", firstPublished = "";

  /**
   * Hier wird ein Objekt der Suchergebnisse generiert mir den entsprechenden getter Methoden.
   * 
   * @param title Titel der Seite
   * @param kwic Erster Satz der Seite
   * @param author Der Autor
   * @param votes Bewertungen
   * @param isNews Ob es news sins
   * @param url komplette url
   * @param domain domain Name
   * @param imageUrl Bild url
   * @param firstIndexed
   * @param firstPublished
   */
  public Result(String title, String kwic, String author, String votes, String isNews, String url,
      String domain, String imageUrl, String firstIndexed, String firstPublished) {
    this.title = title;
    this.kwic = kwic;
    this.author = author;
    this.votes = votes;
    this.isNews = isNews;
    this.url = url;
    this.domain = domain;
    this.imageUrl = imageUrl;
    this.firstIndexed = firstIndexed;
    this.firstPublished = firstPublished;
  }

    public Result(String title, String kwic, String url) {
        this.title = title;
        this.kwic = kwic;
        this.url = url;

    }

  public String getTitle() {
    return title;
  }

  public String getKwic() {
    return kwic;
  }

  public String getAuthor() {
    return author;
  }

  public String getVotes() {
    return votes;
  }

  public String getIsNews() {
    return isNews;
  }

  public String getUrl() {
    return url;
  }

  public String getDomain() {
    return domain;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getFirstIndexed() {
    return firstIndexed;
  }

  public String getFirstPublished() {
    return firstPublished;
  }



}
