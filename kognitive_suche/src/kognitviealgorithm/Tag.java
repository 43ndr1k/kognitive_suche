package kognitviealgorithm;

import java.util.ArrayList;

public class Tag {
  
  /** 
   * @author Tobias Lenz
   */

	private ArrayList<String> tags = new ArrayList <String>();
	private int textBlocNumber;
	private String searchWord;
	
	Tag(int textBlocNumber, String searchword){
		this.textBlocNumber = textBlocNumber;
		this.searchWord = searchword;
	}
	
	public int gettextBlocNumber(){
		return textBlocNumber;
	}
	
	public String getsearchword(){
		return searchWord;
	}
	
	public void addtag(int number, String tag){
		tags.add(number, tag);
	}
	
	public String gettag(int number){
		return tags.get(number);
	}
	
}
