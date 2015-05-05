package de.cpdfjarrtm.pdfborx.parser.biblio;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;

/**
 * Class to find and parse bibliographies of documents.
 * 
 * @author Julian Goetz
 */
public class BibliographyParser{

    private String previouspages;
    private Pattern bibliographylocator;
    private Pattern citationparser;
    private Pattern yearlocator;
    private Pattern separators;
    private Pattern authlocatorInitialsFirst;
    private Pattern authlocatorSurnameFirst;
    private Matcher matcher;
    private String citationpattern;
    private final int minCitationLength = 50;
    
    
    public BibliographyParser(){
        /*
         * citationpattern is adopted from:
         * http://linklens.blogspot.de/2009/04/citation-parsing-regular-expression.html
         */
        citationpattern = "([^e][^d][^s][^\\.]\\s|\\d+\\.?\\s|^)([A-Z][a-z]{1,},?((\\s[A-Z](\\.|,|\\.,))(\\s?[A-Z](\\.|,|\\.,))*))(\\s?(,|and|und|&|,\\s?and|und)?\\s?([A-Z][a-z]{1,},?((\\s[A-Z](\\.|,|\\.,))(\\s?[A-Z](\\.|,|\\.,))*)))*\\s*(\\(?\\d\\d\\d\\d\\)?\\.?)?\\s*(\"|“)?(([A-Za-z:,\\r\\n]{2,}\\s?){3,})\\.?(\"|”)?";
        citationparser = Pattern.compile(citationpattern);
        bibliographylocator = Pattern.compile("(\n{0,5})(.{0,25})(References|Bibliography|Literature|Citations|Bibliografie|Literatur|Quellen)(.{0,25})(\n)(.*)", Pattern.CASE_INSENSITIVE);
        yearlocator = Pattern.compile("([^\\w\\d])(\\d\\d\\d\\d)([^\\w\\d])");
        separators = Pattern.compile("\\.|,|'|\"|“|”");
        authlocatorInitialsFirst = Pattern.compile("(([A-Z]\\.\\s)?[A-Z]\\. [A-Z][a-z]+)");
        authlocatorSurnameFirst = Pattern.compile("([A-Z][a-z]+, [A-Z]\\.(\\s?[A-Z]\\.)?)");
        previouspages = "";
    }
    
    /**
     * This will return the whole bibliography of a document.
     * 
     * @param document  The bibliography of this document will be returned.
     * @return The bibliography as a String. Null, if it was not found.
     */
    public String getBibliography(DocumentPdf document) {

        String bibliography = null;
        if (document == null){
            return null;
        }
        //The search for the bibliography starts at the last page
        for (int i = document.getNumberOfPages(); i >= document.getNumberOfPages()*0.66; i--){
            matcher = bibliographylocator.matcher(document.getText(i));
            
            if (matcher.find()) {

                bibliography = document.getText(i).substring(matcher.end(5))+previouspages;
                i = 0;
                
            }
            
            previouspages = document.getText(i)+previouspages;
        }
        
        if (bibliography == null){
             bibliography = searchForBibliography(document);
        }
        return bibliography;
    }
    
    /**
     * This function will search through a list of citations for its titles and authors.
     * 
     * @param citations These citations will be parsed.
     * @return The list citations. To get the title or author of one citation use its method getTitle() or getAuthor().
     */
    public ArrayList<Citation> parseCitations(ArrayList<Citation> citations){
        String completeCit;
        Matcher separatorMatcher;
        int firstIndexofTitle, lastIndexofTitle;
        // Group 18 of citationpattern contains the title.
        int titleGroup = 18;
        
        if (citations == null){
            return null;
        }
        
        for (Citation cit : citations){
            completeCit = cit.getcompleteCit();
            matcher = citationparser.matcher(completeCit);
            
            while (matcher.find()) {
                
                if (matcher.group(8) == null){
                    cit.setAuthors(matcher.group(2));
                }
                else{
                    cit.setAuthors(matcher.group(2)+matcher.group(8));
                }
                
                firstIndexofTitle = matcher.start(titleGroup);
                lastIndexofTitle = matcher.end(titleGroup);
                
                /*
                 * citationpattern does not completely grab a title if it contains characters like '-', '—'.
                 * If the characters around the end a found title are no separators, it will go on
                 * until one is found.
                 */

                separatorMatcher = separators.matcher(completeCit.substring(lastIndexofTitle-3, lastIndexofTitle));
                if (!separatorMatcher.find()){

                    separatorMatcher = separators.matcher(completeCit);
                    
                    if (separatorMatcher.find(lastIndexofTitle)){
                        
                        lastIndexofTitle = separatorMatcher.end()-1;
                        cit.setTitle(completeCit.substring(firstIndexofTitle, lastIndexofTitle));
                        
                    }
                    else{
                        cit.setTitle(matcher.group(18));
                    }
                    
                }
                else{
                    cit.setTitle(matcher.group(18));
                }
            }
            
            if (cit.getTitle() == null){
                cit = setAuthorAndTitle(cit);
            }
        }
        return citations;
    }
    
    /**
     * This will split a complete bibliography into its citations.
     * 
     * @param bibliography The bibliography to be split.
     * @return An ArrayList. One citation per element.
     */
    public ArrayList<Citation> splitBibliography(String bibliography){
        if (bibliography == null){
            return null;
        }
        
        bibliography = bibliography.replace('-', ' ');
        ArrayList<Citation> citations = new ArrayList<Citation>();
        // 1 element = 1 row
        String[] splitbibliography = bibliography.split("\n"); 
        //saves incomplete parts of citations
        String tempcit = "";    

        matcher = yearlocator.matcher(splitbibliography[0]);
        
        //If true, the year is given at the start of each reference. If false, the year is given at the end.
        if (matcher.find()){
            
            for(int i = 0; i < splitbibliography.length; i++){

                matcher = yearlocator.matcher(splitbibliography[i]);
                
                if(matcher.find()){                    
                    
                    tempcit = "";
                    tempcit = splitbibliography[i];

                }
                else{
                    
                    tempcit = tempcit+" "+splitbibliography[i];
                    
                    if (i < splitbibliography.length-1){
                        //Looks one row ahead. If a new year is found, a new citation begins.
                        matcher = yearlocator.matcher(splitbibliography[i+1]);
                        if (matcher.find()){

                            if (tempcit.length() > minCitationLength){
                                citations.add(new Citation(tempcit));
                            }
                            
                        }
                    }
                    else{
                        //Last citation. No need to look a row ahead.
                        
                        if (tempcit.length() > minCitationLength){
                            citations.add(new Citation(tempcit));
                        }
                        
                    }

                }
            }

        }
        else{
            
            for(int i = 0; i < splitbibliography.length; i++){
                
                matcher = yearlocator.matcher(splitbibliography[i]);
                
                if(matcher.find()){
                    
                    tempcit = tempcit+" "+splitbibliography[i];

                    if (tempcit.length() > minCitationLength){
                        citations.add(new Citation(tempcit));
                    }
                    
                    tempcit = "";
                    
                }
                else{
                    tempcit = tempcit+" "+splitbibliography[i];
                }
            }
            
        }
        
        return citations;
    }
    
    private Citation setAuthorAndTitle(Citation cit){
        String completeCit = cit.getcompleteCit();
        String assumedTitle = getAssumedTitle(completeCit);
        
        int firstIndexOfTitle = completeCit.indexOf(assumedTitle);
        
        cit.setTitle(assumedTitle);
        cit.setAuthors(getAssumedAuthors(completeCit, firstIndexOfTitle));
        
        return cit;
    }
    
    private String getAssumedTitle(String completeCit) {
        //a title is often the longest String between two separator characters
        String[] splitbySeparators = completeCit.split("\\.|,|'|\"|“|”");
        int maxLength = 0;
        String assumedTitle = null;
        
        for (String s : splitbySeparators) {
            if (s.length() > maxLength) {
                maxLength = s.length();
                assumedTitle = s;
            }
        }
        return assumedTitle;
    }
    
    private String getAssumedAuthors(String completeCitation, int firstIndexOfTitle){
        String authors = null;
        matcher = authlocatorInitialsFirst.matcher(completeCitation);
        
        if (firstIndexOfTitle != -1){
            matcher.region(0, firstIndexOfTitle);
        }
        
        if (matcher.find()){
            matcher.reset();
            
            if (firstIndexOfTitle != -1){
                matcher.region(0, firstIndexOfTitle);
            }
            
            while (matcher.find()){
                if (authors == null){
                    authors = matcher.group(1);
                }
                else{
                    authors = authors+", "+matcher.group(1);
                }
            }
        }
        else{
            matcher = authlocatorSurnameFirst.matcher(completeCitation);
            while (matcher.find()){

                if (authors == null){
                    authors = matcher.group(1);
                }
                else{
                    authors = authors+", "+matcher.group(1);
                }
            }
        }
        
        return authors;
    }   
    
    /**
     * This will scan a document for its bibliography.
     * Use method getBibliography, it's less prone to mistakes and will only use this function
     * if it can't find anything.
     * 
     * @param document This document will be scanned
     * @return The whole bibliograpy.
     */
    public String searchForBibliography (DocumentPdf document){
        int startOfBibliography = 0;
        int endOfBibliography = 0;
        int noYearCounter = 0;
        int maxnoYearCounter = 0;
        String pages = "";
        
        for (int i = (int)(document.getNumberOfPages()*0.66); i <= document.getNumberOfPages(); i++){
            pages = pages + document.getText(i);
        }
        String[] lines = pages.split("\n");
        
        //Reverse searching for years through the last pages. First tries to find the end of a bibliography.
        //Then saves each line containing a year until there is no match for at least 6 lines.
        for (int i = lines.length-1; i >= 0; i--){

            if (maxnoYearCounter < noYearCounter){
                maxnoYearCounter = noYearCounter;
            }
            
            String s = lines[i];
            matcher = yearlocator.matcher(s);

            if (matcher.find()){

                if (endOfBibliography == 0){
                    if (i+3 < lines.length){
                        endOfBibliography = i+3;
                    }
                    else{
                        endOfBibliography = i;
                    }
                }
                else{
                    
                    if (i-maxnoYearCounter >= 0){
                        startOfBibliography = i-maxnoYearCounter;
                    }
                    else{
                        startOfBibliography = i;
                    }
                    
                }

                noYearCounter = 0;
            }
            
            else{
                noYearCounter++;
            }
            if (noYearCounter > 5){
                i = -1;
            }
            
        }

        if(lines.length != 0){
            int indexStartofBib = pages.indexOf(lines[startOfBibliography]);
            int indexEndofBib = pages.indexOf(lines[endOfBibliography]);
            
            return pages.substring(indexStartofBib, indexEndofBib);
        }
        return "";
        
    }
    
}
