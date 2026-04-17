package src;

import java.util.ArrayList;
import java.util.List;

public class CFG {
	List<Production> productions = new ArrayList<>();
	Character startSymbol;
	CFG(Character startSymbol){
		this.startSymbol = startSymbol;
	}
	void addProduction(Production p) {
		productions.add(p);
	}
}

class Production {

	Character left;
    List<List<Character>> derivations = new ArrayList<>();

   Production(Character l, List<List<Character>> d ) {
    	left = l;
    	derivations = d;
    }
}