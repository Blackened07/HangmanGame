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

    private final String invalidMessage = "INVALID MESSAGE";

    public ConsoleUI() {
        this.gameFactory = new GameFactory();
        this.exception = new ExceptionView();
        this.scanner = new Scanner(System.in);
    }

    public abstract void process() throws InvalidCommandException;

    protected String getLine() {
        return scanner.nextLine().toLowerCase();
    }

    protected String processLine() {
        String line = getLine();
        while (!isOneValidLetter(line)) {
            checkLine(line);
            line = getLine();
        }
        return line;
    }

    protected void processCommand(String command) {
        try {
            if (isLineEqualsStart(command)) {
                new GameUI(gameFactory.getSession()).process();
            } else if (isLineEqualsExit(command)) {
                System.exit(1);
            } else {
                throw new InvalidCommandException(invalidMessage);
            }
        } catch (InvalidCommandException e) {
            exception.render(e.getMessage());
        }
    }

    protected boolean isLineEqualsStart(String line) {
        return line.equals(GameCommands.START_GAME.getCommand());
    }

    protected boolean isLineEqualsExit(String line) {
        return line.equals(GameCommands.EXIT_GAME.getCommand());
    }

    private boolean isOneValidLetter(String line) {
        String rus_pattern = "[а-яёА-ЯЁ]";
        return line.length() == 1 && !line.replaceAll(rus_pattern, "").equals(line);
    }

    private void checkLine(String line) {
        try {
            if (isLineEqualsExit(line) || isLineEqualsStart(line)) {
                processCommand(line);
            } else {
                throw new InvalidCommandException(invalidMessage);
            }
        } catch (InvalidCommandException e) {
            exception.render(e.getMessage());
        }
    }
}
