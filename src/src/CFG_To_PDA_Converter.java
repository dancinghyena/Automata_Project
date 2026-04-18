package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CFG_To_PDA_Converter {

    PDA convert(CFG cfg) {
        PDA result = new PDA();

        State state1 = new State(false, true);
        result.addState(state1);

        State state2 = new State(false, false);
        result.addState(state2);

        Character startSymbol = cfg.startSymbol;

        Transition tran1 = new Transition(state1, state2, null, null, List.of('$',startSymbol));
        result.addTransition(tran1);

        Set<Character> terminals = new HashSet<>();

        for (Production p : cfg.productions) {

            for (List<Character> entry : p.derivations) {

                for (Character c : entry) {
                    if (!Character.isUpperCase(c)) {
                        terminals.add(c);
                    }
                }

                List<Character> pushList = new ArrayList<>();

                for (int i = entry.size() - 1; i >= 0; i--) {
                    pushList.add(entry.get(i));
                }


                Transition t = new Transition(state2, state2, null, p.left, pushList);
                result.addTransition(t);

            }
        }

        for (Character c : terminals) {
            Transition terminal_t = new Transition(state2, state2, c, c, null);
            result.addTransition(terminal_t);
        }


        State state3 = new State(true, false);
        result.addState(state3);

        Transition tran3 = new Transition(state2, state3, null, '$', null);
        result.addTransition(tran3);

        return result;
    }

    public static void main(String[] args) {

    }

}
