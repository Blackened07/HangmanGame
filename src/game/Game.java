package game;

public class Game {
    private Player player;
    private EncodedWordChecker encodedWordChecker;
    private EncodedWord encodedWord;
    private final PictureStorage pictures;
    private final RandomWordGenerator randomWordGenerator;
    private final int attemptsNumber;
    private boolean gameIsOn;
    private int count;

    public Game(PictureStorage pictures, RandomWordGenerator randomWordGenerator) {
        this.pictures = pictures;
        this.randomWordGenerator = randomWordGenerator;
        this.attemptsNumber = pictures.getPictures().size();
    }

    public void startValidate(){
        //validate
        startGame();
    }

    public void startGame() {
        this.player = new Player();
        this.encodedWord = new EncodedWord(randomWordGenerator.chooseTheRandomWord());
        this.encodedWordChecker = new EncodedWordChecker(encodedWord, player);
        gameIsOn = true;

        while (gameIsOn) {

            String playerEnter = player.getScanner().nextLine();
            //game
            //playerEnterToChar in validate.. return char
            //checkPlayerEnter();
            //print
            //count
        }
    }


    public boolean checkPlayerEnter(char playerEnter) {
        if (!encodedWordChecker.checkEnteredLetters(playerEnter)) {
            return encodedWordChecker.checkEncodedWord(playerEnter);
        }
        return false;
    }

    public void replaceEncodedToLetter(char playerEnter) {
        for (int letterIndex = 0; letterIndex < encodedWord.get().size(); letterIndex++) {
            if (encodedWord.get().get(letterIndex) == playerEnter) {
                encodedWord.getMask().set(letterIndex, playerEnter);
            }
        }
    }

}
