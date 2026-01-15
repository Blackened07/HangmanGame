package game.handlers;

import game.Action;

public class GameHandler extends BaseHandler {

    public GameHandler(Action action) {
        super(action);
    }

    @Override
    public boolean canExecute(String input, boolean state) {
        return isOneLetter(input) && isRussian(input) && state;
    }

    @Override
    public void handle(String input) {

        getAction().makeMove(input);

    }


}
