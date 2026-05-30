package com.automata.web.dto;

import java.util.ArrayList;
import java.util.List;

public class CfgToPdaRequest {
    private String startSymbol;
    private List<ProductionInput> productions = new ArrayList<>();

    public String getStartSymbol() {
        return startSymbol;
    }

    public void setStartSymbol(String startSymbol) {
        this.startSymbol = startSymbol;
    }

    public List<ProductionInput> getProductions() {
        return productions;
    }

    public void setProductions(List<ProductionInput> productions) {
        this.productions = productions != null ? productions : new ArrayList<>();
    }
}
