package fr.nathanpasdutout.riotapi.elements.status;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public class Update {
    private int id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private Boolean publish;
    private String author;
    @JsonProperty("translations")
    private Content[] contents;
    @JsonProperty("publish_locations")
    private String[] publishLocation;

    public int getId() {
        return this.id;
    }

    public OffsetDateTime getCreatedAt() {
        if(this.createdAt == null) return null;

        return OffsetDateTime.parse(this.createdAt);
    }

    public OffsetDateTime getUpdatedAt() {
        if(this.updatedAt == null) return null;

        return OffsetDateTime.parse(this.updatedAt);
    }

    public boolean isPublish() {
        return this.publish;
    }

    public String getAuthor() {
        return this.author;
    }

    public Content[] getContents() {
        return this.contents;
    }

    public String[] getPublishLocation() {
        return this.publishLocation;
    }
}
