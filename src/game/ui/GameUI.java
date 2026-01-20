package game.ui;

import game.model.Game;
import game.model.GameStatus;
import game.view.GameView;
import game.view.HangmanView;

import game.view.View;

public class GameUI extends ConsoleUI {
    private final View<GameStatus> gameView;
    private final View<Integer> hangman;
    private final Game game;

    public GameUI(Game game) {
        super();
        this.game = game;
        this.gameView = new GameView(game.getSecretWord(), game);
        this.hangman = new HangmanView();
    }

    @Override
    public void process() {
        System.out.println("!!!----Игра началась----!!!");

        while (game.getStatus() == GameStatus.IN_PROGRESS) {
            gameView.render(game.getStatus());
            String line = processLine();
            char letter = line.charAt(0);

            if (game.isDuplicate(letter)) {
                System.out.printf("Вы уже вводили букву %s\n", letter);
                //chek in consUI
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

        }
    }

}
