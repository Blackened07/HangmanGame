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
    public void process() throws InvalidCommandException {
        System.out.println("!!!----Игра началась----!!!");

        while (game.getStatus() == GameStatus.IN_PROGRESS) {
            gameView.render(game.getStatus());
            String line = getLine();

            if (isCommand(line)) {
                processCommand(line);
            }
            if (isOneValidLetter(line)) {
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
                    gameView.render(game.getStatus());
                } else if (game.isLose()) {
                    gameView.render(game.getStatus());
                }
            } else {
                throw new InvalidCommandException(INVALID_MESSAGE);
            }
        }
    }

}
