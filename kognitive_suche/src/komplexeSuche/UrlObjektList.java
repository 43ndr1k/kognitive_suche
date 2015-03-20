package komplexeSuche;

import java.util.ArrayList;

public class UrlObjektList {
	
	private String searchword;
	private ArrayList<UrlObjekt> urlObjects = new ArrayList<UrlObjekt>();
	
	public UrlObjektList(String searchword){
		this.setSearchword(searchword);
	}

	public String getSearchword() {
		return searchword;
	}

	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}

	public ArrayList<UrlObjekt> getUrlObjects() {
		return urlObjects;
	}

	public void setUrlObjects(ArrayList<UrlObjekt> urlObjects) {
		this.urlObjects = urlObjects;
	}

	public void addUrlObject(UrlObjekt url){
		urlObjects.add(url);
	}
	
	public int getSize(){
		return urlObjects.size();
	}
		
	
}
