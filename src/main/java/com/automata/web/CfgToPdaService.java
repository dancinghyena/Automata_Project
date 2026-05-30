package com.automata.web;

import Convert.CFG;
import Convert.CFG_To_PDA_Converter;
import Convert.PDA;
import Convert.Production;
import Convert.State;
import Convert.Transition;
import com.automata.web.dto.CfgToPdaRequest;
import com.automata.web.dto.PdaResponse;
import com.automata.web.dto.ProductionInput;
import com.automata.web.dto.StateDto;
import com.automata.web.dto.TransitionDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CfgToPdaService {

    private final CFG_To_PDA_Converter converter = new CFG_To_PDA_Converter();

    public PdaResponse convert(CfgToPdaRequest request) {
        CFG cfg = buildCfg(request);
        PDA pda = converter.convert(cfg);
        return toResponse(pda);
    }

    private CFG buildCfg(CfgToPdaRequest request) {
        if (request.getStartSymbol() == null || request.getStartSymbol().length() != 1) {
            throw new IllegalArgumentException("Start symbol must be exactly one character.");
        }
        char start = request.getStartSymbol().charAt(0);
        if (!Character.isUpperCase(start)) {
            throw new IllegalArgumentException("Start symbol must be an uppercase variable (A–Z).");
        }

        if (request.getProductions() == null || request.getProductions().isEmpty()) {
            throw new IllegalArgumentException("Add at least one production.");
        }

        CFG cfg = new CFG(start);
        for (ProductionInput production : request.getProductions()) {
            if (production.getLeft() == null || production.getLeft().length() != 1) {
                throw new IllegalArgumentException("Each production left-hand side must be one character.");
            }
            char left = production.getLeft().charAt(0);
            if (!Character.isUpperCase(left)) {
                throw new IllegalArgumentException("Production left-hand sides must be uppercase variables (A–Z).");
            }
            if (production.getAlternatives() == null || production.getAlternatives().isEmpty()) {
                throw new IllegalArgumentException("Production for " + left + " needs at least one alternative.");
            }

            List<List<Character>> derivations = new ArrayList<>();
            for (String alternative : production.getAlternatives()) {
                derivations.add(parseAlternative(alternative));
            }
            cfg.addProduction(new Production(left, derivations));
        }
        return cfg;
    }

    private List<Character> parseAlternative(String alternative) {
        if (alternative == null) {
            return List.of();
        }
        String trimmed = alternative.trim();
        if (trimmed.isEmpty()
                || trimmed.equals("ε")
                || trimmed.equalsIgnoreCase("epsilon")
                || trimmed.equalsIgnoreCase("eps")) {
            return List.of();
        }
        List<Character> symbols = new ArrayList<>();
        for (char c : trimmed.toCharArray()) {
            if (c == 'ε') {
                continue;
            }
            symbols.add(c);
        }
        return symbols;
    }

    private PdaResponse toResponse(PDA pda) {
        PdaResponse response = new PdaResponse();

        List<State> states = new ArrayList<>(pda.states);
        states.sort(Comparator.comparingInt(s -> s.id));
        for (State state : states) {
            response.getStates().add(new StateDto(state.id, state.isStart, state.isAccepting));
        }

        List<Transition> transitions = new ArrayList<>(pda.transitions);
        transitions.sort(Comparator
                .comparingInt((Transition t) -> t.from.id)
                .thenComparingInt(t -> t.to.id)
                .thenComparing(t -> symbolLabel(t.input))
                .thenComparing(t -> symbolLabel(t.pop)));

        for (Transition transition : transitions) {
            TransitionDto dto = new TransitionDto();
            dto.setFrom(transition.from.id);
            dto.setTo(transition.to.id);
            dto.setInput(symbolLabel(transition.input));
            dto.setPop(symbolLabel(transition.pop));
            for (Character symbol : transition.push) {
                dto.getPush().add(String.valueOf(symbol));
            }
            response.getTransitions().add(dto);
        }

        return response;
    }

    private static String symbolLabel(Character symbol) {
        return symbol == null ? "ε" : String.valueOf(symbol);
    }
}
