package game.view;


import game.storages.PictureStorage;

public class HangmanView implements View<Integer> {

    private final PictureStorage pictures;

    public HangmanView(PictureStorage pictures) {
        this.pictures = pictures;
    }

    @Override
    public void render(Integer index) {
        System.out.println(pictures.get(index));
    }

}
