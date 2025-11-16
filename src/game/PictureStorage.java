package game;

import java.util.List;

public class PictureStorage {

    private final List<String> pictures;

    public PictureStorage(List<String> pictures) {
        this.pictures = pictures;
    }

    public String get(int index) {
        return pictures.get(index);
    }

    public List<String> getList() {
        return pictures;
    }
}
