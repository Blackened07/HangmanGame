package game;

import java.util.Random;

public class RandomWordGenerator {
    private final Dictionary dictionary;
    private final Random random;

    public RandomWordGenerator(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.random = new Random();
    }

    public String chooseTheRandomWord() {
        int wordsNumbers = dictionary.get().size();

        return dictionary.get().stream().toList().get(random.nextInt(wordsNumbers));
    }
}
