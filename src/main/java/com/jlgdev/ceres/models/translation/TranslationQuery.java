package com.jlgdev.ceres.models.translation;

public class TranslationQuery {
        
    private String q = "";
	private String source = "en";
	private String target = "fr";
	private String format = "text";
	private int alternatives = 1;
	private String api_key = "";
    
    public TranslationQuery() {
    }

    public TranslationQuery(String q, String source, String target, String format, int alternatives, String api_key) {
        this.q = q;
        this.source = source;
        this.target = target;
        this.format = format;
        this.alternatives = alternatives;
        this.api_key = api_key;
    }

    public TranslationQuery(String q) {
        this.q = q;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(int alternatives) {
        this.alternatives = alternatives;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    
}
