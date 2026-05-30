package com.automata.web.dto;

import java.util.ArrayList;
import java.util.List;

public class TransitionDto {
    private int from;
    private int to;
    private String input;
    private String pop;
    private List<String> push = new ArrayList<>();

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public List<String> getPush() {
        return push;
    }

    public void setPush(List<String> push) {
        this.push = push;
    }
}
