package game.validator;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String message) {
        super(message);
    }
}
