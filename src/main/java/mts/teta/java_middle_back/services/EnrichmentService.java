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

    public String enrich(String body) throws JSONException {
        JSONObject message = new JSONObject(body);
        if (!validator.validateMessage(message)) {
            return body;
        }

        Callable<String> task = () -> enrichJson(message);
        Future<String> futureResult = executor.submit(task);

        try {
            return futureResult.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private String enrichJson(JSONObject message){
        try {
            message.put("name", "John");
        } catch (JSONException ignored) {
        }
        return message.toString();
    }
}