package game.handlers;

import game.Action;

public abstract class BaseHandler implements CommandHandler{

    private final Action action;

    public BaseHandler(Action action) {
        this.action = action;
    }

    protected Action getAction() {
        return action;
    }
}
