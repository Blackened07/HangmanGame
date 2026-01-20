package game.ui;

import game.view.MainMenuView;
import game.view.MenuStatus;
import game.view.View;

public class MainMenuUi extends ConsoleUI {
    private final View<MenuStatus> mainMenu;
    private MenuStatus menuStatus;

    public MainMenuUi() {
        super();
        this.mainMenu = new MainMenuView();
        this.menuStatus = MenuStatus.LAUNCHER;
    }

    @Override
    public void process() {
        mainMenu.render(menuStatus);
        while (true) {
            String line = getLine();
            processCommand(line);
            menuStatus = MenuStatus.SELECTOR;
            mainMenu.render(menuStatus);
        }
    }


}
