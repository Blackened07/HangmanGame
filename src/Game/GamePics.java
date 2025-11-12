package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePics {
    private final String PATH = "resources/pics.txt";
    private final List<String> pics;

    public GamePics() {
        pics = new ArrayList<>();
        picsReader();
    }

    private void picsReader () {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            StringBuilder symbolicPics = new StringBuilder();
            String pic;
            while ((pic = reader.readLine()) != null) {
                String SEPARATOR = "#";
                if (!pic.equals(SEPARATOR)) {
                    symbolicPics.append(pic).append("\n");
                } else {
                    assert false;
                    pics.add(symbolicPics.toString());
                    symbolicPics.delete(0, symbolicPics.length());
                }
            }

        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPicsByIndex(int index) {
        return pics.get(index);
    }
}
