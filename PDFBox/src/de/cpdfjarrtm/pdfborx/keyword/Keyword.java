package de.cpdfjarrtm.pdfborx.keyword;

/**
 * A keyword consists of a term and a assoctiated weight.
 * Weighing should use a normalized value between 0.0 and 1.0.
 * 
 * @author  Philipp Kleinhenz
 */
public interface Keyword extends Comparable<Keyword> {
    
    /**
     * Get the keyword term string.
     * @return  The keywords term string.
     */
    public String getTerm();

    /**
     * Get the keywords weight. 
     * @return  The weight as float between 0.0 and 1.0.
     */
    public float getWeight();

    /**
     * Set the keywords weight.
     * @param   weight
     *          The weight as float between 0.0 and 1.0.
     */
    public void setWeight(float weight);
    
}
