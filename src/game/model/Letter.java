package game.model;

import java.util.Objects;

public class Letter {

    private final Character letter;
    private boolean isOpen;

    public Letter(Character letter) {
        this.letter = letter;
    }

    public Character getLetter() {
        return letter;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Letter letter1 = (Letter) object;
        return isOpen == letter1.isOpen && Objects.equals(letter, letter1.letter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, isOpen);
    }

   @Override
    public String toString() {
        return letter.toString();
    }
}
