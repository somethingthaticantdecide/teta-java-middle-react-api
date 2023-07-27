package mts.teta.java_middle_back.controller;

import mts.teta.java_middle_back.services.EnrichmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnrichmentController {
    @Autowired
    EnrichmentService enrichmentService;

    @PostMapping("/enrich")
    public HttpEntity<String> doPost(@RequestBody String message) {
        try {
            String body = enrichmentService.enrich(message);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(message);
        }
    }
}
