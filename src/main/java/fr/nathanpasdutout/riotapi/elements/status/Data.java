package fr.nathanpasdutout.riotapi.elements.status;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public class Data {
    private int id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("archive_at")
    private String archiveAt;
    @JsonProperty("titles")
    private Content[] contents;
    private Update[] updates;
    private String[] platforms;
    @JsonProperty("maintenance_status")
    private String status;
    @JsonProperty("incident_severity")
    private String severity;

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

    public OffsetDateTime getArchivedAt() {
        if(this.archiveAt == null) return null;

        return OffsetDateTime.parse(this.archiveAt);
    }

    public Content[] getContents() {
        return this.contents;
    }

    public Content getContent(String language) {
        for(Content content : this.contents) {
            if(content.getLanguage().equals(language)) return content;
        }

        return null;
    }

    public Update[] getUpdates() {
        return this.updates;
    }

    public String[] getPlatforms() {
        return this.platforms;
    }

    public String getStatus() {
        return this.status;
    }

    public String getSeverity() {
        return this.severity;
    }
}
