package game.storages;

import game.downloader.Loader;

import java.io.BufferedReader;
import java.util.*;

public class Dictionary implements Storage {
    private final Loader loader;
    private List<String> dictionary;

    public Dictionary(Loader loader) {
        this.loader = loader;
        this.dictionary = new ArrayList<>();
    }

    public String get() {
        Random random = new Random();
        int wordsNumbers = dictionary.size();
        return dictionary.get(random.nextInt(wordsNumbers));
    }

    @Override
    public void set(String path) {
        this.dictionary = loader.loadFile(path, Dictionary::load);
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    private static List<String> load(BufferedReader fileReader)  {
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
            System.out.printf("%s; %s", Loader.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
