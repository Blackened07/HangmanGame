package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameFileReader {
    private static final String DICTIONARY_PATH = "C:\\Users\\black\\IdeaProjects\\Hangman\\resources\\russian_Words.txt";
    private static final String PICTURES_PATH = "C:\\Users\\black\\IdeaProjects\\Hangman\\resources\\pics.txt";
    private static final String LOADING_ERROR = ". Работа программы будет завершена!";
    private static final String HYPHEN = "-";
    private static final String SEPARATOR = "#";

    private static final Set<String> dictionary = new HashSet<>();
    private static final List<String> pictures = new ArrayList<>();

    public GameFileReader() {
    }

    static {
        loadFiles(DICTIONARY_PATH, PICTURES_PATH);
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
                    case DICTIONARY_PATH -> loadWords(reader);
                    case PICTURES_PATH -> loadPictures(reader);
                    default -> throw new IOException();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage() + LOADING_ERROR);
                System.exit(0);
            }
        }
    }

    private static void loadWords(BufferedReader reader) throws IOException {

        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                if (!line.contains(HYPHEN)) {
                    assert false;
                    dictionary.add(line);
                }
            }
        }
    }

    private static void loadPictures(BufferedReader reader) throws IOException {

        StringBuilder symbolicPics = new StringBuilder();
        String pic;

        while ((pic = reader.readLine()) != null) {
            if (!pic.equals(SEPARATOR)) {
                symbolicPics.append(pic).append("\n");
            } else {
                assert false;
                pictures.add(symbolicPics.toString());
                symbolicPics.delete(0, symbolicPics.length());
            }
        }
    }

}
