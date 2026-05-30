package Convert;

import java.util.List;

public class Production {
    public final Character left;
    public final List<List<Character>> derivations;

    public Production(Character left, List<List<Character>> derivations) {
        this.left = left;
        this.derivations = derivations;
    }
}
