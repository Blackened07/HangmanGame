package game.ui;

import game.model.GameFactory;
import game.view.MainMenuView;
import game.view.MenuStatus;
import game.view.View;

public class MainMenuUi extends ConsoleUI {
    private final View<MenuStatus> mainMenu;

    public MainMenuUi(GameFactory gameFactory) {
        super(gameFactory);
        this.mainMenu = new MainMenuView();
    }

    @Override
    public void process() {
        mainMenu.render(MenuStatus.LAUNCHER);

        while (true) {
            String command = getLine();
            while (!isCommand(command)) {
                command = getLine();
            }
            processCommand(command);
        }
    }

}
