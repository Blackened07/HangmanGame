package Game;

import java.util.ArrayList;
import java.util.List;

public class GameProcess implements GameScanner{
    private final GamePics gamePics;
    private final RandomWordGenerator wordGenerator;
    private String randomWord;
    private final int numberOfAttempts;
    private int count;
    private boolean isGameOn;
    private final List<String> usersIntroducedChars;
    private final List<String> chars;
    private final List<String> replacedChars;
    private final String CODE = "*";
    private final String START_GAME_EMPTY_CHAR_INIT = "";

    public GameProcess(GamePics gamePics, RandomWordGenerator wordGenerator) {
        this.gamePics = gamePics;
        this.wordGenerator = wordGenerator;
        this.numberOfAttempts = 7;
        this.usersIntroducedChars = new ArrayList<>();
        this.chars = new ArrayList<>();
        this.replacedChars = new ArrayList<>();
    }

    private void setGameOn(boolean gameOn) {
        this.isGameOn = gameOn;
    }

    private void setRandomWord(String randomWord) {
        this.randomWord = randomWord;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public void preStartGame() {
        while (!isGameOn) {
            print(USER_INPUT_START_WAITING_MESSAGE);
            String userInput = gameScanner(isGameOn);
            if (inputChecker(userInput, chars)) {
                if(userInput.equals(EXIT_GAME_COMMAND)) break;
                setGameOn(true);
                game();
            }
        }
    }

    private void game() {
        setToNewStart();
        while (isGameOn) {
            printReplaceSymbols(replacedChars);
            print(ATTEMPTS_COUNT_MESSAGE + (numberOfAttempts - count));
            print(USER_INPUT_IN_GAME_WAITING_MESSAGE);
            String userInput = gameScanner(isGameOn);
            if(userInput.equals(EXIT_GAME_COMMAND)) break;
            if (!introducedCharsChecker(userInput)) {
                if (inputChecker(userInput, chars)) {
                    usersIntroducedChars.add(userInput);
                    if (checkReplacedChars()) {
                        print(WIN_MESSAGE + "\n" + ENCODED_WORD_MESSAGE + randomWord.toUpperCase() + "! " + START_GAME_AFTER_GAME_MESSAGE);
                        setGameOn(false);
                        break;
                    }
                }
                else {
                    usersIntroducedChars.add(userInput);
                    print(NOT_MESSAGE);
                    printHung(gamePics, count);
                    count++;
                    if (count == numberOfAttempts) {
                        print(LOSE_MESSAGE + "\n" + ENCODED_WORD_MESSAGE + randomWord.toUpperCase() + "! " + START_GAME_AFTER_GAME_MESSAGE);
                        setGameOn(false);
                        break;
                    }
                }
            } else print(REPEAT_ABOUT_MESSAGE);
        }
        preStartGame();
    }

    private boolean inputChecker(String input, List<String> chars) {
       switch (input) {
           case RESTART_GAME_COMMAND -> {
               if (isGameOn) {
                   print(RESTART_GAME_MESSAGE);
                   setToNewStart();
                   game();
                   return true;
               }
               else print(FAIL_RESTART_GAME_MESSAGE);
           }
           case START_GAME_COMMAND -> {
               if (!isGameOn){
                   print(START_GAME_MESSAGE);
                   return true;
               }
               else print(FAIL_START_GAME_MESSAGE);
           }
           case EXIT_GAME_COMMAND -> {
               return true;
           }

       }
        return gameWordChecker(input, chars);
    }
    private void setToNewStart () {
        chars.clear();
        replacedChars.clear();
        usersIntroducedChars.clear();
        int START_GAME_COUNT_INIT = 0;
        setCount(START_GAME_COUNT_INIT);
        setRandomWord(wordGenerator.chooseTheRandomWord());
        wordToChars(randomWord);
        replaceChars(START_GAME_EMPTY_CHAR_INIT);
    }

    private boolean gameWordChecker (String input, List<String> chars) {
        for (String s : chars) {
            if(input.equals(s)) {
                replaceChars(s);
                return true;
            }
        }
        return false;
    }

    private void wordToChars (String randomWord) {
        char[] wordChars = randomWord.toCharArray();
        for (int i = 0; i < randomWord.length(); i ++) {
            chars.add(String.valueOf(wordChars[i]));
        }
    }
    private boolean introducedCharsChecker(String input) {
        boolean check = false;
        try {
            for (String s : usersIntroducedChars) {
                if (!input.equals(s)) return check;
                else {
                    check = true;
                    throw new RuntimeException(REPEAT_MESSAGE.toUpperCase());
                }
            }
        } catch (RuntimeException e) {
            print(e.getMessage());
        }
        return check;
    }

    private void replaceChars (String ch) {
        if (ch.isEmpty()) {
            for (int i = 0; i < chars.size(); i++) {
                replacedChars.add(CODE);
            }
        } else {
            for (int i = 0; i < chars.size(); i++) {
                if (chars.get(i).equals(ch)) {
                    replacedChars.set(i, chars.get(i));
                }
            }
        }
    }
    private boolean checkReplacedChars () {
        String code = "";
        for (String s : replacedChars) {
            if (s.equals(CODE)) {
                code = s;
            }
        }
        return code.equals(START_GAME_EMPTY_CHAR_INIT);
    }
}
