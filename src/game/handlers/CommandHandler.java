package game.handlers;

public interface CommandHandler {

    boolean canExecute(String input, boolean state);

    void handle(String input);
}
