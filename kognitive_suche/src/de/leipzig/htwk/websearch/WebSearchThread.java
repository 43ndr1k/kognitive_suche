package de.leipzig.htwk.websearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

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
		}, TimeUnit.SECONDS.toMillis(2));
		setTextAndKeywords();
	}

	void setTextAndKeywords() {

		/**
		 * Mit dieser Funktion werden in Static Keywords und PageText der Url
		 * gesetzt
		 */

		HTMLTools html = new HTMLTools();
		Document doc = html.getHTMLDocument(url);
		Static.pageText[urlNumber] = html.getHTMLText(doc);
		String keywords = html.getMetaKeys(doc);
		if(keywords==null){
			Static.keywords[urlNumber]=null;
		}else{
		Static.keywords[urlNumber] = keywords.split(Pattern.quote("."));
		}
		ready = true;

	}

	@SuppressWarnings("deprecation")
	void stoppeEndless() {
		if (ready == false) {
			Static.pageText[urlNumber] = "";
			System.out.println(urlNumber + " iCh bin tot");
			this.stop();
		} else {
			System.out.println(urlNumber + " ICH BIN FERTIG");
			stop();
		}

	}

}
