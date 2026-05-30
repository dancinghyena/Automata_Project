package Convert;

import java.util.ArrayList;
import java.util.List;

public class Transition {
    public final State from;
    public final State to;
    public final Character input;
    public final Character pop;
    public final List<Character> push;

    public Transition(State from, State to, Character input, Character pop, List<Character> push) {
        this.from = from;
        this.to = to;
        this.input = input;
        this.pop = pop;
        this.push = push != null ? new ArrayList<>(push) : new ArrayList<>();
    }
}
