package game.handlers;

import game.Action;

import static game.InputValidator.isExitCommand;

public class ExitHandler extends BaseHandler {

    public ExitHandler(Action action) {
        super(action);
    }

    @Override
    public boolean canHandle(String input) {
        return isExitCommand(input);
    }

    @Override
    public void handle(String input) {
        getAction().exit();
    }
}
