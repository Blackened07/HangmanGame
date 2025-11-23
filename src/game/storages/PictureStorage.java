package game.storages;

import game.GameFileInspector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PictureStorage {
    private final List<String> pictures;
    String PICTURES_PATH = "C:\\Users\\black\\IdeaProjects\\Hangman\\resources\\pics.txt";

    public PictureStorage(GameFileInspector gameFileReader) throws IOException {
        pictures = new ArrayList<>();
        isStorageFileExist(gameFileReader.isFileExist(PICTURES_PATH));
    }

    public String get(int index) {
        return pictures.get(index);
    }

    public int getListSize() {
        return pictures.size();
    }

    private void isStorageFileExist(boolean isExist) {
        if (isExist) {
            loadPictures();
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
