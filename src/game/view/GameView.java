package game.view;

import game.model.Game;
import game.model.Letter;
import game.model.SecretWord;

import java.util.List;
import java.util.function.Function;

public class GameView implements View<GameState>{
    private final SecretWord word;
    private final Game game;
    private static final char MASK_SYMBOL = '*';
    private static final String WIN = "ВЫ ПОБЕДИЛИ!!! Загаданное слово: ";
    private static final String LOSE = "ВЫ ПРОИГРАЛИ!!! Загаданное слово: ";
    private static final String SECRET_WORD = "Загаданное слово: ";
    private static final String ATTEMPTS_NUMBER = "Количество попыток: ";
    private static final String ATTEMPTS_LEFT = "Попыток осталось: ";
    private static final String OPENED_LETTERS = "Введённые вами буквы: ";
    private static final String DUPLICATE_MESSAGE = "Вы уже вводили букву %s\n";
    private static final String RIGHT = "ВЕРНО!";
    private static final String WRONG = "Неверно...";
    private static final String SELECTOR_MESSAGE = "Что-бы начать заново введите команду: Старт!\nЕсли хотите выйти введите: Выход\n";

    public GameView(SecretWord word, Game game) {
        this.word = word;
        this.game = game;
    }

    @Override
    public void render(GameState state) {
        switch(state) {
            case IN_PROGRESS -> renderRoundMessage();
            case DUPLICATE -> System.out.println(DUPLICATE_MESSAGE);
            case RIGHT_ATTEMPT -> System.out.println(RIGHT);
            case WRONG_ATTEMPT -> System.out.println(WRONG);
            case WIN -> System.out.printf("%s%s\n%s", WIN, word.toString(), SELECTOR_MESSAGE);
            case LOSE -> System.out.printf("%s%s\n%s", LOSE, word.toString(), SELECTOR_MESSAGE);
        }
    }

    private void renderRoundMessage() {
        String line = String.format("%s%s\n%s%d\n%s%d\n%s%s\n",
                SECRET_WORD, renderMask(),
                ATTEMPTS_NUMBER, game.ATTEMPTS_NUMBER,
                ATTEMPTS_LEFT, game.getAttemptsLeft(),
                OPENED_LETTERS, game.getOpenedLetters());

        System.out.println(line);

    }

    private String renderMask() {
        List<Letter> secretWord = word.getSecretWord();

       return secretWord.stream().map((Function<Letter, Object>)letter -> {
            if (letter.isOpen()) {
                return letter.getLetter();
            } else {
                return MASK_SYMBOL;
            }
        }).toList().toString();
    }
}
