package com.automata.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductionInput {
    private String left;
    private List<String> alternatives = new ArrayList<>();

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives != null ? alternatives : new ArrayList<>();
    }
}
