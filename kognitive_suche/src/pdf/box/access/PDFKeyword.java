package pdf.box.access;

public class PDFKeyword implements IPDFKeyword {

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

  public PDFKeyword(String term, float weight) {
    this.term = term;
    this.weight = weight;
  }

  @Override
  public String getTerm() {
    // TODO Auto-generated method stub
    return term;
  }

  @Override
  public float getWeight() {
    // TODO Auto-generated method stub
    return weight;
  }

  @Override
  public void setWeight(float weight) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setTerm(String term) {
    // TODO Auto-generated method stub
  }

}
