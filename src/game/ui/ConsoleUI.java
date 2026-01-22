package game.ui;

import game.model.GameFactory;
import game.validator.GameCommands;
import game.validator.InvalidCommandException;
import game.view.ExceptionView;
import game.view.View;

import java.util.Scanner;

public abstract class ConsoleUI {

    private final View<String> exception;
    private final Scanner scanner;
    private final GameFactory gameFactory;
    private final Validator validator;

    private final String invalidMessage = "INVALID MESSAGE";

    public ConsoleUI(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
        this.exception = new ExceptionView();
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
    }

    public abstract void process() throws InvalidCommandException;

    protected String getLine() {
        return scanner.nextLine().toLowerCase();
    }

    protected Validator getValidator() {
        return validator;
    }

    protected void processCommand(String command) {
        try {
            if (validator.isLineEqualsStart(command)) {
                new GameUI(gameFactory).process();
            } else if (validator.isLineEqualsExit(command)) {
                System.exit(1);
            } else {
                throw new InvalidCommandException(invalidMessage);
            }
        } catch (InvalidCommandException e) {
            exception.render(e.getMessage());
        }
    }
}
