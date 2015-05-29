package de.leipzig.htwk.tests;

import static org.junit.Assert.*;
import cognitive.search.*;
import org.junit.Test;

public class CognitiveTest {

	
	@Test	
	public void doesContainTest0() {
		WordCount Word = new WordCount();
		String searchword ="Hallo wie geht es dir? Heute ist ein schönes Wetter oder?";
		String word =("Wetter");
		Word.doesContain(searchword, word);
		assertEquals(true, Word.doesContain(searchword, word));
	}
	
	@Test	
	public void doesContainTest1() {
		WordCount Word = new WordCount();
		String searchword ="Hallo wie geht es dir? Ist heute schönes Wetter?";
		String word =("Wetter");
		Word.doesContain(searchword, word);
		assertEquals(true, Word.doesContain(searchword, word));
	}
	
	@Test	
	public void doesContainTest2() {
		WordCount Word = new WordCount();
		String searchword ="Hallo wie geht es dir? Heute ist schönes Wetter oder?";
		String word =("schönes Wetter");
		Word.doesContain(searchword, word);
		assertEquals(false, Word.doesContain(searchword, word));
	}
	
	@Test	
	public void doesContainTest3() {
		WordCount Word = new WordCount();
		String searchword ="Hallo wie geht es dir? Ist heute schönes Wetter?";
		String word =("schönes Wetter");
		Word.doesContain(searchword, word);
		assertEquals(false, Word.doesContain(searchword, word));
	}
	
	@Test
	public void enCoding0() {
		String Word ="Müller-Lüdenscheidt";
		assertEquals("60550750206880022", ColognePhonetic.enCoding(Word));
	}
	
	@Test
	public void enCoding1() {
		String Word ="Muller-Ludenscheidt";
		assertEquals("60550750206880022", ColognePhonetic.enCoding(Word));
	}
	
	
	@Test
	public void enCoding2() {
		String Word ="Müller-Lü denscheidt";
		assertEquals("60550750206880022", ColognePhonetic.enCoding(Word));
	}
	
	@Test
	public void enCoding3() {
		String Word ="Müller-Lüdensch.,.eidt";
		assertEquals("60550750206880022", ColognePhonetic.enCoding(Word));
	}
	
	@Test
	public void enCoding4() {
		String Word ="M.ül#ler-Lüde*ns.~ch.,.eidt";
		assertEquals("60550750206880022", ColognePhonetic.enCoding(Word));
	}
	
	@Test
	public void enCoding5() {
		String Word ="M.ül#ler Lüde*ns.~ch.,.eidt";
		assertEquals("60550750206880022", ColognePhonetic.enCoding(Word));
	}
	
	@Test
	public void enCoding6() {
		String Word ="M.ül#les Cüde*ns.~ch.,.eidt";
		assertEquals("60550840206880022", ColognePhonetic.enCoding(Word));
	}
	
	@Test
	public void cleaningDoubles0() {
		String Word ="6005507500206880022";
		assertEquals("6050750206802", ColognePhonetic.cleaningDoubles(Word));
	}
	
	@Test
	public void cleaningDoubles1() {
		String Word ="660000005555550077555555000022066688880022";
		assertEquals("6050750206802", ColognePhonetic.cleaningDoubles(Word));
	}
	
	@Test
	public void cleaningDoubles2() {
		String Word ="0660000005555550077555555000022066688880022";
		assertEquals("06050750206802", ColognePhonetic.cleaningDoubles(Word));
	}
	
	@Test
	public void cleaningDoubles3() {
		String Word ="0000660000005555550077555555000022066688880022";
		assertEquals("06050750206802", ColognePhonetic.cleaningDoubles(Word));
	}
	
	@Test
	public void cleaningZeroes0() {
		String Word ="6050750206802";
		assertEquals("65752682", ColognePhonetic.cleaningZeroes(Word));
	}
	
	@Test
	public void cleaningZeroes1() {
		String Word ="06050750206802";
		assertEquals("065752682", ColognePhonetic.cleaningZeroes(Word));
	}
	
	@Test
	public void cleaningZeroes2() {
		String Word ="0t6050750206802.,:;#+hjfhjkmn";
		assertEquals("0t65752682.,:;#+hjfhjkmn", ColognePhonetic.cleaningZeroes(Word));
	}
	
	@Test
	public void isInList0() {
		String[] wordList = new String[5];
		EditTags edit = new EditTags(null);
		wordList[0] = "Hey";
		wordList[1] = "Hallo";
		wordList[2] = "weiter";
		wordList[3] = "gut";
		wordList[4] = "schlecht";
		String tag ="Hey";
		assertEquals(true, edit.isInList(wordList ,tag));
	}
	
	@Test
	public void isInList1() {
		String[] wordList = new String[5];
		EditTags edit = new EditTags(null);
		wordList[0] = "Hey";
		wordList[1] = "Hallo";
		wordList[2] = "weiter";
		wordList[3] = "gut";
		wordList[4] = "schlecht";
		String tag ="Hallo";
		assertEquals(true, edit.isInList(wordList ,tag));
	}
	
	@Test
	public void isInList2() {
		String[] wordList = new String[5];
		EditTags edit = new EditTags(null);
		wordList[0] = "Hey";
		wordList[1] = "Hallo";
		wordList[2] = "weiter";
		wordList[3] = "gut";
		wordList[4] = "schlecht";
		String tag ="weiter";
		assertEquals(true, edit.isInList(wordList ,tag));
	}
	
	@Test
	public void isInList3() {
		String[] wordList = new String[5];
		EditTags edit = new EditTags(null);
		wordList[0] = "Hey";
		wordList[1] = "Hallo";
		wordList[2] = "weiter";
		wordList[3] = "gut";
		wordList[4] = "schlecht";
		String tag ="gut";
		assertEquals(true, edit.isInList(wordList ,tag));
	}
	
	@Test
	public void isInList4() {
		String[] wordList = new String[5];
		EditTags edit = new EditTags(null);
		wordList[0] = "Hey";
		wordList[1] = "Hallo";
		wordList[2] = "weiter";
		wordList[3] = "gut";
		wordList[4] = "schlecht";
		String tag ="schlecht";
		assertEquals(true, edit.isInList(wordList ,tag));
	}
	
	@Test
	public void isInList5() {
		String[] wordList = new String[6];
		EditTags edit = new EditTags(null);
		wordList[0] = "Hey";
		wordList[1] = "Hallo";
		wordList[2] = "weiter";
		wordList[3] = "gut";
		wordList[4] = "schlecht";
		wordList[5] = "test";
		String tag ="hey";
		assertEquals(true, edit.isInList(wordList ,tag));
	}
	
	@Test
	public void isInList6() {
		String[] wordList = new String[6];
		EditTags edit = new EditTags(null);
		wordList[0] = "Hey";
		wordList[1] = "Hallo";
		wordList[2] = "weiter";
		wordList[3] = "gut";
		wordList[4] = "schlecht";
		wordList[5] = "test";
		String tag ="hey";
		assertEquals(true, edit.isInList(wordList ,tag));
	}
	
	@Test
	public void getInsertPosition0() {
		ReturnTagList retag = new ReturnTagList();
		String tag ="Hey";
		assertEquals(0, retag.getInsertPosition(tag));
	}

}
