package game.model;

import game.downloader.DictionaryLoader;
import game.downloader.FileLoader;
import game.storages.Dictionary;

public class GameFactory {

    private final Dictionary dictionary;

    public GameFactory() {
        this.dictionary = new Dictionary(
                FileLoader.loadFile(
                        DictionaryLoader.DICTIONARY_PATH,
                        DictionaryLoader::load)
        );
    }

    public Game getSession() {
        return new Game(dictionary.getRandomWord());
    }
}
