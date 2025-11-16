package game;

public class EncodedWordChecker {
    private final EncodedWord encodedWord;
    private final Player player;
    private static final char EMPTY_SLOT = ' ';

    public EncodedWordChecker(EncodedWord encodedWord, Player player) {
        this.encodedWord = encodedWord;
        this.player = player;
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

    ;

    public boolean checkLettersWithPlayerEnter(char playerEnter) {
        for (char letter : encodedWord.get()) {
            if (letter == playerEnter) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDoubleEnteredLetters(char playerEnter) {
        if(player.getEnteredLetters().isEmpty()) {
            return true;
        } else return isSameLetter(playerEnter);
    }

    private boolean isSameLetter (char playerEnter) {
        for (char letter : player.getEnteredLetters()) {
            if (playerEnter == letter) {
                return false;
            }
        }
        return true;
    }
}
