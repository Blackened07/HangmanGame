package game;

import game.storages.PictureStorage;

public class Printer {
    private final PictureStorage pictureStorage;
    private final EncodedWord encodedWord;

    public final String START_GAME_MESSAGE = "Игра Началась!!!";
    public final String WIN_MESSAGE = "ВЫ ПОБЕДИЛИ!!!";
    public final String LOSE_MESSAGE = "Вы проиграли!";
    public final String ENCODED_WORD_MESSAGE = "Загаданное слово: ";
    public final String REPEAT_MESSAGE = "Данный символ уже был вами введён";
    public final String ENCODED_LETTERS_NUM = "Количество загаданных букв: ";
    public final String EXIST_LETTER = "Уже введённые вами буквы: ";
    public final String ATTEMPTS_COUNT = "Количество попыток: ";
    public final String ENTER_THE_LETTER = "Введите букву";
    public final String RIGHT_ATTEMPT = "Верно! Откройте букву: ";
    public final String WRONG_ATTEMPT = "Нет такой буквы!";
    public final String GAME_IS_ON = "Игра в процессе";
    public final String GAME_IS_WAITING = "Игра не запущена, нечего перезапускать";

    public Printer(PictureStorage pictureStorage, EncodedWord encodedWord) {
        this.pictureStorage = pictureStorage;
        this.encodedWord = encodedWord;
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void renderHangman(int count) {
        System.out.println(getPicture(count));
    }

    public void renderMask() {
        System.out.println(getMask());
    }

    private String getMask() {
        return encodedWord.getMask().toString();
    }

    private String getPicture(int count) {
        return pictureStorage.get(count);
    }

}
