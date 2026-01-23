package game.view;

public class MainMenuView implements View<MenuStatus> {

    private final String LAUNCHER_MESSAGE = """
            Добро пожаловать в игру "Виселица"
            
            После начала игры будет загадано слово которое вам нужно отгадать
            Для отгадывания вводите в консоль русские буквы
            Если в слове есть такая буква - вы угадали! Игра сообщит вам об этом
            Если не отгадали, появится рисунок виселицы
            Всего у вас будет 7 попыток!
           
            Желаю вам приятной игры!
            """;

    private final String COMMAND_INFORMATION_MESSAGE = """
            Для начала игры введите: Старт
            Для перезапуска игры введите: Рестарт
            Для выхода из игры введите: Выход
            """;


    @Override
    public void render(MenuStatus status) {
        switch (status) {
            case LAUNCHER -> System.out.println(LAUNCHER_MESSAGE);
            case INFO -> System.out.println(COMMAND_INFORMATION_MESSAGE);
        }


    }
}
