package game.handlers;

import game.Action;

import static game.InputValidator.*;

public class InvalidHandler extends BaseHandler{

    public InvalidHandler(Action action) {
        super(action);
    }

    @Override
    public boolean canHandle(String input) {
        return !isStartCommand(input) || !isExitCommand(input) || !isOneLetter(input) || !isRussian(input);
    }

    @Override
    public void handle(String input) {
        getAction().printInvalid(input);
    }

}
