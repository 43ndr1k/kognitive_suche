package kognitiverAlgorithmus;

import kognitiverAlgorithmus.searchAlgorithm;
import kognitiverAlgorithmus.suchobjekt;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		suchobjekt[] ergebnis =new suchobjekt[2];
		ergebnis[0] = new suchobjekt("https://portal.imn.htwk-leipzig.de/fakultaet/weicker","Karsten Weicker, Prof. Dr. rer. nat. � Fakult�t Informatik","Prof. Dr. rer. nat. Karsten Weicker Karsten Weicker, Prof. Dr. rer. nat. Leitungen und �mter Studienfachberater (Informatik) Studienkommission Informatik (Vorsitzender) Studiendekan (Informatik) Fakult�tsrat (Mitglied ) Aufgabenbereiche Lehrgebiet: Praktische Informatik Kontaktinformationen Sprechzeit: nach Vereinbarung Z410  Gustav-Freytag-Str. 42A 04277 Leipzig karsten.weicker [at] htwk-leipzig.de  +49 (0) 341 3076-6395 Lebenslauf1990-1997	Studium der Informatik mit Nebenfach Mathematik, Universit�t Stuttgart 1995-1997	Studium der Computer Science, University of Massachusetts in Amherst Gutachter f�r folgende Zeitschriften: IEEE Transactions on Evolutionary Computation, Evolutionary Computation Journal, ACM Computing Surveys, Information Processing Letters, Softcomputing Journal, Genetic Programming and Evolvable Machines");
		ergebnis[1] = new suchobjekt("http://www.weicker.info/","Informationen �ber die Weicker-Familie","www.weicker.info Informationen �ber die Weicker-Familie	Karsten Weicker [Filme]	Die vollst�ndige Sammlung der Filme, die ich auf gro�er Leinwand gesehen habe - bald wieder online [Musik]	Eine grobe Sammlung der memorizable live acts [Evolution�re Algorithmen]	Das Lehrbuch in der 2. Auflage");
		searchAlgorithm suche = new searchAlgorithm();
		
		suche.kognitivSuchen(ergebnis, "Karsten Weicker");
	}

}
