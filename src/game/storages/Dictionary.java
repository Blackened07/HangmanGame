package game.storages;

import game.GameFileInspector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Dictionary {
    private final Set<String> dictionary;
    String DICTIONARY_PATH = "C:\\Users\\black\\IdeaProjects\\Hangman\\resources\\russian_Words.txt";

    public Dictionary(GameFileInspector gameFileReader) throws IOException {
        this.dictionary = new HashSet<>();
        isDictionaryFileExist(gameFileReader.isFileExist(DICTIONARY_PATH));
    }

    public String getRandomWord() {
        Random random = new Random();
        int wordsNumbers = dictionary.size();

        return dictionary.stream().toList().get(random.nextInt(wordsNumbers));
    }

    private void isDictionaryFileExist(boolean isExist) {
        if (isExist) {
            load();
        }
    }

    private void load() {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
