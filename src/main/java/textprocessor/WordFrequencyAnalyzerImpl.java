package textprocessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        List<WordFrequency> wordFrequencyList = new ArrayList<>();
        ArrayList<String> words = retrieveWordsFromText(text);
        Set<String> wordsSet = new LinkedHashSet<>(words);

        for (String word : wordsSet) {
            WordFrequencyImpl wordFrequency = new WordFrequencyImpl(word, calculateFrequencyForWord(text, word));
            if(!wordFrequencyList.contains(wordFrequency) ){
                wordFrequencyList.add(wordFrequency);
            }
        }

        wordFrequencyList.sort((o1, o2) -> {
            Integer o1Frequency = o1.getFrequency();
            Integer o2Frequency = o2.getFrequency();

            return o2Frequency.compareTo(o1Frequency);
        });

        return wordFrequencyList.stream().limit(n).collect(Collectors.toList());
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
