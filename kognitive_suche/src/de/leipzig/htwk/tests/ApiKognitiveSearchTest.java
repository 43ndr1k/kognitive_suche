package de.leipzig.htwk.tests;


import kognitviealgorithm.ApiCognitiveSearch;
import kognitviealgorithm.ReturnTagList;
import komplexeSuche.TagObjectList;

public class ApiKognitiveSearchTest {

  public static void main(String[] args) {

    ReturnTagList testlist = new ReturnTagList(null);
    
    ApiCognitiveSearch test = new ApiCognitiveSearch();
    test.ApiCognitiveSearch(getTest(), "Sigma Symbol Buchstabe griechischen");
    
  }

  private static String[] getTest() {
    String[] textbloc =
        {
            "Der griechische Buchstabe Sigma (griechisches Neutrum Σίγμα, Majuskel Σ, Minuskel im Wort σ, Minuskel am Wortende ς) ist der 18. Buchstabe des griechischen Alphabets und hat nach dem milesischen Prinzip einen numerischen Wert von 200. In der griechischen Sprache wird es als stimmloses „S“ gesprochen.1 Herkunft2 Verwendung3 Beispiele4 Zusätzliche Zeichen5 Weblinks Herkunft[Bearbeiten] Das phönizische Alphabet hatte vier Buchstaben für Zischlaute. Die Griechen übernahmen zwei davon für ihren S-Laut, die aber nie gleichzeitig in einem der unterschiedlichen altgriechischen Alphabete vorkamen. Der Buchstabe Samech wurde im ionischen Alphabet als X mit dem Lautwert ksi verwendet. Sein Name aber wurde in leicht abgewandelter Form auf den phönizischen Buchstaben „Sin“ übertragen, der nun im Griechischen Sigma hieß. Vom griechischen Sigma leiten sich sowohl das koptische und kyrillische С (über die im byzantinischen Reich verbreitete Form des Sigma, die dem C glich), als auch das lateinische S (über die gewöhnliche Majuskel) ab. Verwendung[Bearbeiten] als kleines Sigma σ im Qualitätsmanagement Six Sigma (3,4 Fehler pro einer Million Fehlermöglichkeiten) in der Physik für die Oberflächenspannung für die Fließspannung für die elektrische Flächenladungsdichte für die Stefan-Boltzmann-Konstante für die spezifische elektrische Leitfähigkeit eines Materials für die Pauli-Matrizen für den Wirkungsquerschnitt in der Mechanik für die Spannung in der Werkstoffkunde eine sehr spröde Phase im Fe-Cr-System die Zugspannung in der Informatik zur Bezeichnung der Selektion in der relationalen Algebra zur Bezeichnung der Selektivität einer Datenbankabfrage in der Mathematik in der Statistik für die Standardabweichung der Grundgesamtheit in der mathematischen Zahlentheorie für eine Teilersumme in der Funktionalanalysis für das Spektrum eines Linearen Operators in der Chemie zur Benennung einer Molekülorbital-Bindung zur Benennung des Sigma-Komplexes als Zeichen für die Volumenkonzentration in der Biologie für ein Protein, welches durch Bindung an die RNA-Polymerase für die Initiation der Transkription notwendig ist (siehe Sigma-Faktor ) in der Phonologie für eine Silbe in der Mikroökonomie als Zeichen für die Substitutionselastizität als großes Sigma Σ ein Bezeichner in der Physik für ein Σ-Baryon. Symbol in der Physik für ein Bezugssystem. Symbol in der Mathematik für Summe oder Signatur. Symbol in der Statistik für die Kovarianzmatrix Symbol in der Theoretischen Informatik für ein Alphabet In der Astronomie ist die Sigma die Geschwindigkeit, mit der sich die Sterne am äußersten Rand einer Galaxie bewegen. als Wort Sigma Verwendung in der Ökologie und Vegetationskunde: Sigma-Soziologie (Synsoziologie) , d.h. die Erfassung von Vegetationskomplexen Kurzbezeichnung in der Medizin für den S-förmig verlaufenden Teil des Dickdarms (Colon sigmoideum). Beispiele[Bearbeiten] Sokrates (Σωκράτης, altgriechische Aussprache Sōkrátēs, heutiges Griechisch Sokrátis) Zusätzliche Zeichen[Bearbeiten] In der griechischen Unzialen wurde folgende Variante verwendet, die im kirchlichen Kontext noch heute üblich ist: Ϲ (Unicode U+03F9: „CAPITAL LUNATE SIGMA SYMBOL“) statt Σ ϲ (Unicode U+03F2: „GREEK LUNATE SIGMA SYMBOL“) statt σ oder Weblinks[Bearbeiten] Wiktionary: Sigma – Bedeutungserklärungen, Wortherkunft, Synonyme, Übersetzungen",
            "Der Running Computer mit Lap- und PC-Funktion",
            "Der ROX 10.0 GPS ist der erste FAHRRAD COMPUTER von"};

    return textbloc;
  }

}
