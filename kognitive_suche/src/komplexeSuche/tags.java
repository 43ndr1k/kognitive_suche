package komplexeSuche;

import java.util.ArrayList;

public class tags{
  
  /**
   * @author Tobias Lenz
   */

	private Short count;
	private String tag;
	private ArrayList<String> address= new ArrayList<String>(); 

	tags(String tag, String firstaddress){

		this.tag = tag;
		address.add(firstaddress);
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
	public void addaddress(String addresse){
		address.add(addresse);
	}
	public ArrayList<String> getaddress(){
		return address;
	}
	public void overrideaddresslist(ArrayList<String> addresses){
		this.address = addresses;
	}
	
	
}

