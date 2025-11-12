package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Words {
    private final String PATH = "resources/russian_Words.txt";
    private final Set<String> words;

    public Words() {
        words = new HashSet<>();
       wordsReader();
    }

    private void wordsReader () {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    assert false;
                    words.add(line);
                }
            }
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Set<String> getWords() {
        return words;
    }
}
