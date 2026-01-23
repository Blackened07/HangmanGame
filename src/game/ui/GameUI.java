package game.ui;

import game.model.Game;
import game.model.GameFactory;
import game.model.GameStatus;
import game.validator.InvalidCommandException;
import game.view.GameView;
import game.view.HangmanView;

import game.view.View;

public class GameUI extends ConsoleUI {
    private final View<GameStatus> gameView;
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
            gameView.render(game.getStatus());
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
            System.out.printf("Вы уже вводили букву %s\n", letter);
        } else {
            if (game.open(letter)) {
                System.out.println("RIGHT");
            } else {
                System.out.println("WRONG");
                hangman.render(game.getMistakeCount());
            }
        }

        if (game.isWin()) {
            setGameOn(false);
            gameView.render(game.getStatus());
        } else if (game.isLose()) {
            setGameOn(false);
            gameView.render(game.getStatus());
        }
    }

}
