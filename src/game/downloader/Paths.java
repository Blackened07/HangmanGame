package game.downloader;

public enum Paths {
    DICTIONARY_PATH_KEY("dictionary.path"),
    PICTURES_PATH_KEY("picture.path");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
