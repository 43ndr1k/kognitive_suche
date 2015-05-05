package de.cpdfjarrtm.pdfborx.keyword;

/**
 * Straight-Forward Keyword data class, consisting only of term and weight,
 * without any additional calculation etc.
 * 
 * @author  Philipp Kleinhenz
 */
public class SimpleKeyword implements Keyword {
    private final String term;
    private float weight;

    /**
     * Creates a keyword with a default weight of 0.
     * @param   term
     *          The term.
     */
    public SimpleKeyword(String word) {
        this.term = word;
        weight = 0;
    }

    /**
     * Creates a keyword with given term and weight.
     * @param   term
     *          The term.
     * @param   weight 
     *          The weight.
     */
    public SimpleKeyword(String term, float weight) {
        this.term = term;
        this.weight = weight;
    }

    @Override
    public String getTerm() {
        return term;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Keyword o) {
        return Float.compare(weight, o.getWeight());
    }
    
}
