package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final List<Character> enteredLetters;
    private char enteredLetter;
    private final Scanner scanner;

    public Player() {
        this.enteredLetters = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public List<Character> getEnteredLetters() {
        return enteredLetters;
    }


    public Scanner getScanner() {
        return scanner;
    }

    public char getEnteredLetter() {
        return enteredLetter;
    }

    public void setEnteredLetter(char enteredLetter) {
        this.enteredLetter = enteredLetter;
    }
}
