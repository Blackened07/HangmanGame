package game.handlers;

import game.Action;
import game.validator.GameCommands;
import game.validator.InvalidCommandException;

import static game.validator.GameCommands.*;

public class InvalidHandler extends BaseHandler{
    private static final String NOTHING_INPUT_MESSAGE_ON_GAME_OFF = "Вы ничего не ввели, пожалуйста введите команду";
    private static final String NOTHING_INPUT_MESSAGE_ON_GAME_ON = "Вы ничего не ввели, пожалуйста введите русскую букву";
    private static final String ENG_LETTER_ENTERED = "Вы ввели цифру или букву другого алфавита, пожалуйста введите русскую букву";
    private static final String MORE_THAN_ONE_LETTER = "Вы ввели больше одного символа, пожалуйста введите русскую букву";
    private static final String ONE_LETTER_ENTERED_ON_GAME_OFF = "Вы ввели букву, пожалуйста введите команду";
    private static final String GAME_IS_WAITING = "Игра не запущена, нечего перезапускать";
    private static final String GAME_IS_ON = "Игра в процессе";

    public InvalidHandler(Action action) {
        super(action);
    }

    @Override
    public boolean canExecute(String input, boolean state) {
        try {
            if (input.isEmpty() && !state) {
                throw new InvalidCommandException(NOTHING_INPUT_MESSAGE_ON_GAME_OFF);
            } else if (input.isEmpty() && state) {
                throw new InvalidCommandException(NOTHING_INPUT_MESSAGE_ON_GAME_ON);
            } else if (isMoreThanOneLetter(input) ) {
                if (!isCommand(input) && state) {
                    throw new InvalidCommandException(MORE_THAN_ONE_LETTER);
                } else if (input.equals(GameCommands.RESTART_GAME.getCommand()) && !state ) {
                    throw new InvalidCommandException(GAME_IS_WAITING);
                } else if (input.equals(GameCommands.START_GAME.getCommand()) && state) {
                    throw new InvalidCommandException(GAME_IS_ON);
                }
            } else if (!isRussian(input)) {
                throw new InvalidCommandException(ENG_LETTER_ENTERED);
            } else if (isOneLetter(input) && !state) {
                throw new InvalidCommandException(ONE_LETTER_ENTERED_ON_GAME_OFF);
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void handle(String input) {

    }

    private static boolean isMoreThanOneLetter(String playerInput) {
        return playerInput.length() != 1;
    }

    private static boolean isCommand(String input) {
        return input.equals(START_GAME.getCommand()) ||
                input.equals(RESTART_GAME.getCommand()) ||
                input.equals(EXIT_GAME.getCommand());
    }
}
