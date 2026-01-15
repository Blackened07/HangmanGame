package game.handlers;

import game.Action;
import game.validator.GameCommands;

public class ExitHandler extends BaseHandler {

    public ExitHandler(Action action) {
        super(action);
    }

    @Override
    public boolean canExecute(String input, boolean state) {
        return (input.equals(GameCommands.EXIT_GAME.getCommand()) && !state) ||
                (input.equals(GameCommands.EXIT_GAME.getCommand()) && state);
    }

    @Override
    public void handle(String input) {
        getAction().exit();
    }
}
