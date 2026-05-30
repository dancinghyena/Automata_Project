package Convert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PDA {
    public static final Character EPSILON = null;

    public final List<State> states = new ArrayList<>();
    public final Set<Transition> transitions = new HashSet<>();

    public void addState(State s) {
        states.add(s);
    }

    public void addTransition(Transition t) {
        transitions.add(t);
    }
}
