package game.model;


import java.util.LinkedHashSet;
import java.util.Set;

public class Game {

    public final int ATTEMPTS_NUMBER = 7;

    private final SecretWord secretWord;
    private final Set<Character> openedLetters;
    private GameStatus status;
    private int mistakeCount;

    public Game(String word) {
        this.secretWord = new SecretWord(word);
        this.openedLetters = new LinkedHashSet<>();
        this.status = GameStatus.IN_PROGRESS;
    }

    public SecretWord getSecretWord() {
        return secretWord;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Set<Character> getOpenedLetters() {
        return openedLetters;
    }

    public int getMistakeCount() {
        return mistakeCount - 1;
    }

    public int getAttemptsLeft() {
        return ATTEMPTS_NUMBER - mistakeCount;
    }

    public boolean open(char letter) {

        if (secretWord.isLetterExist(letter)) {
            secretWord.open(letter);
            return true;
        } else {
            mistakeCount++;
            return false;
        }

    }

    public boolean isDuplicate(char letter) {
        if (!openedLetters.contains(letter)) {
            openedLetters.add(letter);
            return false;
        }
        return true;
    }

    public boolean isWin() {

        if (secretWord.checkAllOpenedLetters()) {
            status = GameStatus.WIN;
            return true;
        }
        return false;
    }

    public boolean isLose() {
        if (getAttemptsLeft() == 0) {
            status = GameStatus.LOSE;
            return true;
        }
        return false;
    }
}
