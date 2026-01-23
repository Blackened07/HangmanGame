import game.downloader.Loader;
import game.downloader.FromDiskLoader;
import game.model.GameFactory;
import game.storages.Dictionary;
import game.storages.PictureStorage;
import game.ui.MainMenuUi;

public class App {

    public static void main(String[] args) {

        Loader loader = new FromDiskLoader();

        Dictionary dictionary = new Dictionary(loader);
        PictureStorage pictureStorage = new PictureStorage(loader);

        GameFactory gameFactory = new GameFactory(dictionary, pictureStorage);

        MainMenuUi m = new MainMenuUi(gameFactory);
        m.process();
    }
}