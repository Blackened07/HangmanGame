package game.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int ATTEMPTS_NUMBER = 7;
    private static final char EMPTY_SLOT = ' ';

    private final EncodedWord encodedWord;

    private final List<Character> enteredLetters;
    private char enteredLetter;

    private int count;

    public Game(String encodedWord) {
        this.encodedWord = new EncodedWord(encodedWord);
        this.enteredLetters = new ArrayList<>();
    }

    public String getEncodedWordMask() {
        return encodedWord.getMask().toString();
    }

    public String getEncodedWord() {
        return encodedWord.get().toString();
    }

    public int getEncodedWordSize() {
        return encodedWord.getMaskSize();
    }

    public char getEnteredLetter() {
        return enteredLetter;
    }

    public int getCount() {
        return count;
    }

    public int getAttempt() {
        return ATTEMPTS_NUMBER - count;
    }

    public void setCount() {
        this.count += 1;
    }

    public String getEnteredLetters() {
        return enteredLetters.toString();
    }

    public void setEnteredLetter(char enteredLetter) {
        this.enteredLetter = enteredLetter;
        addLetterToList();
    }

    public boolean isPlayerEnterEqualsEncodedWordLetters(char playerEnter) {
        return encodedWord.get().contains(playerEnter);
    }

    public boolean isPlayerEnterNotDuplicate(char playerEnter) {
        return !enteredLetters.contains(playerEnter);
    }

    public void replaceEncodedToLetter(char playerEnter) {
        for (int letterIndex = 0; letterIndex < encodedWord.getMaskSize(); letterIndex++) {
            if (encodedWord.get().get(letterIndex) == playerEnter) {
                encodedWord.getMask().set(letterIndex, playerEnter);
            }
        }
    }

    public boolean isWin() {
        char code = EMPTY_SLOT;
        for (char letter : encodedWord.getMask()) {
            if (letter == encodedWord.getCODE()) {
                code = letter;
            }
        }
        return code == EMPTY_SLOT;
    }

    public boolean isCountToLose() {
        return count == ATTEMPTS_NUMBER;
    }

    private void addLetterToList() {
        if (!enteredLetters.contains(enteredLetter))
            enteredLetters.add(enteredLetter);
    }

}
