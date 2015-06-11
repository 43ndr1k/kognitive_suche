package de.leipzig.htwk.search.history;

import java.io.Serializable;
import java.util.Date;

/**
 * Objekt welches Suchwort und Zeitpunkt der Suche enthält.
 * 
 * @author Fabian Freihube
 *
 * @param <searchWord> Suchwort
 * @param <date> Datum der Suche
 */
public class HistoryObject<searchWord, date> implements Serializable {
	public String searchWord;
	public Date date;

	public HistoryObject(String searchWord) {
		this.searchWord = searchWord;
		this.date = new Date();
	}
}
