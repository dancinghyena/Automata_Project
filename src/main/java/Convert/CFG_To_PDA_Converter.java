package Convert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CFG_To_PDA_Converter {

    public PDA convert(CFG cfg) {
        State.resetIdCounter();

        PDA result = new PDA();

        State state1 = new State(false, true);
        result.addState(state1);

        State state2 = new State(false, false);
        result.addState(state2);

        Character startSymbol = cfg.startSymbol;

        Transition tran1 = new Transition(state1, state2, null, null, List.of('$', startSymbol));
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
            Transition terminalT = new Transition(state2, state2, c, c, List.of());
            result.addTransition(terminalT);
        }

        State state3 = new State(true, false);
        result.addState(state3);

        Transition tran3 = new Transition(state2, state3, null, '$', List.of());
        result.addTransition(tran3);

        return result;
    }

    private static void printPDA(PDA pda, String testName) {
        System.out.println("=== PDA for Test: " + testName + " ===");
        System.out.println("States (" + pda.states.size() + "):");
        for (State s : pda.states) {
            System.out.println("  State " + s.id + " (start=" + s.isStart + ", accepting=" + s.isAccepting + ")");
        }
        System.out.println("\nTransitions (" + pda.transitions.size() + "):");
        for (Transition t : pda.transitions) {
            String inputStr = (t.input == null) ? "ε" : t.input.toString();
            String popStr = (t.pop == null) ? "ε" : t.pop.toString();
            String pushStr = t.push.isEmpty() ? "ε" : t.push.toString();
            System.out.println("  " + t.from.id + " --[" + inputStr + ", pop:" + popStr + ", push:" + pushStr + "]--> " + t.to.id);
        }
        System.out.println("=====================================\n");
    }

    public static void main(String[] args) {
        CFG_To_PDA_Converter converter = new CFG_To_PDA_Converter();

        CFG cfg1 = new CFG('S');
        List<List<Character>> derivs1 = new ArrayList<>();
        derivs1.add(List.of('a', 'S', 'b'));
        derivs1.add(List.of());
        cfg1.addProduction(new Production('S', derivs1));
        printPDA(converter.convert(cfg1), "Test 1: S → aSb | ε   (a^n b^n)");

        CFG cfg2 = new CFG('S');
        List<List<Character>> derivs2 = new ArrayList<>();
        derivs2.add(List.of('a', 'S', 'a'));
        derivs2.add(List.of('b', 'S', 'b'));
        derivs2.add(List.of());
        cfg2.addProduction(new Production('S', derivs2));
        printPDA(converter.convert(cfg2), "Test 2: S → aSa | bSb | ε   (even palindromes)");

        CFG cfg3 = new CFG('S');
        cfg3.addProduction(new Production('S', List.of(List.of('A', 'B'))));
        cfg3.addProduction(new Production('A', List.of(List.of('a'))));
        cfg3.addProduction(new Production('B', List.of(List.of('b'))));
        printPDA(converter.convert(cfg3), "Test 3: S→AB, A→a, B→b   (string \"ab\")");

        CFG cfg4 = new CFG('S');
        List<List<Character>> derivs4 = new ArrayList<>();
        derivs4.add(List.of('a', 'S'));
        derivs4.add(List.of());
        cfg4.addProduction(new Production('S', derivs4));
        printPDA(converter.convert(cfg4), "Test 4: S → aS | ε   (a*)");
    }
}
