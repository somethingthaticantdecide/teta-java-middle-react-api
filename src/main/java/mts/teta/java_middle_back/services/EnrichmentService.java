package mts.teta.java_middle_back.services;

import mts.teta.java_middle_back.model.Message;
import mts.teta.java_middle_back.validators.MessageValidator;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.utilities.Assert;

@Service
public class EnrichmentService {
    private final MessageValidator validator;

    public EnrichmentService(MessageValidator validator) {
        this.validator = validator;
    }

    public String enrich(Message message) {
//        Assert.that(message, );
        return "";
    }

    public String enrich(String message) {
//        JSONAssert.assertNotEquals();
        return "";
    }
}