package fr.nathanpasdutout.riotapi.elements;

import org.json.JSONObject;

public class Champion {

    private int id;
    private JSONObject data;

    public Champion(int id, JSONObject data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return this.id;
    }

    public JSONObject getData() {
        return this.data;
    }
}
