package komplexeSuche;

import java.util.ArrayList;

public class TagObjectList {
/**
 * @author Tobias Lenz
 * 
 * 
 * Objekt zur Uebergabe von Taginformatinen enthält eine ArrayList von TagObjects
 * Bei der Initialisierung soll das entsprechende Suchwort uebergeben werden
 * 
 */
	private static String searchword;
	private ArrayList<TagObject> tagObjects = new ArrayList<TagObject>();
	
	public TagObjectList(String searchword){
		this.searchword = searchword;
	}
	
	public void addTagObject(TagObject tag,int num){
		tagObjects.add(num, tag);
	}
	String getsearchword (){
		return searchword;
	}
	public TagObject getTagObject(int num){
		return tagObjects.get(num);
	}

	public int getsize() {
		return tagObjects.size();
	}
}
