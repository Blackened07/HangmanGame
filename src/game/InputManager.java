package game;

import game.handlers.CommandHandler;

import java.util.List;
import java.util.Scanner;

public class InputManager {

    private final Scanner scanner;
    private boolean isGameOn;

    private final List<CommandHandler> handlers;

    public InputManager(List<CommandHandler> handlers) {
        this.handlers = handlers;
        this.scanner = new Scanner(System.in);
    }

    public void mainInputHandler() {
        while (true) {
            String playerInput = takePlayerInput();

            for (CommandHandler handler : handlers) {
                if (handler.canExecute(playerInput, isGameOn)) {
                    handler.handle(playerInput);
                }
            }
        }
    }

    public boolean isGameOn() {
        return isGameOn;
    }

    public void setGameOn(boolean gameOn) {
        isGameOn = gameOn;
    }

    private String takePlayerInput() {
        return scanner.nextLine().toLowerCase();
    }

}
