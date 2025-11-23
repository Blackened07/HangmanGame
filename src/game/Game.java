package game;

import game.storages.Dictionary;
import game.storages.PictureStorage;

public class Game {
    private Player player;
    private EncodedWordChecker encodedWordChecker;
    private EncodedWord encodedWord;
    private Printer printer;
    private final EnterValidator enterValidator;
    private final PictureStorage pictures;
    private final Dictionary dictionary;
    private final int attemptsNumber;
    private boolean isGameOn;

    private final String FOR_START_GAME_MESSAGE = "Для начала игры введите команду Старт." +
            "\nЕсли хотите выйти введите Выход\nДля перезапуска активной игры введите команду Рестарт";

    private String playerEnter;

    public Game(PictureStorage pictures, Dictionary dictionary) {
        this.pictures = pictures;
        this.dictionary = dictionary;
        this.attemptsNumber = pictures.getListSize();
        this.player = new Player();
        this.enterValidator = new EnterValidator();
    }

    public void startPreGame() {
        System.out.println(FOR_START_GAME_MESSAGE);
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

        System.out.println(printer.START_GAME_MESSAGE);
        System.out.println(printer.ENCODED_LETTERS_NUM + encodedWord.getMaskSize());

        while (isGameOn) {
            System.out.println(printer.EXIST_LETTER + player.getEnteredLetters());
            System.out.println(printer.ATTEMPTS_COUNT + (attemptsNumber - player.getCount()));
            printer.renderMask();
            System.out.println(printer.ENTER_THE_LETTER);

            checkGameProcess();
        }
    }

    private void setStartSettings() {
        this.player = new Player();
        this.encodedWord = new EncodedWord(dictionary.getRandomWord());
        this.encodedWordChecker = new EncodedWordChecker(encodedWord, player);
        this.printer = new Printer(pictures, encodedWord);
    }

    private void checkGameProcess() {
        if (enterValidator.isPlayerEnterValid(takePlayerEnter())) {

            if (enterValidator.isCommandOrLetter(playerEnter)) {

                char letter = playerEnter.charAt(0);

                if (isPlayerEnterNotDuplicate(letter)) {

                    player.setEnteredLetter(letter);
                    if (comparePlayerEnterWithEncodedWordsLetters(letter)) {
                        replaceEncodedToLetter(letter);
                        System.out.println(printer.RIGHT_ATTEMPT + player.getEnteredLetter());

                        if (encodedWordChecker.isWin()) {
                            System.out.printf("%s %s %s\n", printer.WIN_MESSAGE, printer.ENCODED_WORD_MESSAGE, encodedWord.get());
                            isGameOn = false;
                            startPreGame();
                        }
                    } else {

                        System.out.println(printer.WRONG_ATTEMPT);
                        printer.renderHangman(player.getCount());
                        player.setCount();

                        if (isCountToLose()) {
                            System.out.printf("%s %s %s\n", printer.LOSE_MESSAGE, printer.ENCODED_WORD_MESSAGE, encodedWord.get());
                            isGameOn = false;
                            startPreGame();
                        }
                    }
                } else {
                    System.out.println(printer.REPEAT_MESSAGE);
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
            System.out.println(printer.GAME_IS_ON);
        } else if (playerEnter.equals(enterValidator.RESTART_GAME) && !isGameOn) {
            System.out.println(printer.GAME_IS_WAITING);
            startPreGame();
        } else {
            startPreGame();
        }
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
