package de.cpdfjarrtm.pdfborx.index;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple List of Search results. Each result consists of an document id and
 * a float scoring value (similarity).
 * 
 * @author  Philipp Kleinhenz
 */
public class SearchResult extends ArrayList<SearchResult.ResultEntry> {
    public void addResult(long id, float score) {
        this.add(new ResultEntry(id, score));
    }
    
    /**
     * Sort the results ascending by score.
     */
    public void sort() {
        Collections.sort(this);
    }
    
    /**
     * A single search result entry. Consists of an document id and a float
     * scoring value.
     */
    public class ResultEntry implements Comparable<ResultEntry> {
        private final long id;
        private final float score;
        
        public ResultEntry(long id, float score) {
            this.id = id;
            this.score = score;
        }
        
        /**
         * Get the results document id.
         * @return  The document id.
         */
        public long getId() {
            return id;
        }
        
        /**
         * Get the results score.
         * @return  The score as float.
         */
        public float getScore() {
            return score;
        }
        
        @Override
        public int compareTo(ResultEntry o) {
            return Float.compare(this.score, o.score);
        }
        
    }
}
