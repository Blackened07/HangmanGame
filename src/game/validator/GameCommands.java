package game.validator;

public enum GameCommands {
    START_GAME("старт"),
    RESTART_GAME("рестарт"),
    EXIT_GAME("выход"),
    LETTER("[а-яёА-ЯЁ]");

    private final String command;

    GameCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
