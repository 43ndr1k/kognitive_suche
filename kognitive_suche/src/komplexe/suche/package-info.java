/**
 *         Dieses Package dient dazu, gefundene Strings auf wichtige Schlüsselwörter (Tags)
 *         zu untersuchen. 
 *         
 *         Diese werden durch spezielle Eigenschaften wie Nähe zum Suchwort, Metakeys
 *         bzw. relevante Wörter(pdfbox), Titel der Website, Häufigkeit. o.Ä. nach Relevanz
 *         geordnet und geordnet zurückgegeben.
 *         <p> 
 *         Es werden Suchobjekte mit den Eigenschaften: Textbeispiel, Titel (PDF-Buch oder Website),
 *         Adresse, Meta-Keys(falls vorhanden) oder pdf-schluesselbegriffe, -- diese werden in der
 *         Klasse  {@code suchobjekt}  definiert -- sowie der Suchbegriff, welcher eingegeben wurde.
 *         <p>
 *         !!!Wichtig: Das Zeichenformat muss nach UTF-8 konvertiert werden, damit Sonderzeichen korrekt erkannt werden.!!!
 *         
 *         @author tlenz
 */
package komplexe.suche;

//TODO lesen, evtl. Doc-Comment korrigieren, diese ganze Sache mit "//" löschen

//Was in deiner Package-Annotation falsch war: 
// als erstes kommt die kurze Beschreibung, ein Satz, am besten nicht länger als eine Zeile. Den Punkt nicht vergessen.
// diese kurze Beschreibung kommt ins Overview aller Packete und noch in paar Stellen rein.
// Tag "return" wird bei Packeten nicht genutzt. Diese Information dann einfach in die Beschreibung reinschreiben

//@return Liste von Tags mit Ihren jeweiligen Websiten 

// Neuer Absatz im Kommentar wird beim Erstellen von htmls ignoriert. Deshalb an dieser Stelle ein <p> reinschreiben, siehst du schon