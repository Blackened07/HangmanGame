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

    public boolean checkLettersWithPlayerEnter(char playerEnter) {
        return encodedWord.get().contains(playerEnter);
    }

    public boolean checkDuplicateEnteredLetters(char playerEnter) {
        return !player.getEnteredLetters().contains(playerEnter);
    }

}
