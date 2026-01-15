package game;

import game.model.Game;
import game.storages.Dictionary;
import game.view.Printer;

public class Action {

    private final Dictionary dictionary;
    private final Printer printer;
    private InputManager manager;
    private Game game;

    public Action(Dictionary dictionary, Printer printer) {
        this.dictionary = dictionary;
        this.printer = printer;
    }

    public void setManager (InputManager manager) {
        this.manager = manager;
    }

    public void createGame() {
        game = new Game(dictionary.getRandomWord());
        manager.setGameOn(true);
        printer.printMessage(printer.START_GAME_MESSAGE);
        printer.printRoundMessage(game.getEncodedWordMask(), game.getEncodedWordSize(), game.getEnteredLetters(), Game.ATTEMPTS_NUMBER);
    }

    public void makeMove(String input) {
        char letter = getPlayerInputForGameProcess(input);

        if (game.isPlayerEnterNotDuplicate(letter)) {
            game.setEnteredLetter(letter);
            checkWhenPlayerInputNotDuplicate(letter);

        } else {
            printer.printDuplicateLetterMessage( );
            printer.printMessage(game.getEnteredLetters());
        }

    }

    public void exit() {
        if (manager.isGameOn()) {
            manager.setGameOn(false);
            printer.printStartMessage();
        } else {
            printer.printMessage(printer.EXIT_GAME);
            System.exit(0);
        }
    }

    private void checkWhenPlayerInputNotDuplicate(char letter) {

        if (game.isPlayerEnterEqualsEncodedWordLetters(letter)) {
            game.replaceEncodedToLetter(letter);
            printer.printRightMessage(game.getEnteredLetter());
            if (game.isWin()) {
                printer.printWin(game.getEncodedWordMask());
                processEndGame();
                return;
            }
            printRound();
        } else {
            printer.printWrongMessage(game.getEnteredLetter());
            printer.printHangman(game.getCount());
            game.setCount();
            if (game.isCountToLose()) {
                printer.printLose(game.getEncodedWord());
                processEndGame();
                return;
            }
            printRound();
        }
    }

    private void printRound() {
        printer.printRoundMessage(
                game.getEncodedWordMask(),
                game.getEncodedWordSize(),
                game.getEnteredLetters(),
                game.getAttempt());
    }

    private char getPlayerInputForGameProcess(String input) {
        return input.charAt(0);
    }

    private void processEndGame() {
        manager.setGameOn(false);
        printer.printStartMessage();
    }

}
