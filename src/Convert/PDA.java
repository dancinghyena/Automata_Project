package Convert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PDA {
    static final Character EPSILON = null;

    List<State> states = new ArrayList<>();
    Set<Transition> transitions = new HashSet<>();

    void addState(State s) {
        states.add(s);
    }

    void addTransition(Transition t) {
        transitions.add(t);
    }
}

class Transition {
    State from;
    State to;
    Character input;
    Character pop;
    List<Character> push = new ArrayList<>();

    Transition(State from, State to, Character input, Character pop, List<Character> push) {
        this.from = from;
        this.to = to;
        this.input = input;
        this.pop = pop;
        this.push = push != null ? new ArrayList<>(push) : new ArrayList<>();
    }
}

class State {
    private static int counter = 0;
    int id;
    boolean isAccepting;
    boolean isStart;

    State(boolean accepting, boolean start) {
        id = ++counter;
        this.isAccepting = accepting;
        this.isStart = start;
    }
}