package game.storages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Dictionary {
    private final Set<String> dictionary;
    String DICTIONARY_PATH = "C:\\Users\\black\\IdeaProjects\\Hangman\\resources\\russian_Words.txt";

    public Dictionary() {
        this.dictionary = new HashSet<>();
        load();
    }

    public String getRandomWord() {
        Random random = new Random();
        int wordsNumbers = dictionary.size();
        return dictionary.stream().toList().get(random.nextInt(wordsNumbers));
    }

    public void load() {
        File file = new File(DICTIONARY_PATH);
        try {
            if (file.exists()) {
                loadDictionary();
            } else {
                throw new IOException("Файл " + file.getName() + " не найден!");
            }
        } catch (IOException e) {
            String loadingError = ". Работа программы будет завершена!";
            System.out.println(e.getMessage() + loadingError);
            System.exit(0);
        }
    }

    private void loadDictionary() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(DICTIONARY_PATH))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String hyphen = "-";
                    if (!line.contains(hyphen)) {
                        assert false;
                        dictionary.add(line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
