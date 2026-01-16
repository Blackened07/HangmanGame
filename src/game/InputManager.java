package game;

import game.handlers.CommandHandler;

import java.util.List;
import java.util.Scanner;

public class InputManager {

    private final Scanner scanner;

    private final List<CommandHandler> handlers;

    public InputManager(List<CommandHandler> handlers) {
        this.handlers = handlers;
        this.scanner = new Scanner(System.in);
    }

    public void mainInputHandler() {
        while (true) {
            String playerInput = takePlayerInput();

            for (CommandHandler handler : handlers) {
                if (handler.canHandle(playerInput)) {
                    handler.handle(playerInput);
                    break;
                }
            }
        }
    }

    private String takePlayerInput() {
        return scanner.nextLine().toLowerCase();
    }

}
