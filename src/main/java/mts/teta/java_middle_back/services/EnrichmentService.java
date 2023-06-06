package mts.teta.java_middle_back.services;

import mts.teta.java_middle_back.model.Message;
import mts.teta.java_middle_back.validators.MessageValidator;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class EnrichmentService {
    private final MessageValidator validator;

    public EnrichmentService(MessageValidator validator) {
        this.validator = validator;
    }

    public String enrich(Message message) {
        Assert.notNull(message.getMsisdn(), "[Assertion failed] - this argument is required; it must not be null");
        return "";
    }

    public String enrich(String body) throws JSONException {
        JSONObject message = new JSONObject(body);
        Assert.notNull(message.get("msisdn"), "msisdn is required; it must not be null");

//        JSONAssert.assertNotEquals();
        return "";
    }
}