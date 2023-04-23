package mts.teta.java_middle_back.services;

import mts.teta.java_middle_back.model.Message;
import mts.teta.java_middle_back.validators.MessageValidator;
import org.springframework.stereotype.Service;

@Service
public class EnrichmentService {
    // принцип DI НЕ нарушен
    private final MessageValidator validator;

    public EnrichmentService(MessageValidator validator) {
        this.validator = validator;
    }

    public String enrich(Message message) {
        return "";
    }
}