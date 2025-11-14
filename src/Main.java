import game.*;

public class Main {
    private final static String introduce = """
            Добро пожаловать в игру "Виселица"
            
            Для начала игры введите Старт
            Для перезапуска игры введите Рестарт
            Для выхода из игры введите Выход
            
            Желаю вам приятной игры!
            """;

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary(GameFileReader.getDictionary());
        PictureStorage pictureStorage = new PictureStorage(GameFileReader.getPictures());

        RandomWordGenerator randomWordGenerator = new RandomWordGenerator(dictionary);
        System.out.println(introduce);
        Game game = new Game(pictureStorage, randomWordGenerator);

    }
}