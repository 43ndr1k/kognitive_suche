package kognitive_suche.src.komplexeSuche;

public class suchobjekt {

	private String link, titel, searchtext;

	/**
	 * @author Tobias Lenz
	 * Beispielhafte Implementation des Objekts Suchergebnis
	 * Hinweis: in der Zeichenkette searchtext stehen die Begriffe, welche in dem Suchergebnis um den Suchtext herum gefunden wurden; getrennt durch Leerzeichen
	 * @param link
	 * @param titel
	 * @param searchtext
	 */
	public suchobjekt(String link, String titel, String searchtext){
		this.link = link;
		this.titel = titel;
		this.searchtext = searchtext;
		
	}
	public String getlink() {
		return link;
	}
	public String gettitel() {
		return titel;
	}
	public String getsearchtext() {
		return searchtext;
	}
	
	
}
