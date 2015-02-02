package Faroo;

import java.net.URL;
import java.util.Date;

/**
 * Created by hendrik on 14.12.14.
 */
public class Result {



    //Date firstIndexed = null, firstPublished = null;

    String title="", kwic="", author="", votes="", isNews="";
    String url = null, domain =null, imageUrl=null;

    String firstIndexed = "", firstPublished = "";

    public Result(String title, String kwic, String author, String votes, String isNews, String url, String domain,
                  String imageUrl, String firstIndexed, String firstPublished) {
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

