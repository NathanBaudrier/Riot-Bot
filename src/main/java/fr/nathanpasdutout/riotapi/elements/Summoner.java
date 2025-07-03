package fr.nathanpasdutout.riotapi.elements;

import org.json.JSONObject;

public class Summoner {

    public static final String SUMMONER_BARRIER = "SummonerBarrier";
    public static final String SUMMONER_CLEANSE = "SummonerBoost";
    public static final String SUMMONER_FLASH = "SummonerFlash";

    private String id;
    private JSONObject data;

    public Summoner(String id, JSONObject data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return this.id;
    }

    public JSONObject getData() {
        return this.data;
    }
}
