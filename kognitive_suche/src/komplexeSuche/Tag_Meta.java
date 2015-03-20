package komplexeSuche;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class Tag_Meta extends Thread {
	
	//@author Franz Schwarzer
	
	String url;
	int urlNumber;
	String[] keys;

	
	Tag_Meta(String url, int urlNumber){
		this.url=url;
		this.urlNumber=urlNumber;
	}

	@Override public void run(){
		System.out.println(this.url);
		this.keys=getMetaKeys();
		Statics.keys=this.keys;
		Statics.urlkeys[urlNumber]=keys;
		Statics.url[urlNumber]=url;
		System.out.println(Statics.urlkeys[urlNumber][0]); 
	}
	
	
	
	
	String[] getMetaKeys(){
		StringBuilder sb=new StringBuilder();
		 
		try {
			 Scanner scanner = new Scanner(new URL(url).openStream());
			 while (scanner.hasNextLine()) {
			 sb.append(scanner.nextLine() + "\n");
			 }
			 scanner.close();
			 } catch (MalformedURLException e) {
			 e.printStackTrace();
			 } catch (IOException e) {
			 e.printStackTrace();
			 }
		//System.out.println(sb.toString());
		
	// Suche nach Meta Key
		Statics.pageText[urlNumber]=sb.toString();
		int index = sb.toString().indexOf( "<meta name=\"keywords\" content=");
		String sub=sb.toString().substring(index);
		index=sub.indexOf("/>");
		sub=sub.substring(0, index);
		
		for(int i=0;i<2;i++){
		index = sub.indexOf( "\"", sub.indexOf("\"") + 1 ); 
		sub=sub.substring(index);
		}
		
		sub=sub.substring(1,sub.length()-2);
		String[] keys=sub.split(", ");
		
		return keys;
		
	}
	
	ArrayList<String[]> UrlKeys(String[] urls){
		
		
		ArrayList<String[]> rueckgabe=new ArrayList<String[]>();
		
		
		for(int i =0;i<url.length();i++){
			
			
		}
		

		//liste.add(getMetaKeys("http://www.easy-coding.de/index.php/Thread/10300-HTML-auslesen-und-nach-spezifischen-Tags-suchen/"));
		//liste.add(getMetaKeys("https://www.youtube.com/?gl=DE&hl=de"));
		
		return rueckgabe;
	}

}
