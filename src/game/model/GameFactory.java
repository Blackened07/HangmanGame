package game.model;

import game.downloader.Path;
import game.storages.Dictionary;
import game.storages.PictureStorage;

public class GameFactory {

    private final Dictionary dictionary;
    private final PictureStorage pictureStorage;

    public GameFactory(Dictionary dictionary, PictureStorage pictureStorage) {
        this.dictionary = dictionary;
        this.pictureStorage = pictureStorage;
    }

    public PictureStorage getPictures() {
        return pictureStorage;
    }

    public Game getSession() {
        if (dictionary.isEmpty()) {
            dictionary.set(Path.RUSSIAN_DICTIONARY.getPath());
        }

        if (pictureStorage.isEmpty()) {
            pictureStorage.set(Path.HANGMAN_PICTURES.getPath());
        }

        return new Game(dictionary.get());
    }

}
