package game.view;

import game.downloader.FileLoader;
import game.downloader.Paths;
import game.downloader.PicturesLoader;
import game.downloader.PropertiesUtil;
import game.storages.PictureStorage;
import game.validator.InvalidCommandException;

import java.util.List;

import static game.InputValidator.*;

public class Printer {

    private static final String PICTURES_PATH = PropertiesUtil.get(Paths.PICTURES_PATH_KEY.getPath());
    private final PictureStorage pictureStorage;

    public final String FOR_START_GAME_MESSAGE = "Для начала игры введите команду Старт." +
            "\nЕсли хотите выйти введите Выход\nДля перезапуска активной игры введите команду Рестарт";
    public final String START_GAME_MESSAGE = "Игра Началась!!!";
    public final String WIN_MESSAGE = "ВЫ ПОБЕДИЛИ!!!";
    public final String LOSE_MESSAGE = "Вы проиграли!";
    public final String ENCODED_WORD_MESSAGE = "Загаданное слово: ";
    public final String DUPLICATE_MESSAGE = "Данный символ уже был вами введён";
    public final String ENCODED_LETTERS_NUM = "Количество загаданных букв: ";
    public final String EXIST_LETTER = "Уже введённые вами буквы: ";
    public final String ATTEMPTS_COUNT = "Количество попыток: ";
    public final String ENTER_THE_LETTER = "Введите букву";
    public final String RIGHT_ATTEMPT = "Верно! Откройте букву: ";
    public final String WRONG_ATTEMPT = "Нет такой буквы!";
    public final String EXIT_GAME = "Игра завершена";

    public final String NOTHING_INPUT_MESSAGE_ON_GAME_OFF = "Вы ничего не ввели, пожалуйста введите команду";
    public final String NOTHING_INPUT_MESSAGE_ON_GAME_ON = "Вы ничего не ввели, пожалуйста введите русскую букву";
    public final String ENG_LETTER_ENTERED = "Вы ввели цифру или букву другого алфавита, пожалуйста введите русскую букву";
    public final String MORE_THAN_ONE_LETTER = "Вы ввели больше одного символа, пожалуйста введите русскую букву";
    public final String UNKNOWN_COMMAND = "Неизвестная команда, пожалуйста введите команду";
    public final String ONE_LETTER_ENTERED_ON_GAME_OFF = "Вы ввели букву, пожалуйста введите команду";
    public final String GAME_IS_WAITING = "Игра не запущена, нечего перезапускать";
    public final String GAME_IS_ON = "Игра в процессе";

    public Printer() {
        List<String> picturesLoad = FileLoader.loadFile(
                PICTURES_PATH,
                PicturesLoader::loadPictures);

       this.pictureStorage = new PictureStorage(picturesLoad);
       checkPictureIsNotEmpty(picturesLoad);
    }

    public void printMessage(String text) {
        System.out.println(text);
    }

    public void printStartMessage() {
        System.out.println(FOR_START_GAME_MESSAGE);
    }

    public void printDuplicateLetterMessage() {
        System.out.println(DUPLICATE_MESSAGE);
    }

    public void printHangman(int count) {
        System.out.println(getPicture(count));
    }

    public void printRoundMessage(String mask, int size, String letters, int attempts) {
        System.out.printf("%s%s\n", EXIST_LETTER, letters);
        System.out.printf("%s%d\n", ENCODED_LETTERS_NUM, size);
        System.out.printf("%s%s\n", ENCODED_WORD_MESSAGE, mask);
        System.out.printf("%s%d\n", ATTEMPTS_COUNT, attempts);
        System.out.println(ENTER_THE_LETTER + "\n");
    }

    public void printRightMessage(char letter) {
        System.out.printf("%s%s\n", RIGHT_ATTEMPT, letter);
    }

    public void printWin(String encodedWord) {
        System.out.println(WIN_MESSAGE);
        System.out.printf("%s %s\n", ENCODED_WORD_MESSAGE, encodedWord);
    }

    public void printWrongMessage(char letter) {
        String upperLetter = String.valueOf(letter).toUpperCase();
        System.out.printf("%s %s\n", WRONG_ATTEMPT, upperLetter);
    }

    public void printLose(String encodedWord) {
        System.out.println(LOSE_MESSAGE);
        System.out.printf("%s %s\n", ENCODED_WORD_MESSAGE, encodedWord);
    }

    public void printInvalidStart() {
        System.out.println(GAME_IS_ON);
    }

    public void printInvalidRestart() {
        System.out.println(GAME_IS_WAITING);
    }

    public void printInvalidPreStartLetter() {
        System.out.println(ONE_LETTER_ENTERED_ON_GAME_OFF);
    }

    public void printInvalid(String input, boolean state) {
        try {
            if (input.isEmpty()) {
                ifIsEmpty(state);
            } else if (isMoreThanOneLetter(input)) {
                ifMoreThanOne(state);
            } else if (isOneLetter(input)) {
                ifOne(state);
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ifOne(boolean state) throws InvalidCommandException {
        if (state) {
            throw new InvalidCommandException(ENG_LETTER_ENTERED);
        } else  {
            throw new InvalidCommandException(UNKNOWN_COMMAND);
        }
    }

    private void ifMoreThanOne( boolean state) throws InvalidCommandException {
        if (state) {
            throw new InvalidCommandException(MORE_THAN_ONE_LETTER);
        } else {
            throw new InvalidCommandException(UNKNOWN_COMMAND);
        }
    }

    private void ifIsEmpty(boolean state) throws InvalidCommandException {
        if (!state) {
            throw new InvalidCommandException(NOTHING_INPUT_MESSAGE_ON_GAME_OFF);
        } else {
            throw new InvalidCommandException(NOTHING_INPUT_MESSAGE_ON_GAME_ON);
        }
    }

    private String getPicture(int count) {
        return pictureStorage.get(count);
    }

    private void checkPictureIsNotEmpty(List<String> picturesLoad) {
        if (picturesLoad.isEmpty()) {
            printMessage(EXIT_GAME);
            System.exit(0);
        }
    }
}
