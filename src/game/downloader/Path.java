package game.downloader;

public enum Path {
    RUSSIAN_DICTIONARY("resources/russian_Words.txt"),
    HANGMAN_PICTURES("resources/pics.txt");

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
