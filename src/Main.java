import game.*;
import game.downloader.*;
import game.storages.Dictionary;
import game.storages.PictureStorage;
import game.view.UserInterface;

import java.util.List;

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

        System.out.println(INTRODUCE);

        UserInterface us = new UserInterface();
        us.startPreGame();

    }
}