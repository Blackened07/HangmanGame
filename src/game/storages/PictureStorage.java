package game.storages;

import game.downloader.Loader;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class PictureStorage {

    private final Loader loader;
    private List<String> pictures;

    public PictureStorage(Loader loader) {
        this.loader = loader;
        this.pictures = new ArrayList<>();
    }

    public String get(int index) {
        return pictures.get(index);
    }

    public void set(String path) {
        this.pictures = loader.loadFile(path, PictureStorage::load);
    }

    public boolean isEmpty() {
        return pictures.isEmpty();
    }

    private static List<String> load(BufferedReader fileReader) {
        List<String> pictures = new ArrayList<>();
        StringBuilder symbolicPics = new StringBuilder();
        String pic;

        try {
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
            return pictures;
        } catch (Exception e) {
            System.out.printf("%s; %s", Loader.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
