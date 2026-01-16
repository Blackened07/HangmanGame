import game.Action;
import game.InputManager;
import game.handlers.*;
import game.storages.Dictionary;
import game.view.Printer;

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

        Printer printer = new Printer();

        Action action = new Action(Dictionary.getInstance(), printer);

        List<CommandHandler> handlers = List.of(
                new StartHandler(action),
                new RestartHandler(action),
                new GameHandler(action),
                new ExitHandler(action),
                new InvalidHandler(action)
        );

        InputManager manager = new InputManager(handlers);

        System.out.println(INTRODUCE);
        printer.printStartMessage();

        manager.mainInputHandler();
    }
}