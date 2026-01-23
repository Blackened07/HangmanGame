package game.ui;

import game.model.GameFactory;
import game.validator.InvalidCommandException;
import game.view.GameState;

public class MainMenuUi extends ConsoleUI {

    public MainMenuUi(GameFactory gameFactory) {
        super(gameFactory);
    }

    @Override
    public void process() {
        getMainMenuView().render(GameState.LAUNCHER);
        getMainMenuView().render(GameState.INFO);

            while (!isGameOn()) {
                String command = getLine();
                try {
                    processCommand(command);
                } catch (InvalidCommandException e) {
                    getExceptionView().render(INVALID_MESSAGE);
                }
            }

    }

}
