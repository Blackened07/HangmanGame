package game;

public class Printer {
    private final PictureStorage pictureStorage;
    private final EncodedWord encodedWord;

    static final String START_GAME_MESSAGE = "Игра Началась!!!";
    static final String WIN_MESSAGE = "ВЫ ПОБЕДИЛИ!!!";
    static final String LOSE_MESSAGE = "Вы проиграли!";
    static final String ENCODED_WORD_MESSAGE = "Загаданное слово: ";
    static final String REPEAT_MESSAGE = "Данный символ уже был вами введён";
    static final String FOR_START_GAME_MESSAGE = "Для начала игры введите команду Старт." +
            "\nЕсли хотите выйти введите Выход\nДля перезапуска активной игры введите команду Рестарт";
    static final String ENCODED_LETTERS_NUM = "Количество загаданных букв: ";
    static final String EXIST_LETTER = "Уже введённые вами буквы: ";
    static final String ATTEMPTS_COUNT = "Количество попыток: ";
    static final String ENTER_THE_LETTER = "Введите букву";
    static final String RIGHT_ATTEMPT = "Верно! Откройте букву: ";
    static final String WRONG_ATTEMPT = "Нет такой буквы!";
    static final String GAME_IS_ON = "Игра в процессе";
    static final String GAME_IS_WAITING = "Игра не запущена, нечего перезапускать";

    public Printer(PictureStorage pictureStorage, EncodedWord encodedWord) {
        this.pictureStorage = pictureStorage;
        this.encodedWord = encodedWord;
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
