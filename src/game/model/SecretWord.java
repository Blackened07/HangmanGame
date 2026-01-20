package game.model;

import java.util.ArrayList;
import java.util.List;


public class SecretWord {
    private List<Letter> secretWord;

    public SecretWord(String word) {
        this.secretWord = getLettersList(word);
    }

    public List<Letter> getSecretWord() {
        return secretWord;
    }

    public boolean isLetterExist(char letter) {
        for (Letter l : secretWord) {
            if (l.getLetter() == letter) {
                l.setOpen(true);
                return true;
            }
        }
        return false;
    }

    public boolean checkAllOpenedLetters() {
        for (Letter l: secretWord) {
            if(!l.isOpen()) {
                return false;
            }
        }
        return true;
    }

    private List<Letter> getLettersList(String word) {
        secretWord = new ArrayList<>();

        for (Character letter : word.toCharArray()) {
            secretWord.add(new Letter(letter));
        }

        return secretWord;
    }


}
