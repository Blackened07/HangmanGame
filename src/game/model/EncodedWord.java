package game.model;

import java.util.ArrayList;
import java.util.List;

public class EncodedWord {
    private final List<Character> encodedWordsLetters;
    private final List<Character> encodedWordsMask;
    private final char CODE = '*';

    public EncodedWord(String randomWord) {
        this.encodedWordsLetters = new ArrayList<>();
        this.encodedWordsMask = new ArrayList<>();
        setEncodedWordsLetters(randomWord);
        setEncodedWordsMask(randomWord);
    }

    public List<Character> get() {
        return encodedWordsLetters;
    }

    public char getCODE() {
        return CODE;
    }

    public List<Character> getMask() {
        return encodedWordsMask;
    }

    public int getMaskSize() {
        return getMask().size();
    }

    private void setEncodedWordsLetters(String encodedWord) {
        char[] encodedWordsLettersArray = encodedWord.toCharArray();

        for (char letter : encodedWordsLettersArray ) {
            encodedWordsLetters.add(letter);
        }
    }

    private void setEncodedWordsMask(String encodedWord) {
        int encodedWordLength = encodedWord.length();

        for (int i = 0; i < encodedWordLength; i++) {
            encodedWordsMask.add(i, CODE);
        }
    }

}
