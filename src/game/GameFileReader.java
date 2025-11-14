package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameFileReader {
    private static final String WORDS_PATH = "resources/russian_Words.txt";
    private static final String PICTURES_PATH = "resources/pics.txt";

    private static final Set<String> dictionary = new HashSet<>();
    private static final List<String> pictures = new ArrayList<>();

    public GameFileReader() {}

    static {
        loadFiles(WORDS_PATH, PICTURES_PATH);
    }

    public static Set<String> getDictionary() {
        return dictionary;
    }

    public static List<String> getPictures() {
        return pictures;
    }

    private static void loadFiles(String... path) {

        for (String string : path) {

            try (BufferedReader reader = new BufferedReader(new FileReader(string))) {
                switch (string) {
                    case WORDS_PATH -> loadWords(reader);
                    case PICTURES_PATH -> loadPictures(reader);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void loadWords(BufferedReader reader) throws IOException {

        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                assert false;
                dictionary.add(line);
            }
        }
    }

    private static void loadPictures(BufferedReader reader) throws IOException {

        StringBuilder symbolicPics = new StringBuilder();
        String pic;

        while ((pic = reader.readLine()) != null) {
            String separator = "#";
            if (!pic.equals(separator)) {
                symbolicPics.append(pic).append("\n");
            } else {
                assert false;
                pictures.add(symbolicPics.toString());
                symbolicPics.delete(0, symbolicPics.length());
            }
        }
    }

}
