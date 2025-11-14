package game;

import java.util.Set;

public class Dictionary {

    private final Set<String> dictionary;

    public Dictionary(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public Set<String> get(){
        return dictionary;
    }
}
