package Game;

import java.util.Random;

public class RandomWordGenerator {
    private final Words words;

    public RandomWordGenerator(Words words) {
        this.words = words;
    }

    public String chooseTheRandomWord() {
        int numberOfWords = words.getWords().size();
        Random random = new Random();
        return words.getWords().stream().toList().get(random.nextInt(numberOfWords));
    }
}
