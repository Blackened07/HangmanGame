package game;

import java.io.File;
import java.io.IOException;

public class GameFileInspector {

    public boolean isFileExist(String path) {
        try  {
            if (isExist(path)) {
                return true;
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            String loadingError = ". Работа программы будет завершена!";
            System.out.println(e.getMessage() + loadingError);
            System.exit(0);
        }
        return false;
    }

    private boolean isExist(String path) throws IOException {
        File file = new File(path);
        return file.exists();
    }
}
