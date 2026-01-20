package game.view;

public class ExceptionView implements View<String> {

    @Override
    public void render(String message) {
        System.out.println(message);
    }
}
