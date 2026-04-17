package src;

import java.util.ArrayList;
import java.util.List;

public class CFG_To_PDA_Converter {

	
PDA convert(CFG cfg) {
	PDA result = new PDA();
	
    State state1 = new State(false, true);
    result.addState(state1);
    
    State state2 = new State(false, false);
    result.addState(state2);
    
    Transition tran1 = new Transition(state1, state2, null , null, List.of('$'));
    result.addTransition(tran1);

    Character startSymbol = cfg.startSymbol;
    
    State state3 = new State(false, false);
    result.addState(state3);
    
    Transition tran2 = new Transition(state2, state3, null , null, List.of(startSymbol));
    result.addTransition(tran2);

	for(Production p : cfg.productions) {
		for (List<Character> entry : p.derivations) {
			List<Character> pushList = new ArrayList<>();
			
			for (int i = entry.size() - 1; i >= 0; i--) {
			    pushList.add(entry.get(i));
			}
			
		    Transition t = new Transition(state3,state3,null,p.left,pushList);
		    result.addTransition(t);

		}
	}
	
	State state4 = new State(true, false);
    result.addState(state4);
    
    
	Transition tran4 = new Transition(state3, state4, null , '$', null);
    result.addTransition(tran4);
	
	return result;
}
	public static void main(String[] args) {

	}

}


// F0dA9MGQtfrcTNuM
// TYtN7KwC2cGP.47
// woBTf1DnB7sElciP
// woBTf1DnB7sElciP
// UGXICpfGAcBVno7I