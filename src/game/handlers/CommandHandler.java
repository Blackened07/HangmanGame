package game.handlers;

public interface CommandHandler {

    boolean canHandle(String input);

    void handle(String input);
}
