package game;

import game.validator.GameCommands;

import java.util.function.Predicate;

import static game.validator.GameCommands.RESTART_GAME;
import static game.validator.GameCommands.START_GAME;

public final class InputValidator {

    private static final String RUS_PATTERN = "[а-яёА-ЯЁ]";

    public static boolean isRussian(String playerInput) {
        return !playerInput.replaceAll(RUS_PATTERN, "").equals(playerInput);
    }

    public static boolean isOneLetter(String playerInput) {
        return playerInput.length() == 1;
    }

    public static boolean isStartCommand(String input) {
        return input.equals(GameCommands.START_GAME.getCommand()) ;
    }

    public static boolean isRestartCommand(String input) {
        return input.equals(GameCommands.RESTART_GAME.getCommand());
    }

    public static boolean isExitCommand(String input) {
        return input.equals(GameCommands.EXIT_GAME.getCommand());
    }

    public static boolean isMoreThanOneLetter(String playerInput) {
        return playerInput.length() != 1;
    }

    public static boolean isCommand(String input) {
        return input.equals(START_GAME.getCommand()) ||
                input.equals(RESTART_GAME.getCommand()) ||
                input.equals(GameCommands.EXIT_GAME.getCommand());
    }
}
