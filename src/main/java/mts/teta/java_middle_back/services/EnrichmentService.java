package mts.teta.java_middle_back.services;

import mts.teta.java_middle_back.validators.MessageValidator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class EnrichmentService {
    private final MessageValidator validator;
    ExecutorService executor = Executors.newFixedThreadPool(4);

    public EnrichmentService(MessageValidator validator) {
        this.validator = validator;
    }

    public String enrich(String body) {
        JSONObject message;
        try {
            message = new JSONObject(body);
        } catch (JSONException e) {
            throw new IllegalArgumentException("Invalid JSON format", e);
        }

        if (!validator.validateMessage(message)) {
            return body;
        }

        Future<String> futureResult = executor.submit(() -> enrichJson(message));

        try {
            return futureResult.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Enrichment process failed", e);
        } finally {
            futureResult.cancel(true); // Cancel the task if it hasn't completed yet
        }
    }

    private String enrichJson(JSONObject message) {
        try {
            message.put("name", "John");
        } catch (JSONException ignored) {
        }
        return message.toString();
    }

    public void shutdown() {
        executor.shutdown();
    }
}