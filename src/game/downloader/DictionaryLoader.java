package game.downloader;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class DictionaryLoader {
    public static final String DICTIONARY_PATH = "resources/russian_Words.txt";
    private static final String ERROR = "Загрузка словаря не удалась";

    public static List<String> load(BufferedReader fileReader)  {
            Set<String> dictionary = new HashSet<>();
            String line;
        try {
            while ((line = fileReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String hyphen = "-";
                    if (!line.contains(hyphen)) {
                        assert false;
                        dictionary.add(line);
                    }
                }
            }
            return new ArrayList<>(dictionary);
        } catch (Exception e) {
            System.out.printf("%s; %s", ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
