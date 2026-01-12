package game.validator;

import static game.validator.GameCommands.*;

public class EnterValidator {

    private static final String NOTHING_INPUT_MESSAGE_ON_GAME_OFF = "Вы ничего не ввели, пожалуйста введите команду";
    private static final String NOTHING_INPUT_MESSAGE_ON_GAME_ON = "Вы ничего не ввели, пожалуйста введите русскую букву";
    private static final String RUS_PATTERN = "[а-яёА-ЯЁ]";
    private static final String ENG_LETTER_ENTERED = "Вы ввели цифру или букву другого алфавита, пожалуйста введите русскую букву";
    private static final String MORE_THAN_ONE_LETTER = "Вы ввели больше одного символа, пожалуйста введите русскую букву";
    private static final String ONE_LETTER_ENTERED_ON_GAME_OFF = "Вы ввели букву, пожалуйста введите команду";
    private static final String MORE_THAN_ONE_LETTER_ON_GAME_OFF = "ERROR!!! Пожалуйста введите команду";

    public EnterValidator() {
    }

    public boolean isPlayerEnterValid(String playerEnter, boolean isGameOn) {
        return isValid(playerEnter, isGameOn);
    }

    public boolean isCommandOrLetter(String playerEnter) {
        return isStartGameCommand(playerEnter);
    }

    private boolean isValid(String playerEnter, boolean isGameOn) {
        try {
            if (playerEnter.isEmpty() && !isGameOn) {
                throw new InvalidCommandException(NOTHING_INPUT_MESSAGE_ON_GAME_OFF);
            } else if (playerEnter.isEmpty() && isGameOn) {
                throw new InvalidCommandException(NOTHING_INPUT_MESSAGE_ON_GAME_ON);
            } else if (isMoreThanOneLetter(playerEnter)) {
                if (isCommand(playerEnter, isGameOn)) {
                    return true;
                };
            } else if (!isRussian(playerEnter) && isGameOn) {
                throw new InvalidCommandException(ENG_LETTER_ENTERED);
            } else if (isOneLetter(playerEnter) && !isGameOn) {
                throw new InvalidCommandException(ONE_LETTER_ENTERED_ON_GAME_OFF);
            } else {
                return true;
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private boolean isRussian(String playerEnter) {
        return !playerEnter.replaceAll(RUS_PATTERN, "").equals(playerEnter);
    }

    private boolean isMoreThanOneLetter(String playerEnter) {
        return playerEnter.length() != 1;
    }

    private boolean isOneLetter(String playerEnter) {
        return playerEnter.length() == 1;
    }

    private boolean isCommand(String playerEnter, boolean isGameOn) throws InvalidCommandException {
        if (isStartGameCommand(playerEnter) && isGameOn) {
            throw new InvalidCommandException(MORE_THAN_ONE_LETTER);
        } else if (isStartGameCommand(playerEnter) && !isGameOn) {
            throw new InvalidCommandException(MORE_THAN_ONE_LETTER_ON_GAME_OFF);
        } else {
            return true;
        }
    }

    private boolean isStartGameCommand(String playerEnter) {
        return !playerEnter.equals(START_GAME.getCommand()) &&
                !playerEnter.equals(RESTART_GAME.getCommand()) &&
                !playerEnter.equals(EXIT_GAME.getCommand());
    }

}
