package com.automata.web.dto;

public class StateDto {
    private int id;
    private boolean start;
    private boolean accepting;

    public StateDto() {
    }

    public StateDto(int id, boolean start, boolean accepting) {
        this.id = id;
        this.start = start;
        this.accepting = accepting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isAccepting() {
        return accepting;
    }

    public void setAccepting(boolean accepting) {
        this.accepting = accepting;
    }
}
