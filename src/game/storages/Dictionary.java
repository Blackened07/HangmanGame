package game.storages;

import game.downloader.DictionaryLoader;
import game.downloader.FileLoader;
import game.downloader.Paths;
import game.downloader.PropertiesUtil;

import java.util.List;
import java.util.Random;

public class Dictionary {

    private static final String DICTIONARY_PATH = PropertiesUtil.get(Paths.DICTIONARY_PATH_KEY.getPath());

    private final List<String> dictionary;

    private Dictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public static Dictionary getInstance() {
        List<String> dictionaryLoad = FileLoader.loadFile(
                DICTIONARY_PATH,
                DictionaryLoader::loadDictionary);

        return new Dictionary(dictionaryLoad);
    }

    public String getRandomWord() {
        Random random = new Random();
        int wordsNumbers = dictionary.size();
        return dictionary.get(random.nextInt(wordsNumbers));
    }

}
