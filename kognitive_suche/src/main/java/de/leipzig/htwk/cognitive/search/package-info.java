/**
 * Hier werden Schlüsselwörter(Tags) aus Textblöcken und einem Suchwort generiert {@code cogintive.search}.
 * Auf die Textblöcke wird eine Häufigkeitsanalyse und eine Umgebungsanalyse angewendet.
 * Die dadurch entstandenen Tags werden zusammengefügt und ihnen wird eine Priorität zugeordnet.
 * Danach ist es möglich bestimmte Tags zu entfernen oder Tag-Informationen die auf andere Weise erhalten wurden (Überschriften, Datenbanken)
 * hinzuzufügen.
 * <p>
 * Als letztes werden die Tags über die Klasse EditTags bearbeitet (stoplist, removeSearchWord, stem).
 * 
 * Die Tag werden als Objekt vom Typ ReturnTagList zurückgegeben.
 * @author Tobias Lenz
 * @author Steffen Schreiber
 */
package de.leipzig.htwk.cognitive.search;