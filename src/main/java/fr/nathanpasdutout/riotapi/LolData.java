package fr.nathanpasdutout.riotapi;

import fr.nathanpasdutout.database.UserData;
import fr.nathanpasdutout.exceptions.AccountNotFoundException;
import net.dv8tion.jda.api.entities.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpResponse;

public class LolData {

    public static final String SOLO = "RANKED_SOLO_5x5";
    public static final String FLEX = "RANKED_FLEX_SR";

    public static final String PUUID = "puuid";
    public static final String GAME_NAME  = "gameName";
    public static final String TAG_LINE = "tagLine";
    public static final String WINS = "wins";
    public static final String LOSSES = "losses";
    public static final String LEAGUE_POINTS = "leaguePoints";
    public static final String TIER = "tier";
    public static final String RANK = "rank";

    public static JSONObject getAccountData(String gameName, String tagLine) {
        HttpResponse<String> response = (new Request("https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + gameName + "/" + tagLine + "/")).sendRequestToAPI();

        if(response.statusCode() != 200) return null;

        return new JSONObject(response.body());
    }

    public static JSONArray getLeagueData(User user) throws AccountNotFoundException {
        UserData data = new UserData(user);

        if(!data.hasData()) throw new AccountNotFoundException(user);

        HttpResponse<String> response = (new Request("https://euw1.api.riotgames.com/lol/league/v4/entries/by-puuid/" + data.getPUUID())).sendRequestToAPI();

        if(response.statusCode() != 200) return null;

        return new JSONArray(response.body());
    }
}
