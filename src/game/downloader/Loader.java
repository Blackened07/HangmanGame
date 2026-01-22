package game.downloader;

import java.io.BufferedReader;
import java.util.List;
import java.util.function.Function;

public interface Loader {
    String ERROR = "Загрузка словаря не удалась";
    List<String> loadFile(String path, Function<BufferedReader, List<String>> load);
}
