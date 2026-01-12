package game.storages;

import game.downloader.DictionaryLoader;
import game.downloader.FileLoader;
import game.downloader.Paths;
import game.downloader.PropertiesUtil;

import java.util.List;
import java.util.Random;

public class Dictionary {

    private final List<String> dictionary;

    public Dictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public String getRandomWord() {
        Random random = new Random();
        int wordsNumbers = dictionary.size();
        return dictionary.get(random.nextInt(wordsNumbers));
    }

}
