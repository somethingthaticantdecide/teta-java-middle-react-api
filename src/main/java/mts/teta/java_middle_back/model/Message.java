package mts.teta.java_middle_back.model;

public class Message {
    private String content;
    private String msisdn;
    private EnrichmentType enrichmentType;

    public enum EnrichmentType {
        MSISDN;
    }
}
