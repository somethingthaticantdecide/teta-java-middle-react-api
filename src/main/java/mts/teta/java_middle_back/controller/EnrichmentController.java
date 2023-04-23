package mts.teta.java_middle_back.controller;

import mts.teta.java_middle_back.model.Message;
import mts.teta.java_middle_back.services.EnrichmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class EnrichmentController {
    @Autowired
    EnrichmentService enrichmentService;

    @PostMapping("/enrich")
    public String doPost(String message) {
        return "signIn";
    }
}
