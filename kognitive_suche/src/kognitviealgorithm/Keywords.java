package kognitviealgorithm;

import java.util.ArrayList;

public class Keywords{
  
  /**
   * @author Tobias Lenz
   */

	private Short count;
	private String tag;
	private ArrayList<Integer> textBlocNumber= new ArrayList<Integer>(); 

	Keywords(String tag, int firstTextBlocNumber){

		this.tag = tag;
		textBlocNumber.add(firstTextBlocNumber);
		this.count = 1;
	}
	
	public String gettag(){
		return tag;
	}
	public Short getcount(){
		return count;
	}
	public void setcount(){
		count++;
	}
	public void addaddress(int textBlocNumber){
	  this.textBlocNumber.add(textBlocNumber);
	}
	public ArrayList<Integer> gettextBlocNumber(){
		return textBlocNumber;
	}
	public void overrideaddresslist(ArrayList<Integer> textBlocNumber){
		this.textBlocNumber = textBlocNumber;
	}
	
	
}

