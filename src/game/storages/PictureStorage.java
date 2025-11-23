package game.storages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PictureStorage {
    private final List<String> pictures;
    String PICTURES_PATH = "C:\\Users\\black\\IdeaProjects\\Hangman\\resources\\pics.txt";

    public PictureStorage() {
        pictures = new ArrayList<>();
        load();
    }

    public String get(int index) {
        return pictures.get(index);
    }

    public int getListSize() {
        return pictures.size();
    }

    private void load() {
        File file = new File(PICTURES_PATH);
        try {
            if (file.exists()) {
                loadPictures();
            } else {
                throw new IOException("Файл " + file.getName() + " не найден!");
            }
        } catch (IOException e) {
            String loadingError = ". Работа программы будет завершена!";
            System.out.println(e.getMessage() + loadingError);
            System.exit(0);
        }
    }

    private void loadPictures() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(PICTURES_PATH))) {
            StringBuilder symbolicPics = new StringBuilder();
            String pic;

            while ((pic = fileReader.readLine()) != null) {
                String separator = "#";
                if (!pic.equals(separator)) {
                    symbolicPics.append(pic).append("\n");
                } else {
                    assert false;
                    pictures.add(symbolicPics.toString());
                    symbolicPics.delete(0, symbolicPics.length());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
