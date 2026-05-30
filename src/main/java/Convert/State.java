package Convert;

public class State {
    private static int counter = 0;

    public final int id;
    public final boolean isAccepting;
    public final boolean isStart;

    public State(boolean accepting, boolean start) {
        id = ++counter;
        this.isAccepting = accepting;
        this.isStart = start;
    }

    static void resetIdCounter() {
        counter = 0;
    }
}
