package mts.teta.java_middle_back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mts.teta.java_middle_back.enums.EnrichmentType;

@JsonIgnoreProperties
public class Message {
    private String content;
    private String action;
    private String page;
    private String msisdn;
    private EnrichmentType enrichmentType;
}
