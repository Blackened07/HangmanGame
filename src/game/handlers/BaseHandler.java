package game.handlers;

import game.Action;

public abstract class BaseHandler implements CommandHandler{

    private static final String RUS_PATTERN = "[а-яёА-ЯЁ]";

    private final Action action;

    public BaseHandler(Action action) {
        this.action = action;
    }

    protected Action getAction() {
        return action;
    }

    protected boolean isRussian(String playerInput) {
        return !playerInput.replaceAll(RUS_PATTERN, "").equals(playerInput);
    }

    protected boolean isOneLetter(String playerInput) {
        return playerInput.length() == 1;
    }
}
