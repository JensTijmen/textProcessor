package textprocessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer{
    @Override
    public int calculateHighestFrequency(String text) {
        ArrayList<String> words = retrieveWordsFromText(text);
        int highestFrequency = 0;

        for(String word : words){
            int calculatedFrequency = calculateFrequencyForWord(text, word);
            if (calculatedFrequency > highestFrequency) highestFrequency = calculatedFrequency;
        }

        return highestFrequency;
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        return Collections.frequency(retrieveWordsFromText(text), word.toLowerCase());
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        return null;
    }

    private ArrayList<String> retrieveWordsFromText(String text) {
        ArrayList<String> retrievedWords = new ArrayList<>();
        String patternThatOnlyMatchesLetters = "([a-zA-Z]+)";
        Pattern pattern = Pattern.compile(patternThatOnlyMatchesLetters);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            retrievedWords.add(matcher.group().toLowerCase());
        }

        return retrievedWords;
    }
}
