package game;

public class Game {
    private Player player;
    private EncodedWordChecker encodedWordChecker;
    private EncodedWord encodedWord;
    private Printer renderer;
    private final EnterValidator enterValidator;
    private final PictureStorage pictures;
    private final RandomWordGenerator randomWordGenerator;
    private final int attemptsNumber;
    private boolean isGameOn;

    private String playerEnter;

    public Game(PictureStorage pictures, RandomWordGenerator randomWordGenerator) {
        this.pictures = pictures;
        this.randomWordGenerator = randomWordGenerator;
        this.attemptsNumber = pictures.getList().size();
        this.player = new Player();
        this.enterValidator = new EnterValidator();
    }

    public void startPreGame() {
        System.out.println(Printer.FOR_START_GAME_MESSAGE);
        while (!isGameOn) {
            if (enterValidator.isPlayerEnterValid(takePlayerEnter())) {
                if (!enterValidator.isCommandOrLetter(playerEnter)) {
                    setGameOrExit();
                    break;
                }
            }
        }
    }

    public void startGame() {

        setStartSettings();

        System.out.println(Printer.START_GAME_MESSAGE);
        System.out.println(Printer.ENCODED_LETTERS_NUM + encodedWord.getMaskSize());

        while (isGameOn) {
            System.out.println(Printer.EXIST_LETTER + player.getEnteredLetters());
            System.out.println(Printer.ATTEMPTS_COUNT + (attemptsNumber - player.getCount()));
            renderer.renderMask();
            System.out.println(Printer.ENTER_THE_LETTER);

            checkGameProcess();
        }
    }

    private void setStartSettings() {
        this.player = new Player();
        this.encodedWord = new EncodedWord(randomWordGenerator.getWord());
        this.encodedWordChecker = new EncodedWordChecker(encodedWord, player);
        this.renderer = new Printer(pictures, encodedWord);
    }

    private void checkGameProcess() {
        if (enterValidator.isPlayerEnterValid(takePlayerEnter())) {

            if (enterValidator.isCommandOrLetter(playerEnter)) {

                char letter = getPlayerEnterToChar(playerEnter);

                if (isPlayerEnterNotDuplicate(letter)) {

                    player.setEnteredLetter(letter);
                    if (comparePlayerEnterWithEncodedWordsLetters(letter)) {
                        replaceEncodedToLetter(letter);
                        System.out.println(Printer.RIGHT_ATTEMPT + player.getEnteredLetter());

                        if (encodedWordChecker.isWin()) {
                            System.out.printf("%s %s %s\n", Printer.WIN_MESSAGE, Printer.ENCODED_WORD_MESSAGE, encodedWord.get());
                            isGameOn = false;
                            startPreGame();
                        }
                    } else {

                        System.out.println(Printer.WRONG_ATTEMPT);
                        renderer.renderHangman(player.getCount());
                        player.setCount();

                        if (isCountToLose()) {
                            System.out.printf("%s %s %s\n", Printer.LOSE_MESSAGE, Printer.ENCODED_WORD_MESSAGE, encodedWord.get());
                            isGameOn = false;
                            startPreGame();
                        }
                    }
                } else {
                    System.out.println(Printer.REPEAT_MESSAGE);
                }
            } else {
                setGameOrExit();
            }
        }
    }

    private void setGameOrExit() {

        if (playerEnter.equals(enterValidator.EXIT_GAME)) {
            exitGame();
        } else if (playerEnter.equals(enterValidator.START_GAME) && !isGameOn) {
            isGameOn = true;
            startGame();
        } else if (playerEnter.equals(enterValidator.RESTART_GAME) && isGameOn) {
            startGame();
        } else if (playerEnter.equals(enterValidator.START_GAME) && isGameOn) {
            System.out.println(Printer.GAME_IS_ON);
        } else if (playerEnter.equals(enterValidator.RESTART_GAME) && !isGameOn) {
            System.out.println(Printer.GAME_IS_WAITING);
            startPreGame();
        } else {
            startPreGame();
        }
    }

    private char getPlayerEnterToChar(String playerEnter) {
        return playerEnter.charAt(0);
    }

    private void setPlayerEnter(String playerEnter) {
        this.playerEnter = playerEnter;
    }

    private boolean comparePlayerEnterWithEncodedWordsLetters(char playerEnter) {
        return encodedWordChecker.checkLettersWithPlayerEnter(playerEnter);
    }

    private boolean isPlayerEnterNotDuplicate(char playerEnter) {
        return encodedWordChecker.checkDuplicateEnteredLetters(playerEnter);
    }

    private void replaceEncodedToLetter(char playerEnter) {
        for (int letterIndex = 0; letterIndex < encodedWord.getMaskSize(); letterIndex++) {
            if (encodedWord.get().get(letterIndex) == playerEnter) {
                encodedWord.getMask().set(letterIndex, playerEnter);
            }
        }
    }

    private String takePlayerEnter() {
        String enter = player.getScanner().nextLine().toLowerCase();
        setPlayerEnter(enter);
        return enter;
    }

    private boolean isCountToLose() {
        return player.getCount() == attemptsNumber;
    }

    private void exitGame() {
        isGameOn = !isGameOn;
    }
}
