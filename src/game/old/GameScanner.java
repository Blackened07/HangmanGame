package game.old;

import game.InvalidCommandException;

import java.util.Scanner;

public interface GameScanner extends Printable {
    String START_GAME_COMMAND = "Старт";
    String RESTART_GAME_COMMAND = "Рустарт";
    String EXIT_GAME_COMMAND = "Выход";
    String RUS_PATTERN = "[а-яА-Я]";

    default String gameScanner(boolean isGameOn) {
        Scanner sc = new Scanner(System.in);
        String userTextInput;
        char character;
        while (true) {
            try {
                userTextInput = sc.nextLine();

                if (userTextInput.isEmpty()) throw new InvalidCommandException(NOTHING_INPUT_MESSAGE);
                else if (userTextInput.equals(userTextInput.replaceAll(RUS_PATTERN,""))) throw new InvalidCommandException(USER_INPUT_ERROR_MESSAGE);
                else if (userTextInput.length() != 1) throw new InvalidCommandException(MORE_THAN_ONE_CHAR_INPUT_MESSAGE);
                else if (userTextInput.equals(START_GAME_COMMAND) || userTextInput.equals(RESTART_GAME_COMMAND) || userTextInput.equals(EXIT_GAME_COMMAND)) {
                    switch (userTextInput) {
                        case EXIT_GAME_COMMAND -> {
                            return EXIT_GAME_COMMAND;
                        }
                        case START_GAME_COMMAND -> {
                            return START_GAME_COMMAND;
                        }
                        case RESTART_GAME_COMMAND -> {
                            return RESTART_GAME_COMMAND;
                        }
                    }
                }
                character = userTextInput.charAt(0);
                if (Character.isLowerCase(character) && isGameOn) {
                    print(CHAR_USER_INPUT_IN_GAME_MESSAGE + userTextInput);
                    return userTextInput;
                } else throw new InvalidCommandException(USER_INPUT_ERROR_MESSAGE);

            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
