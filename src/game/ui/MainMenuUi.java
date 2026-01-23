package game.ui;

import game.model.GameFactory;
import game.validator.InvalidCommandException;
import game.view.MainMenuView;
import game.view.MenuStatus;
import game.view.View;

public class MainMenuUi extends ConsoleUI {

    public MainMenuUi(GameFactory gameFactory) {
        super(gameFactory);
    }

    @Override
    public void process() {
        getMainMenuView().render(MenuStatus.LAUNCHER);
        getMainMenuView().render(MenuStatus.INFO);

            while (!isGameOn()) {
                String command = getLine();
                try {
                    processCommand(command);
                } catch (InvalidCommandException e) {
                    getException().render(INVALID_MESSAGE);
                }
            }

    }

}
