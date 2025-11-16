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

    private final String START_GAME_MESSAGE = "Игра Началась!!!";
    private final String PRESTART_GAME_MESSAGE = "Игра не началась, введите команду";

    private final String WIN_MESSAGE = "ВЫ ПОБЕДИЛИ!!!";
    private final String LOSE_MESSAGE = "Вы проиграли!";
    private final String ENCODED_WORD_MESSAGE = "Загаданное слово: ";
    private final String REPEAT_MESSAGE = "Данный символ уже был вами введён";

    public Game(PictureStorage pictures, RandomWordGenerator randomWordGenerator) {
        this.pictures = pictures;
        this.randomWordGenerator = randomWordGenerator;
        this.attemptsNumber = pictures.getList().size();
        this.player = new Player();
        this.enterValidator = new EnterValidator();
    }

    public void startGameValidate() {
        System.out.println("Для начала игры введите команду Старт." +
                "\nЕсли хотите выйти введите Выход\nДля перезапуска активной игры введите команду Рестарт");
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
        this.player = new Player();
        this.encodedWord = new EncodedWord(randomWordGenerator.chooseTheRandomWord());
        this.encodedWordChecker = new EncodedWordChecker(encodedWord, player);
        this.renderer = new Printer(pictures, encodedWord);

        System.out.println(START_GAME_MESSAGE);
        System.out.println("Количество загаданных букв: " + encodedWord.getMaskSize());

        while (isGameOn) {
            System.out.println("Уже введённые вами буквы: " + player.getEnteredLetters());
            System.out.println("Осталось попыток: " + (attemptsNumber - player.getCount()));
            renderer.renderMask();
            System.out.println("Введите букву");
            if (enterValidator.isPlayerEnterValid(takePlayerEnter())) {

                if (enterValidator.isCommandOrLetter(playerEnter)) {

                    char letter = getPlayerEnterToChar(playerEnter);

                    if (isPlayerEnterNotDouble(letter)) {

                        player.setEnteredLetter(letter);
                        if (comparePlayerEnterWithEncodedWordsLetters(letter)) {
                            replaceEncodedToLetter(letter);
                            System.out.println("Верно! Откройте букву: " + player.getEnteredLetter());

                            if (encodedWordChecker.isWin()) {
                                System.out.println(WIN_MESSAGE + ENCODED_WORD_MESSAGE + encodedWord.get());
                                isGameOn = false;
                                startGameValidate();
                            }
                        } else {

                            System.out.println("Нет такой буквы!");
                            renderer.renderHangman(player.getCount());
                            player.setCount();

                            if (isCountToLose()) {
                                System.out.println(LOSE_MESSAGE + ENCODED_WORD_MESSAGE + encodedWord.get());
                                isGameOn = false;
                                startGameValidate();
                            }
                        }
                    } else {
                        System.out.println(REPEAT_MESSAGE);
                    }
                } else {
                    setGameOrExit();
                }
            }
        }
    }

    private void setGameOrExit() {

        if (playerEnter.equals(enterValidator.getEXIT_GAME())) {
            exitGame();
        } else if (playerEnter.equals(enterValidator.getSTART_GAME()) && !isGameOn) {
            isGameOn = true;
            startGame();
        } else if (playerEnter.equals(enterValidator.getRESTART_GAME()) && isGameOn) {
            startGame();
        } else if (playerEnter.equals(enterValidator.getSTART_GAME()) && isGameOn) {
            System.out.println("Игра в процессе");
        } else if (playerEnter.equals(enterValidator.getRESTART_GAME()) && !isGameOn) {
            System.out.println("Игра не запущена, нечего перезапускать");
            startGameValidate();
        } else {
            startGameValidate();
        }
    }

    private void exitGame() {
        isGameOn = !isGameOn;
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

    private boolean isPlayerEnterNotDouble(char playerEnter) {
        return encodedWordChecker.checkDoubleEnteredLetters(playerEnter);
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
}
