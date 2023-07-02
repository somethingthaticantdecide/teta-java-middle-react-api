package mts.teta.java_middle_back.validators;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MessageValidator {
    public boolean validateMessage(JSONObject message) {
        return message.has("msisdn");
    }
}