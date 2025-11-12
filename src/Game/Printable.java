package Game;

import java.util.List;

public interface Printable {
    /// GameSystemMess
    String USER_INPUT_START_WAITING_MESSAGE = "Введите команду: ";
    String START_GAME_MESSAGE = "Игра началась";
    String RESTART_GAME_MESSAGE = "Перезапуск игры. " + START_GAME_MESSAGE;
    String FAIL_RESTART_GAME_MESSAGE = "Игра не началась, нечего перезапускать. Введите 'Н', что бы начать игру!";
    String FAIL_START_GAME_MESSAGE = "Игра в процессе и её можно только перезапустить! Введите 'Р', что бы перезапустить игру!";
    String START_GAME_AFTER_GAME_MESSAGE = "Если хотите продолжить игру, введите 'Н'\nЕсли хотите выйти, введите 'В'";
    String NOTHING_INPUT_MESSAGE = "Вы ничего не ввели";
    String MORE_THAN_ONE_CHAR_INPUT_MESSAGE = "Вы ввели больше одной буквы";
    String USER_INPUT_ERROR_MESSAGE = "Вы ввели неверную команду";
    /// InGameMess
    String USER_INPUT_IN_GAME_WAITING_MESSAGE = "Введите букву: ";
    String ATTEMPTS_COUNT_MESSAGE = "Количество попыток: ";
    String CHAR_USER_INPUT_IN_GAME_MESSAGE = "Вы ввели букву: ";
    String REPEAT_MESSAGE = "Данный символ уже был вами введён";
    String REPEAT_ABOUT_MESSAGE = "Повторно введённый символ не считается за ошибку! Вам повезло!";
    String NOT_MESSAGE = "В загаданном слове нет такой буквы";
    /// EndGameMess
    String WIN_MESSAGE = "ВЫ ПОБЕДИЛИ!!!";
    String LOSE_MESSAGE = "Вы проиграли!";
    String ENCODED_WORD_MESSAGE = "Загаданное слово: ";


    default void print (String message) {
        System.out.println(message);
    }
    default void printReplaceSymbols (List<String> chars) {
        for (int i = 0; i < chars.size(); i++) {
            if (i != chars.size() - 1) {
            System.out.print(chars.get(i));
            } else System.out.print(chars.get(i) + "\n");
        }
    }
    default void printHung(GamePics gamePics, int count) {
        print(gamePics.getPicsByIndex(count));
    }
}
