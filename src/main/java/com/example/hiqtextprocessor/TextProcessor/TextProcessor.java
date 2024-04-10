package com.example.hiqtextprocessor.TextProcessor;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class TextProcessor {
    public String processText(MultipartFile file){
        HashMap<String, Integer> words;
        List<String> differentFormatsOfTheWord;

        String textFromFile = getTextFromFile(file);
        String textWithoutPunctuation = removePunctuation(textFromFile); //Removing every punctuation from the word e.g. "HiQ," to "HiQ" for the correct counting of occurrence.
        String wholeTextInLowerCase = textWithoutPunctuation.toLowerCase(); //To calculate same word, no matter if there is capital letter or not, e.g. "The" = "the".
        words = countNumberOfOccurrences(wholeTextInLowerCase); //Counting number of occurrence for each word in the text.
        String mostFrequentWord = getMostFrequentWord(words);
        differentFormatsOfTheWord = addDifferentFormatsOfTheWord(mostFrequentWord); // e.g. "The", "the", "THE"
        return addPrefixAndSuffix(textFromFile, differentFormatsOfTheWord);
    }

    private String getTextFromFile(MultipartFile file) {
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String line;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        }catch (Exception exception){
            return "Something went wrong during the reading of the file! " + exception.getMessage();
        }
        return stringBuilder.toString();
    }

    private String removePunctuation(String textFromFile) {
        return textFromFile.replaceAll("[^a-zA-Z]", " ");
    }

    private HashMap<String, Integer> countNumberOfOccurrences(String wholeTextInLowerCase) {
        Scanner scanner = new Scanner(wholeTextInLowerCase);
        HashMap<String, Integer> words = new HashMap<>();

        while(scanner.hasNext()){
            String word = scanner.next();
            if(!existsWord(words, word)){ //If the word does not exist in the list.
                words.put(word, 1); //The word is added to the list with number of occurrence 1.
            } else {
                Integer currentValue = words.get(word);
                words.replace(word, currentValue + 1); //If the word already exists in the list, the current number of occurrence is increased with 1.
            }
        }
        return words;
    }

    private boolean existsWord(Map<String, Integer> words, String word) {
        return words.containsKey(word);
    }

    private String getMostFrequentWord(Map<String, Integer> words) {
        String mostFrequentWord = null;
        int maxValue = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();

            if (value > maxValue) {
                maxValue = value;
                mostFrequentWord = key;
            }
        }
        return mostFrequentWord;
    }

    private List<String> addDifferentFormatsOfTheWord(String mostFrequentWord) {
        List<String> differentFormatsOfTheWord = new ArrayList<>();

        String mostFrequentWordUppercase = mostFrequentWord.toUpperCase(); // The word can contain all uppercase's e.g. "the" and "THE".
        String mostFrequentWordCapitalisedFirstLetter = capitaliseFirstLetter(mostFrequentWord); // The word can be in the beginning of the sentence e.g. "The .....".

        differentFormatsOfTheWord.add(mostFrequentWord);
        differentFormatsOfTheWord.add(mostFrequentWordUppercase);
        differentFormatsOfTheWord.add(mostFrequentWordCapitalisedFirstLetter);

        return differentFormatsOfTheWord;
    }

    private String capitaliseFirstLetter(String mostFrequentWord) {
        return Character.toUpperCase(mostFrequentWord.charAt(0)) + mostFrequentWord.substring(1);
    }

    private String addPrefixAndSuffix(String textFromFile, List<String> differentFormatsOfTheWord) {
        String foo = "foo";
        String bar = "bar";

        String regex = "(?<!\\S)" + Pattern.quote(differentFormatsOfTheWord.get(0)) + "(?!\\S)"; // To add prefix and suffix only to words that are not part of the other words.
        textFromFile = textFromFile.replaceAll(regex, foo + differentFormatsOfTheWord.get(0) + bar);

        regex = "(?<!\\S)" + Pattern.quote(differentFormatsOfTheWord.get(1)) + "(?!\\S)"; // To add prefix and suffix only to words that are not part of the other words.
        textFromFile = textFromFile.replaceAll(regex, foo + differentFormatsOfTheWord.get(1) + bar);

        regex = "(?<!\\S)" + Pattern.quote(differentFormatsOfTheWord.get(2)) + "(?!\\S)"; // To add prefix and suffix only to words that are not part of the other words.
        textFromFile = textFromFile.replaceAll(regex, foo + differentFormatsOfTheWord.get(2) + bar);

        //Following lines are for adding the prefix and suffix in case where some punctuation comes after the word, e.g. "Word, -> fooWordbar,".
        regex = "(?<!\\S)" + Pattern.quote(differentFormatsOfTheWord.get(0)) + "(?=[\\p{Punct}])";
        textFromFile = textFromFile.replaceAll(regex, foo + differentFormatsOfTheWord.get(0) + bar);

        regex = "(?<!\\S)" + Pattern.quote(differentFormatsOfTheWord.get(1)) + "(?=[\\p{Punct}])";
        textFromFile = textFromFile.replaceAll(regex, foo + differentFormatsOfTheWord.get(1) + bar);

        regex = "(?<!\\S)" + Pattern.quote(differentFormatsOfTheWord.get(2)) + "(?=[\\p{Punct}])";
        textFromFile = textFromFile.replaceAll(regex, foo + differentFormatsOfTheWord.get(2) + bar);

        return textFromFile;
    }
}