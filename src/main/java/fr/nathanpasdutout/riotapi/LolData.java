package fr.nathanpasdutout.riotapi;

import fr.nathanpasdutout.database.UserData;
import fr.nathanpasdutout.exceptions.AccountNotFoundException;
import fr.nathanpasdutout.exceptions.RequestFailedException;
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

    public static JSONObject getAccountData(String gameName, String tagLine) throws RequestFailedException {
        HttpResponse<String> response = (new Request("https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + gameName + "/" + tagLine + "/")).sendRequestToAPI();

        if(response.statusCode() != 200) throw new RequestFailedException(response.statusCode());

        return new JSONObject(response.body());
    }

    public static JSONArray getLeagueData(User user) throws AccountNotFoundException, RequestFailedException {
        UserData data = new UserData(user);

        if(!data.hasData()) throw new AccountNotFoundException(user);

        HttpResponse<String> response = (new Request("https://euw1.api.riotgames.com/lol/league/v4/entries/by-puuid/" + data.getPUUID())).sendRequestToAPI();

        if(response.statusCode() != 200) throw new RequestFailedException(response.statusCode());

        return new JSONArray(response.body());
    }

    public static JSONArray getChampionsData() throws RequestFailedException {
        HttpResponse<String> response = (new Request("https://ddragon.leagueoflegends.com/cdn/15.10.1/data/fr_FR/champion.json")).sendRequestToAPI();

        if(response.statusCode() != 200) throw new RequestFailedException(response.statusCode());

        return new JSONArray(response.body());
    }

    public static JSONObject getStatusData() throws RequestFailedException {
        HttpResponse<String> response = (new Request("https://euw1.api.riotgames.com/lol/status/v4/platform-data")).sendRequestToAPI();

        if(response.statusCode() != 200) throw new RequestFailedException(response.statusCode());

        return new JSONObject(response.body());
    }
}
