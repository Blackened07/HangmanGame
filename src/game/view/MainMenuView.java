package game.view;

public class MainMenuView implements View<MenuStatus> {

    private final static String LAUNCHER_MESSAGE = """
            Добро пожаловать в игру "Виселица"
            
            После начала игры будет загадано слово которое вам нужно отгадать
            Для отгадывания вводите в консоль русские буквы
            Если в слове есть такая буква - вы угадали! Игра сообщит вам об этом
            Если не отгадали, появится рисунок виселицы
            Всего у вас будет 7 попыток!
            
            Для начала игры введите: Старт
            Для перезапуска игры введите: Рестарт
            Для выхода из игры введите: Выход
            
            Желаю вам приятной игры!
            """;


    @Override
    public void render(MenuStatus status) {
        System.out.printf("%s", LAUNCHER_MESSAGE);
    }
}
