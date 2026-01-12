package game.downloader;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public final class PicturesLoader {

    private static final String ERROR = "Загрузка картинок не удалась";

    public static List<String> loadPictures(BufferedReader fileReader) {
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
            System.out.printf("%s; %s", ERROR, e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
