package mts.teta.java_middle_back.services;

import mts.teta.java_middle_back.validators.MessageValidator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class EnrichmentService {
    private final MessageValidator validator;

    public EnrichmentService(MessageValidator validator) {
        this.validator = validator;
    }

    public String enrich(String body) throws JSONException {
        JSONObject message = new JSONObject(body);
        if (!validator.validateMessage(message)) {
            throw new IllegalArgumentException("msisdn is required; it must not be null");
        }
        enrichJson(message);
        return message.toString();
    }

    private static void enrichJson(JSONObject message) throws JSONException {
        message.put("name", "John");
    }
}