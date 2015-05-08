package pdf.box.access;

public class Pdfkeyword implements Ipdfkeywords {

  /**
   * Keyword Object
   *
   * Includes the keyword and the weight.
   * 
   * @param term: the keyword itself.
   * @param weight: float between 0.0 and 1.0. Higher weight represents more important keyword.
   * 
   * @author Fabian Freihube
   */

  String term;
  float weight;

  public Pdfkeyword(String term, float weight) {
    this.term = term;
    this.weight = weight;
  }

  @Override
  public String getTerm() {
    // Auto-generated method stub
    return term;
  }

  @Override
  public float getWeight() {
    // Auto-generated method stub
    return weight;
  }

  @Override
  public void setWeight(float weight) {
    // Auto-generated method stub

  }

  @Override
  public void setTerm(String term) {
    // Auto-generated method stub
  }

}
