package komplexeSuche;




import java.util.ArrayList;
import java.util.Collections;

public class searchAlgorithm {

	
	
	
	/**
	 * Algorithmus zur Erkennung von Schlüsselbegriffen
	 * Komplexität n³ 
	 * 
	 * @author Tobias Lenz
	 */
	
	private String text;
	
	
	
	public ArrayList<tags> kognitivSuchen(suchobjekt[] ergebnis, String searchword){
		
		boolean flag  = true;
		int anzTags = 0;
		int numbcontsearchword = 0;		//Anzahl der im Text entahltenen Suchwörter
		int range = 5;
		
		ArrayList<tags> tagfrequency = new ArrayList<tags>();	//Datentyp für häufigste Suchwörter
		ArrayList<taglist> tagnearby = new ArrayList<taglist>();	//Datentyp für Umgebungssuchwörter
		ArrayList<tags> retlist = new ArrayList<tags>();	//Datentyp für die Rückgabe
		for( int i = 0; i < ergebnis.length; i++){
			
			text = ergebnis[i].getsearchtext();
			text = text.replaceAll("[^a-zA-Z0-9 .äüöÄÖÜß@]", "");		//hier werden alle Zeichen aus dem Text gelöscht, welche weder Zahlen, Buchstaben, . oder Leerzeichen sind 
			                                                            //Hinweis: "Reguläre Ausdrücke"
			String[] parts = text.split(" ");
			
			for(int j = 0; j < parts.length; j++){
				if(doescontain(searchword, parts[j])){
					tagnearby.add(numbcontsearchword, new taglist(ergebnis[i].getlink(), parts[j]));
					int num = 0;
					for(int l = 1; l < range; l++){
						if(j-l >= 0){
							tagnearby.get(numbcontsearchword).addtag(num, parts[j-l]);
							num++;
						}
						
						if(j+l < parts.length){
							tagnearby.get(numbcontsearchword).addtag(num, parts[j+l]);
							num++;
						}
					}
					numbcontsearchword++;
					
				}
				flag = true;
				for(int k = 0; k < tagfrequency.size(); k++){
				
					
				
					if(parts[j].equals(tagfrequency.get(k).gettag()) && !parts[j].equals("") && !badWord(parts[j]) && !doescontain(searchword, parts[j])){
						flag = false;
						tagfrequency.get(k).addaddress(ergebnis[i].getlink());
						tagfrequency.get(k).setcount();
						break;
					}
					
					
					}
					if(flag){						
						tagfrequency.add(anzTags, new tags(parts[j], ergebnis[i].getlink()));
						anzTags++;
				}
			}
			
		}
		Collections.sort(tagfrequency, new SortTags());
		retlist = merge(tagfrequency, tagnearby, ergebnis, searchword);
		int n = tagfrequency.size()-1;
		System.out.println(tagfrequency.get(n).gettag());
		return retlist;
		
		
	}



	



	private boolean badWord(String string) {
	  
	  String wordList[] ={"der", "die", "das","und","des","da","wo","von","den"};
	  
	 
	  
	  
	  for(int i = 0; i < wordList.length; i++){
	    if(string.equals(wordList[i])){
	      return true;
	    }
	  }
	  
    return false;
  }







  private ArrayList<tags> merge(ArrayList<tags> tagfrequency,
			ArrayList<taglist> tagnearby, suchobjekt[] ergebnis,
			String searchword) {
		
		return null;
	}



	private boolean doescontain(String searchword, String string) {
		String[] parts = searchword.split(" ");
		for(int i= 0; i<parts.length; i++){
			if(parts[i].equals(string)){
				return true;
			}
		}
		return false;
	}
	
}


