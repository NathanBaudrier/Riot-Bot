package fr.nathanpasdutout.riotapi;

import org.json.JSONObject;

import java.net.http.HttpResponse;

public class LolData {

    public static final String PUUID = "puuid";
    public static final String GAME_NAME  = "gameName";
    public static final String TAG_LINE = "tagLine";

    public static JSONObject getAccountData(String gameName, String tagLine) {
        HttpResponse<String> response = (new Request("https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + gameName + "/" + tagLine + "/")).getResponse();

        if(response.statusCode() != 200) return null;

        return new JSONObject(response.body());
    }
}
