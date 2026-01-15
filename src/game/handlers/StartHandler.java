package game.handlers;

import game.Action;

import game.validator.GameCommands;

public class StartHandler extends BaseHandler {

    public StartHandler(Action action) {
        super(action);
    }

    @Override
    public boolean canExecute(String input, boolean state) {
        return (input.equals(GameCommands.START_GAME.getCommand()) && !state) ||
                (input.equals(GameCommands.RESTART_GAME.getCommand()) && state);
    }

    @Override
    public void handle(String input) {

        getAction().createGame();

    }
}
