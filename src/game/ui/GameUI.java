package game.ui;

import game.model.Game;
import game.model.GameFactory;
import game.validator.InvalidCommandException;
import game.view.GameState;
import game.view.GameView;
import game.view.HangmanView;

import game.view.View;

import static game.view.GameState.*;

public class GameUI extends ConsoleUI {
    private final View<GameState> gameView;
    private final View<Integer> hangman;
    private final Game game;

    public GameUI(GameFactory gameFactory) {
        super(gameFactory);
        this.game = gameFactory.getSession();
        this.gameView = new GameView(game.getSecretWord(), game);
        this.hangman = new HangmanView(gameFactory.getPictures());
    }

    @Override
    public void process() {
        setGameOn(true);
        System.out.println("!!!----Игра началась----!!!");

        while (isGameOn()) {
            gameView.render(IN_PROGRESS);
            String line = getLine();

            try {
                processCommand(line);
                processGame(line);
            } catch (InvalidCommandException e) {
                getException().render(INVALID_MESSAGE);
            }
        }
    }

    private void processGame(String line) {
        char letter = line.charAt(0);

        if (game.isDuplicate(letter)) {
            gameView.render(DUPLICATE);
        } else {
            if (game.open(letter)) {
                gameView.render(RIGHT_ATTEMPT);
            } else {
                gameView.render(WRONG_ATTEMPT);
                hangman.render(game.getMistakeCount());
            }
        }

        if (game.isWin()) {
            setGameOn(false);
            gameView.render(WIN);
        } else if (game.isLose()) {
            setGameOn(false);
            gameView.render(LOSE);
        }
    }

}
