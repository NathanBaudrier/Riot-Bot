package fr.nathanpasdutout.riotapi.elements.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Content {
    @JsonProperty("locale")
    private String language;
    @JsonProperty("content")
    private String text;

    public String getLanguage() {
        return this.language;
    }

    public String getText() {
        return this.text;
    }
}
