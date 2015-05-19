package de.leipzig.htwk.websearch;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import komplexe.suche.Statics;

public class WebSearchThread extends Thread {

	/**
	 * Abgeleitet von Thread und ist somit einer der parallel auzuführenden
	 * Vorgänge. Hier sollten die Funktion zum aufrufen der URL bishin zum
	 * herausfiltern der Tags aufgerufen werden!
	 * 
	 * @author Franz Schwarzer
	 * @param url
	 *            Url der Webseite
	 * @param urlNumber
	 *            Um das wievielte Suchergebniss handelt es sich?
	 * @param keys
	 *            Sollte irgendwann die Tags der URL wiederspiegeln
	 */

	String url;
	int urlNumber;
	String[] keys;
	boolean ready = false;

	WebSearchThread(String url, int urlNumber) {
		this.url = url;
		this.urlNumber = urlNumber;
	}

	@Override
	public void run() {

		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				stoppeEndless();
			}
		}, TimeUnit.SECONDS.toMillis(3));

		this.keys = setTags();
	}

	String[] setTags() {

		/**
		 * @return Rückgabe wird später durch das entsprechende Objekt ersetzt
		 *         Hier müssen ausserdem die Funktionen des CognitiveSearch mit
		 *         eingebunden werden
		 * 
		 */

		HTMLTools html = new HTMLTools();
		String sc = html.getHTMLSourceCode(url);

		Static.pageText[urlNumber] = sc;
		// String[] mk=html.getMetaKeys(sc);
		ready = true;
		return null;

	}

	@SuppressWarnings("deprecation")
	void stoppeEndless() {
		if (ready == false) {
			Static.pageText[urlNumber] = "";
			System.out.println(urlNumber + " iCh bin tot");
			this.stop();
		} else {
			System.out.println(urlNumber + " ICH BIN FERTIG");
		}

	}

}
