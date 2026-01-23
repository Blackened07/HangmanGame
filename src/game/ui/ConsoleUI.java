package game.ui;

import game.model.GameFactory;
import game.validator.InvalidCommandException;
import game.view.ExceptionView;
import game.view.MainMenuView;
import game.view.GameState;
import game.view.View;

import java.util.Scanner;

public abstract class ConsoleUI {

    private final Scanner scanner;
    private final GameFactory gameFactory;
    private boolean isGameOn;
    private final View<String> exceptionView;
    private final View<GameState> mainMenuView;
    private final String START = "старт";
    private final String EXIT = "выход";
    protected static final String RUS_PATTERN = "[а-яёА-ЯЁ]";
    protected static final String INVALID_MESSAGE = "INVALID MESSAGE";

    public ConsoleUI(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
        this.scanner = new Scanner(System.in);
        this.exceptionView = new ExceptionView();
        this.mainMenuView = new MainMenuView();
    }

    public abstract void process();

    protected boolean isGameOn() {
        return isGameOn;
    }

    protected void setGameOn(boolean gameOn) {
        isGameOn = gameOn;
    }

    protected String getLine() {
        return scanner.nextLine().toLowerCase();
    }

    protected GameFactory getGameFactory() {
        return gameFactory;
    }

    protected View<String> getException() {
        return exceptionView;
    }

    public View<GameState> getMainMenuView() {
        return mainMenuView;
    }

    protected void processCommand(String command) throws InvalidCommandException {
        switch (command) {
            case START -> {
                new GameUI(getGameFactory()).process();
            }
            case EXIT -> {
                if (isGameOn()) {
                    setGameOn(false);
                    mainMenuView.render(GameState.INFO);
                } else {
                    System.exit(0);
                }
            }
            default -> {
                if (isValidLetter(command) && !isGameOn) {
                    throw new InvalidCommandException(INVALID_MESSAGE);
                }
                if (!isValidLetter(command)) {
                    throw new InvalidCommandException(INVALID_MESSAGE);
                }
            }
        }

    }

    private boolean isValidLetter(String line) {
        return line.length() == 1 && !line.replaceAll(RUS_PATTERN, "").equals(line);
    }

}
