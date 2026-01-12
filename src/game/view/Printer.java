package game.view;

import game.downloader.FileLoader;
import game.downloader.Paths;
import game.downloader.PicturesLoader;
import game.downloader.PropertiesUtil;
import game.storages.PictureStorage;

import java.util.List;

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
    public final String GAME_IS_ON = "Игра в процессе";
    public final String GAME_IS_WAITING = "Игра не запущена, нечего перезапускать";
    public final String EXIT_GAME = "Игра завершена";

    public Printer() {
        List<String> picturesLoad = FileLoader.loadFile(
                PICTURES_PATH,
                PicturesLoader::loadPictures);

       this.pictureStorage = new PictureStorage(picturesLoad);
       checkPictureIsNotEmpty(picturesLoad);
    }

    public void renderMessage(String text) {
        System.out.println(text);
    }

    public void renderHangman(int count) {
        System.out.println(getPicture(count));
    }

    private String getPicture(int count) {
        return pictureStorage.get(count);
    }

    private void checkPictureIsNotEmpty(List<String> picturesLoad) {
        if (picturesLoad.isEmpty()) {
            renderMessage(EXIT_GAME);
            System.exit(0);
        }
    }

}
