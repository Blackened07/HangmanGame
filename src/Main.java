import game.*;
import game.storages.Dictionary;
import game.storages.PictureStorage;

import java.io.IOException;

public class Main {

    private final static String INTRODUCE = """
            Добро пожаловать в игру "Виселица"
            
            После начала игры будет загадано слово которое вам нужно отгадать
            Для отгадывания вводите в консоль русские буквы
            Если в слове есть такая буква - вы угадали! Игра сообщит вам об этом
            Если не отгадали, появится рисунок виселицы
            Всего у вас будет 7 попыток!
            
            Желаю вам приятной игры!
            """;

    public static void main(String[] args) throws IOException {
        GameFileInspector gameFileReader = new GameFileInspector();
        Dictionary dictionary = new Dictionary(gameFileReader);
        PictureStorage pictureStorage = new PictureStorage(gameFileReader);
        System.out.println(INTRODUCE);
        Game game = new Game(pictureStorage, dictionary);
        game.startPreGame();
    }
}