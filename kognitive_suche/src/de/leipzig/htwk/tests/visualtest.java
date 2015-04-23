package de.leipzig.htwk.tests;


import komplexe.suche.TagObject;
import komplexe.suche.TagObjectList;
import visualize.VisController;

public class visualtest {
	private static TagObjectList tags = new TagObjectList("Sigma");
	public static void visualtest() {
		
		
		

		}
	
	public TagObjectList getTags(){
		TagObject tags1 = new TagObject("Buchstabe");
		tags1.addurl(0, "http://de.wikipedia.org/wiki/Sigma");
		tags1.addurl(1, "http://de.wikipedia.org/wiki/Griechisches_Alphabet");
		tags1.addurl(2, "http://denl.dict.cc/?s=Sigma+%CE%A3+%CF%83+%CF%82+C+%5Bgriechischer+Buchstabe%5D");
		tags1.addurl(3, "http://www.uni-protokolle.de/Lexikon/Sigma.html");
		
		TagObject tags2 = new TagObject("Sport");
		tags2.addurl(0, "http://www.sigma-run.com/de/");
		tags2.addurl(1, "http://www.sigmasport.de/de/startseite/?flash=1");
		tags2.addurl(2, "http://www.fahrrad.de/sigmasport.html");
		
		TagObject tags3 = new TagObject("Kamera");
		tags3.addurl(0, "http://www.sigma-foto.de/produkte/kameras.html");
		tags3.addurl(1, "http://www.sigma-foto.de/home.html");
		tags3.addurl(2, "http://de.wikipedia.org/wiki/Sigma_%28Unternehmen%29");
		tags3.addurl(3, "http://www.dkamera.de/testbericht/sigma-dp2/");
		tags3.addurl(4, "http://www.amazon.de/Sigma-Kamera-Foto-Elektronik/s?ie=UTF8&page=1&rh=n%3A571860%2Ck%3ASigma");
		
		TagObject tags4 = new TagObject("S");
		
		TagObject tags5 = new TagObject("Bla");
	
		
		tags.addTagObject(tags1, 0);
		tags.addTagObject(tags2, 1);
		tags.addTagObject(tags3, 2);
		tags.addTagObject(tags4, 3);
		tags.addTagObject(tags5, 4);

		return tags;
	}
}
