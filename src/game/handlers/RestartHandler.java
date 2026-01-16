package game.handlers;

import game.Action;
import game.InputValidator;

public class RestartHandler extends BaseHandler{
    public RestartHandler(Action action) {
        super(action);
    }

    @Override
    public boolean canHandle(String input) {
        return InputValidator.isRestartCommand(input);
    }

    @Override
    public void handle(String input) {
        getAction().restartGame();
    }
}
