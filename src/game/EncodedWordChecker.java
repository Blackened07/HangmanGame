package game;

public class EncodedWordChecker {
    private final EncodedWord encodedWord;
    private final Player player;
    private static final String REPEAT_MESSAGE = "Данный символ уже был вами введён";
    private static final char EMPTY_SLOT = ' ';

    public EncodedWordChecker(EncodedWord encodedWord, Player player) {
        this.encodedWord = encodedWord;
        this.player = player;
    }
    //replace to Game class

    //game invoke
    public boolean checkIfEncodedLetterExist() {
        char code = EMPTY_SLOT;
        for (char letter : encodedWord.getMask()) {
            if (letter == encodedWord.getCODE()) {
                code = letter;
            }
        }
        return code == EMPTY_SLOT;
    };

    public boolean checkEncodedWord(char playerEnter) {
        for (char letter : encodedWord.get()) {
            if (letter == playerEnter) {
                player.getEnteredLetters().add(playerEnter);
                return true;
            }
        }
        return false;
    }

    boolean checkEnteredLetters(char playerEnter) {
        boolean check = false;
        try {
            for (char letter : player.getEnteredLetters()) {
                if (playerEnter != letter) {
                    return check;
                } else {
                    check = true;
                    throw new RuntimeException(REPEAT_MESSAGE.toUpperCase());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
}
