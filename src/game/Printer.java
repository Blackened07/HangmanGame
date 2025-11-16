package game;

public class Printer {
    private PictureStorage pictureStorage;
    private EncodedWord encodedWord;

    public Printer(PictureStorage pictureStorage, EncodedWord encodedWord) {
        this.pictureStorage = pictureStorage;
        this.encodedWord = encodedWord;
    }

    public void renderHangman(int count) {
        System.out.println(getPicture(count));
    }

    public void renderMask() {
        System.out.println(getMask());
    }

    private String getMask() {
        return encodedWord.getMask().toString();
    }

    private String getPicture(int count) {
        return pictureStorage.get(count);
    }

}
