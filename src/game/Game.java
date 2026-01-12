package game;

import game.view.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int ATTEMPTS_NUMBER = 7;
    private static final char EMPTY_SLOT = ' ';

    private final UserInterface UI;
    private final EncodedWord encodedWord;

    private final List<Character> enteredLetters;
    private char enteredLetter;
    private int count;

    public Game(UserInterface ui) {
        this.UI = ui;
        this.encodedWord = new EncodedWord(UI.getRandomWord());
        this.enteredLetters = new ArrayList<>();
    }

    public List<Character> getEncodedWordMask() {
        return encodedWord.getMask();
    }

    public int getMaskSize() {
        return encodedWord.getMaskSize();
    }

    public void startGame() {
        while (UI.getGameOnState()) {
            UI.callPrinterToViewEveryTurnGameMessage();
            checkGameProcess();
        }
    }

    private void checkGameProcess() {

        if (UI.getPlayerEnterValid()) {

            if (UI.getIsCommand()) {

                char letter = UI.getPlayerEnter();

                if (isPlayerEnterNotDuplicate(letter)) {
                    setEnteredLetter(letter);
                    checkWhenPlayerEnterNotDuplicate(letter);
                } else {
                    UI.callPrinterToViewStateGameMessage(GameState.REPEAT, "");
                }
            } else {
                UI.setGameOrExit();
            }
        }
    }

    public int getCount() {
        return count;
    }

    public List<Character> getEnteredLetters() {
        return enteredLetters;
    }

    private void checkWhenPlayerEnterNotDuplicate(char letter) {
        if (isPlayerEnterEqualsEncodedWordLetters(letter)) {

            replaceEncodedToLetter(letter);

            UI.callPrinterToViewStateGameMessage(GameState.RIGHT, String.valueOf(getEnteredLetter()));

            if (isWin()) {
                UI.callPrinterToViewStateGameMessage(GameState.WIN, encodedWord.get().toString());
                UI.setGameOn(false);
                UI.startPreGame();
            }

        } else {

            UI.callPrinterToViewStateGameMessage(GameState.WRONG, "");
            UI.printHangman(getCount());
            setCount();

            if (isCountToLose()) {
                UI.callPrinterToViewStateGameMessage(GameState.LOSE, encodedWord.get().toString());
                UI.setGameOn(false);
                UI.startPreGame();
            }
        }
    }

    private void setCount() {
        this.count += 1;
    }

    private char getEnteredLetter() {
        return enteredLetter;
    }

    private void setEnteredLetter(char enteredLetter) {
        this.enteredLetter = enteredLetter;
        addLetterToList();
    }

    private void addLetterToList() {
        if (!enteredLetters.contains(enteredLetter))
            enteredLetters.add(enteredLetter);
    }

    private boolean isPlayerEnterEqualsEncodedWordLetters(char playerEnter) {
        return encodedWord.get().contains(playerEnter);
    }

    private boolean isPlayerEnterNotDuplicate(char playerEnter) {
        return !getEnteredLetters().contains(playerEnter);
    }

    private void replaceEncodedToLetter(char playerEnter) {
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

    private boolean isCountToLose() {
        return getCount() == ATTEMPTS_NUMBER;
    }

}
