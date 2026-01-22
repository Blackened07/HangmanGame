package game.downloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.function.Function;

public class FromDiskLoader implements Loader {

    @Override
    public List<String> loadFile(String path, Function<BufferedReader, List<String>> load) {

        try (BufferedReader fileReader = new BufferedReader(new FileReader(path))) {

            return load.apply(fileReader);

        } catch (Exception e) {
            System.out.printf("%s! Работа программы будет завершена!", e.getMessage());
            System.exit(0);
        }

        return List.of();
    }

}
