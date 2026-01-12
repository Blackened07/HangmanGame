package game.view;

import game.Game;
import game.GameState;
import game.downloader.DictionaryLoader;
import game.downloader.FileLoader;
import game.downloader.Paths;
import game.downloader.PropertiesUtil;
import game.storages.Dictionary;
import game.validator.EnterValidator;

import java.util.List;
import java.util.Scanner;

import static game.Game.ATTEMPTS_NUMBER;
import static game.validator.GameCommands.*;
import static game.validator.GameCommands.RESTART_GAME;
import static game.validator.GameCommands.START_GAME;

public class UserInterface {

    private static final String DICTIONARY_PATH = PropertiesUtil.get(Paths.DICTIONARY_PATH_KEY.getPath());

    private final EnterValidator enterValidator;
    private final Printer printer;
    private final Scanner scanner;
    private Game game;
    private boolean isGameOn;
    private String playerEnter;

    private final Dictionary dictionary;

    public UserInterface() {
        List<String> dictionaryLoad = FileLoader.loadFile(
                DICTIONARY_PATH,
                DictionaryLoader::loadDictionary);

        this.enterValidator = new EnterValidator();
        this.printer = new Printer();
        this.scanner = new Scanner(System.in);
        this.isGameOn = false;
        this.dictionary = new Dictionary(dictionaryLoad);
        isDictionaryNotEmpty(dictionaryLoad);
    }

    public void startPreGame() {
        printer.renderMessage(printer.FOR_START_GAME_MESSAGE);

        while (!isGameOn) {
            if (enterValidator.isPlayerEnterValid(takePlayerEnter(), isGameOn)) {
                if (enterValidator.isCommand(playerEnter)) {
                    setGameOrExit();
                    break;
                }
            }
        }
    }

    public void setGameOrExit() {

        if (playerEnter.equals(EXIT_GAME.getCommand()) && isGameOn) {
            stopGame();
        } else if (playerEnter.equals(EXIT_GAME.getCommand()) && !isGameOn) {
            exitGame();
        } else if (playerEnter.equals(START_GAME.getCommand()) && !isGameOn) {
            isGameOn = true;
            startGame();
        } else if (playerEnter.equals(RESTART_GAME.getCommand()) && isGameOn) {
            startGame();
        } else if (playerEnter.equals(START_GAME.getCommand()) && isGameOn) {
            printer.renderMessage(printer.GAME_IS_ON);
        } else if (playerEnter.equals(RESTART_GAME.getCommand()) && !isGameOn) {
            printer.renderMessage(printer.GAME_IS_WAITING);
            startPreGame();
        } else {
            startPreGame();
        }
    }

    public String takePlayerEnter() {
        String enter = scanner.nextLine().toLowerCase();
        setPlayerEnter(enter);
        return enter;
    }

    public char getPlayerEnter() {
        return playerEnter.charAt(0);
    }

    public boolean getGameOnState() {
        return isGameOn;
    }

    public boolean getPlayerEnterValid() {
        return enterValidator.isPlayerEnterValid(takePlayerEnter(), isGameOn);
    }

    public boolean getIsCommand() {
        return enterValidator.isCommand(playerEnter);
    }

    public String getRandomWord() {
        return dictionary.getRandomWord();
    }

    public void callPrinterToViewEveryTurnGameMessage() {

    }

    public void callPrinterToViewStateGameMessage(GameState state, String... message) {
        switch (state) {
            case RIGHT -> {
                printer.renderMessage(printer.RIGHT_ATTEMPT + message[0]);
            }
            case WRONG -> {
                printer.renderMessage(printer.WRONG_ATTEMPT);
            }
            case WIN -> {
                printer.renderMessage(printer.WIN_MESSAGE + printer.ENCODED_WORD_MESSAGE + message[0] + "\n");
            }
            case LOSE -> {
                printer.renderMessage(printer.LOSE_MESSAGE + printer.ENCODED_WORD_MESSAGE + message[0] + "\n");
            }
            case REPEAT -> {
                printer.renderMessage(printer.DUPLICATE_MESSAGE);
            }
            case EVERY_TURN -> {
                printer.renderMessage(printer.EXIST_LETTER + message[0]);
                printer.renderMessage(printer.ATTEMPTS_COUNT + message[1]);
                printer.renderMessage(message[2]);
                printer.renderMessage(printer.ENTER_THE_LETTER);
            }
        }
    }

    public void printHangman(int count) {
        printer.renderHangman(count);
    }

    public void setGameOn(boolean gameOn) {
        isGameOn = gameOn;
    }

    private void setPlayerEnter(String playerEnter) {
        this.playerEnter = playerEnter;
    }

    private void startGame() {
        game = new Game(this);
        printer.renderMessage(printer.START_GAME_MESSAGE);
        printer.renderMessage(printer.ENCODED_LETTERS_NUM + game.getMaskSize());
        game.startGame();
    }

    private void isDictionaryNotEmpty(List<String> dictionaryLoad) {
        if (dictionaryLoad.isEmpty()) {
            exitGame();
        }
    }

    private void stopGame() {
        isGameOn = !isGameOn;
    }

    private void exitGame() {
        printer.renderMessage(printer.EXIT_GAME);
        System.exit(0);
    }
}
