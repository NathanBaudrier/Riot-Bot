package fr.nathanpasdutout.riotapi.elements.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {

    private String id;
    @JsonProperty("name")
    private String region;
    private String[] locales;
    private Data[] maintenances;
    private Data[] incidents;

    public String getId() {
        return this.id;
    }

    public String getRegion() {
        return this.region;
    }

    public String[] getLocales() {
        return this.locales;
    }

    public Data[] getMaintenances() {
        return this.maintenances;
    }

    public Data[] getIncidents() {
        return this.incidents;
    }
}
