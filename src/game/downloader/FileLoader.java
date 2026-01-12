package game.downloader;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public final class FileLoader {

    public static List<String> loadFile(String path, Function<BufferedReader, List<String>> load) {

        try (BufferedReader fileReader = new BufferedReader(new FileReader(path))) {

            return load.apply(fileReader);

        } catch (Exception e) {
            System.out.printf("%s! Работа программы будет завершена!", e.getMessage());
            System.exit(0);
        }

        return Collections.emptyList();
    }

}
