package mts.teta.java_middle_back.services;

import mts.teta.java_middle_back.model.UserInfo;
import mts.teta.java_middle_back.validators.MessageValidator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.*;

@Service
public class EnrichmentService {
    private final MessageValidator validator;
    private final ExecutorService executor;
    private Map<String, UserInfo> userMap;

    public EnrichmentService(MessageValidator validator, Map<String, UserInfo> userMap) {
        this.validator = validator;
        this.executor = Executors.newFixedThreadPool(4);
        this.userMap = userMap;
    }

    public String enrich(String body) {
        JSONObject message;
        try {
            message = new JSONObject(body);
        } catch (JSONException ignored) {
            return body;
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
            String msisdn = message.getString("msisdn");
            if (userExists(msisdn)) {
                UserInfo userInfo = getUserInfo(msisdn);

                JSONObject enrichment = new JSONObject();
                enrichment.put("firstName", userInfo.getFirstName());
                enrichment.put("lastName", userInfo.getLastName());

                message.put("enrichment", enrichment);
            }
        } catch (JSONException ignored) {}
        return message.toString();
    }

    public boolean userExists(String msisdn) {
        return userMap.containsKey(msisdn);
    }

    public UserInfo getUserInfo(String msisdn) {
        return userMap.get(msisdn);
    }

    public void shutdown() {
        executor.shutdown();
    }
}