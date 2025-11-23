import game.*;

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

    public static void main(String[] args) {

        Dictionary dictionary = new Dictionary(GameFileReader.getDictionary());
        PictureStorage pictureStorage = new PictureStorage(GameFileReader.getPictures());
        RandomWordGenerator randomWordGenerator = new RandomWordGenerator(dictionary);
        System.out.println(INTRODUCE);
        Game game = new Game(pictureStorage, randomWordGenerator);
        game.startPreGame();
    }
}