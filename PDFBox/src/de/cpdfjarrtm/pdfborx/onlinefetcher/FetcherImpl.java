package de.cpdfjarrtm.pdfborx.onlinefetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author lorenz
 */
public class FetcherImpl {

    HashMap<String, String> metaMap = new HashMap<>();
    String link, meta, title, page1, url, titleOut;

    int begin, end;

    // main method to have quick connection test
    
    public static void main(String[] args) throws Exception {

        String title = "\r\n\r\nOptimum Tracking with Evolution Strategies\r\n\r\n";
        FetcherImpl fetcher = new FetcherImpl(title);
        fetcher.getMetaData();

    }
    

    public FetcherImpl(String page1) throws IOException {

        // start at first and get string till second newline
        this.begin = page1.indexOf("\n");
        this.end = page1.indexOf("\n", this.begin + 1);

        System.out.println(page1.indexOf("\n"));

        while ((this.end - this.begin) < 5) {
            this.begin++;
            this.end = page1.indexOf("\n", this.begin);
        }
        // store title for output 
        titleOut = page1.substring(this.begin, this.end - 1);

        // get String between begin and end, replace all spaces with +sign
        this.title = page1.substring(this.begin, this.end - 1).replaceAll(" ", "+");
        this.url = "http://citeseerx.ist.psu.edu/search?q=" + this.title + "&submit=Search&sort=rlv&t=doc";

    }
    // Constructor that gets title from document
    public FetcherImpl(String title, String temp) throws IOException {

        // get String between begin and end, replace all spaces with +sign
        this.title = title.replaceAll(" ", "+");
        System.out.println(this.title);
        this.url = "http://citeseerx.ist.psu.edu/search?q=" + this.title + "&submit=Search&sort=rlv&t=doc";

    }

    /**
     *
     * @return returns a HashMap(String,String) of the Metadata found online
     * @throws IOException
     */
    public HashMap getMetaData() throws IOException {

        System.out.println("Titel :" + this.title);
        System.out.println("URL :" + this.url + "\n");
        System.out.println("Testing 1 - Send Http GET request");

        Connection con = new Connection();

        // first request to get the link
        this.link = parseLink(con.createConnection(this.url));
        //System.out.println(con.createConnection(this.url));
        // second request to the link to get the page behind
        this.meta = con.createConnection("http://citeseerx.ist.psu.edu" + this.link);
        // parse hole page to get metafiles from it
        System.out.println(parseMeta(this.meta, this.metaMap));
        //  http.parseMeta(meta,metaMap);

        System.out.println("\n");
        return metaMap;
    }

    /**
     *
     * @param response : input is url to list of search results
     * @return url to page with highes ranking found in search results of
     * citeseerx
     */
    private String parseLink(String response) {
        String linkToMeta;

        // get link of first entry in search results
        Document doc = Jsoup.parse(response);
        Element result = doc.getElementById("result_list");
        Elements links = result.getElementsByTag("a");

        // for (Element link : links) {
        // String linkHref = link.attr("href");
        //System.out.print(links.attr("href") + "\n");
        linkToMeta = links.attr("href") + "\n";
        return linkToMeta;
        //}
    }

    /**
     * Function goes throu HTML, searches for meta block by ID, extracts entries
     * and puts them into a HashMap
     *
     * @param response plain HTML
     * @param metaMap Map to store entries in
     * @return HashMap of the Metadata Entries
     */
    private HashMap parseMeta(String response, HashMap metaMap) {
        String metaString = "";

        // get link of first entry in search results
        Document doc = Jsoup.parse(response);

        Elements metaTag = doc.getElementsByTag("meta");

        //metaMap.put("Title", titleOut);
        // print all MetaData
        //System.out.println(meta.toString());
        for (Element metaElement : metaTag) {
            // add in Title
            if ("citation_title".equals(metaElement.attr("name"))) {
                metaString = metaElement.attr("content");
                metaMap.put("Title", metaString);
            }
            // add in Author
            if ("citation_authors".equals(metaElement.attr("name"))) {
                metaString = metaElement.attr("content");
                metaMap.put("Author", metaString);
            }
            // add in Year
            if ("citation_year".equals(metaElement.attr("name"))) {
                metaString = metaElement.attr("content");
                metaMap.put("Year", metaString);
            }
            // add in Abstract
            if ("description".equals(metaElement.attr("name"))) {
                metaString = metaElement.attr("content");
                metaMap.put("Abstract", metaString.substring(12, metaString.length()));
            }
            // add in Conference
            if ("citation_conference".equals(metaElement.attr("name"))) {
                metaString = metaElement.attr("content");
                metaMap.put("Conference", metaString);
            }

        }

        return metaMap;
    }

}
