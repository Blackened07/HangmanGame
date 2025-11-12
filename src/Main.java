import Game.*;

public class Main {
    private final static String introduce = """
            Добро пожаловать в игру "Виселица"
            
            Для начала игры введите заглавную букву 'Н'
            Для перезапуска игры введите заглавную букву 'Р'
            Для выхода из игры введите заглавную букву 'В'
            
            Желаю вам приятной игры!
            """;

    public static void main(String[] args) {
        RandomWordGenerator rand = new RandomWordGenerator(new Words());
        GameProcess game = new GameProcess(new GamePics(), rand);
        System.out.println(introduce);
        game.preStartGame();
    }
}