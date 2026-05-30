package com.automata.web.dto;

import java.util.ArrayList;
import java.util.List;

public class PdaResponse {
    private List<StateDto> states = new ArrayList<>();
    private List<TransitionDto> transitions = new ArrayList<>();

    public List<StateDto> getStates() {
        return states;
    }

    public void setStates(List<StateDto> states) {
        this.states = states;
    }

    public List<TransitionDto> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<TransitionDto> transitions) {
        this.transitions = transitions;
    }
}
