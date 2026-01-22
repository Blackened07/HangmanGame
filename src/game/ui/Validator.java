package game.ui;

import game.validator.GameCommands;
import game.validator.InvalidCommandException;
import game.view.ExceptionView;
import game.view.View;
//STATIC
public class Validator {
    private final View<String> exception;
    private final String invalidMessage = "INVALID MESSAGE";

    public Validator() {
        this.exception = new ExceptionView();
    }

    public boolean isValid(String line) {
        try {
            if (isCommand(line)) {
                return true;
            } else if (isOneValidLetter(line)) {
                return true;
            } else {
                throw new InvalidCommandException(invalidMessage);
            }
        } catch (InvalidCommandException e) {
            exception.render(e.getMessage());
        }
        return false;
    }

    public boolean isCommand(String line) {
        return isLineEqualsExit(line) || isLineEqualsStart(line);
    }

    public boolean isLineEqualsStart(String line) {
        return line.equals(GameCommands.START_GAME.getCommand());
    }

    public boolean isLineEqualsExit(String line) {
        return line.equals(GameCommands.EXIT_GAME.getCommand());
    }

    public boolean isOneValidLetter(String line) {
        String rus_pattern = "[а-яёА-ЯЁ]";
        return line.length() == 1 && !line.replaceAll(rus_pattern, "").equals(line);
    }
}
