package game.handlers;

import game.Action;

import static game.InputValidator.isOneLetter;
import static game.InputValidator.isRussian;

public class GameHandler extends BaseHandler {

    public GameHandler(Action action) {
        super(action);
    }

    @Override
    public boolean canHandle(String input) {
        return isOneLetter(input) && isRussian(input);
    }

    @Override
    public void handle(String input) {

        getAction().makeMove(input);

    }


}
