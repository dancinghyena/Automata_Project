package com.automata.web;

import com.automata.web.dto.CfgToPdaRequest;
import com.automata.web.dto.PdaResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CfgToPdaController {

    private final CfgToPdaService service;

    public CfgToPdaController(CfgToPdaService service) {
        this.service = service;
    }

    @PostMapping("/cfg-to-pda")
    public PdaResponse convert(@RequestBody CfgToPdaRequest request) {
        return service.convert(request);
    }
}
