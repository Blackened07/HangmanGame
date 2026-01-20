package game.view;

import game.downloader.FileLoader;
import game.downloader.PicturesLoader;
import game.storages.PictureStorage;

public class HangmanView implements View<Integer> {

    private final PictureStorage pictureStorage;

    public HangmanView() {
        this.pictureStorage = new PictureStorage(
                FileLoader.loadFile(
                        PicturesLoader.PICTURE_PATH,
                        PicturesLoader::load)
        );

    }

    @Override
    public void render(Integer index) {
        System.out.println(pictureStorage.get(index));
    }

}
