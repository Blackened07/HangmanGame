package game.ui;

import game.model.GameFactory;
import game.validator.GameCommands;
import game.validator.InvalidCommandException;
import game.view.ExceptionView;
import game.view.View;

import java.util.Scanner;

public abstract class ConsoleUI {

    private final Scanner scanner;
    private final GameFactory gameFactory;
    private final View<String> exception;
    public static final String INVALID_MESSAGE = "INVALID MESSAGE";

    public ConsoleUI(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
        this.exception = new ExceptionView();
        this.scanner = new Scanner(System.in);
    }

    public abstract void process() throws InvalidCommandException;

    protected String getLine() {
        return scanner.nextLine().toLowerCase();
    }

    protected boolean isCommand(String line) {
        return isLineEqualsExit(line) || isLineEqualsStart(line);
    }

    protected boolean isOneValidLetter(String line) {
        String rus_pattern = "[а-яёА-ЯЁ]";
        return line.length() == 1 && !line.replaceAll(rus_pattern, "").equals(line);
    }

    protected void processCommand(String command) {
        try {
            if (isLineEqualsStart(command)) {
                new GameUI(gameFactory).process();
            } else if (isLineEqualsExit(command)) {
                System.exit(1);
            } else {
                throw new InvalidCommandException(INVALID_MESSAGE);
            }
        } catch (InvalidCommandException e) {
            exception.render(e.getMessage());
        }
    }

    private boolean isLineEqualsStart(String line) {
        return line.equals(GameCommands.START_GAME.getCommand());
    }

    private boolean isLineEqualsExit(String line) {
        return line.equals(GameCommands.EXIT_GAME.getCommand());
    }

}
