package textprocessor;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordFrequencyAnalyzerImplTest {
    static final String TEST_TEXT = "test test test howhowhow   how   --are--you-I?   i'm just testing!";
    static final int HIGHEST_FREQUENCY = 3;
    WordFrequencyAnalyzerImpl myAnalyzer = new WordFrequencyAnalyzerImpl();

    @Test
    void calculateHighestFrequency() {
        String testText2 = "This is test 2 2 2 2 2. One Two tWo twO TWO three Three thRee THREE";

        assertEquals(HIGHEST_FREQUENCY, myAnalyzer.calculateHighestFrequency(TEST_TEXT));
        assertEquals(4, myAnalyzer.calculateHighestFrequency(testText2));
    }

    @Test
    void calculateFrequencyForWord() {
        assertEquals(0, myAnalyzer.calculateFrequencyForWord(TEST_TEXT, "-"));
        assertEquals(1, myAnalyzer.calculateFrequencyForWord(TEST_TEXT, "how"));
        assertEquals(2, myAnalyzer.calculateFrequencyForWord(TEST_TEXT, "i"));
        assertEquals(3, myAnalyzer.calculateFrequencyForWord(TEST_TEXT, "test"));
    }

    @Test
    void calculateMostFrequentNWords() {
        List<WordFrequency> topFiveWordFrequencyList = myAnalyzer.calculateMostFrequentNWords(TEST_TEXT, 5);

        assertEquals(5, topFiveWordFrequencyList.size());
        assertEquals("test", topFiveWordFrequencyList.get(0).getWord());
        assertEquals("i", topFiveWordFrequencyList.get(1).getWord());
        assertThrows(IndexOutOfBoundsException.class, () -> topFiveWordFrequencyList.get(5));
    }
}