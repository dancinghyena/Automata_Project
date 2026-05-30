package Convert;

import java.util.ArrayList;
import java.util.List;

public class CFG {
    public final List<Production> productions = new ArrayList<>();
    public final Character startSymbol;

    public CFG(Character startSymbol) {
        this.startSymbol = startSymbol;
    }

    public void addProduction(Production p) {
        productions.add(p);
    }
}
