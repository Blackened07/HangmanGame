package game;

public class EnterValidator {
    public final String START_GAME = "старт";
    public final String RESTART_GAME = "рестарт";
    public final String EXIT_GAME = "выход";

    private static final String NOTHING_INPUT_MESSAGE = "Вы ничего не ввели, пожалуйста введите русскую букву";
    private static final String RUS_PATTERN = "[а-яёА-ЯЁ]";
    private static final String ENG_LETTER_ENTERED = "Вы ввели цифру или букву другого алфавита, пожалуйста введите русскую букву";
    private static final String MORE_THAN_ONE_LETTER = "Вы ввели больше одной буквы, пожалуйста введите русскую букву";

    public EnterValidator() {
    }

    public boolean isPlayerEnterValid(String playerEnter) {
        return isValid(playerEnter);
    }

    public boolean isCommandOrLetter(String playerEnter) {
        return isStartGameCommand(playerEnter);
    }

    private boolean isValid (String playerEnter) {
        try {
            if (playerEnter.isEmpty()) {
                throw new InvalidCommandException(NOTHING_INPUT_MESSAGE);
            } else if (!isRussian(playerEnter)) {
                throw new InvalidCommandException(ENG_LETTER_ENTERED);
            } else if (isMoreThanOneLetter(playerEnter)) {
                if (isStartGameCommand(playerEnter)) {
                    throw new InvalidCommandException(MORE_THAN_ONE_LETTER);
                } else {
                    return true;
                }
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

    private boolean isStartGameCommand(String playerEnter) {
        return !playerEnter.equals(START_GAME) && !playerEnter.equals(RESTART_GAME) && !playerEnter.equals(EXIT_GAME);
    }

}
