package de.leipzig.htwk.search.history;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Klasse zum Speichern und Laden des Suchverlaufs
 * 
 * @author Fabian Freihube
 *
 */
public class SearchHistory {

	File historyFile;
	public ArrayList<HistoryObject> historyData;

	/**
	 * Öffnet die Hitsory-Datei
	 */
	public SearchHistory() {
		historyFile = new File("history.last");

		if (!historyFile.exists()) {
			try {
				historyFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			historyData = new ArrayList<HistoryObject>();
		} else {

			FileInputStream fis;
			ObjectInputStream ois;

			try {
				fis = new FileInputStream(historyFile);
				ois = new ObjectInputStream(fis);
				historyData = (ArrayList<HistoryObject>) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Fügt der Verlaufsliste das gesuchte Wort und den Zeitpunkt der Suche hinzu
	 * 
	 * @param searchWord Suchwort
	 */
	public void addSearch(String searchWord) {
		// TODO Auto-generated method stub

		HistoryObject newObject = new HistoryObject(searchWord);
		historyData.add(newObject);
		saveSearch();	
	}
	
	public void saveSearch() {
	  FileOutputStream fos;
	    ObjectOutputStream oos;

	    try {
	        fos = new FileOutputStream(historyFile);
	        oos = new ObjectOutputStream(fos);
	        oos.writeObject(historyData);
	        oos.close();
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<HistoryObject> getSearches() {
		return historyData;
	}

}
