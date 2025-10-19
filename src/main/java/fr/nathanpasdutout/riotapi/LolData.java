package fr.nathanpasdutout.riotapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nathanpasdutout.database.UserData;
import fr.nathanpasdutout.exceptions.AccountNotFoundException;
import fr.nathanpasdutout.exceptions.RequestFailedException;
import fr.nathanpasdutout.riotapi.elements.account.Account;
import fr.nathanpasdutout.riotapi.elements.league.Rank;
import fr.nathanpasdutout.riotapi.elements.status.Status;
import net.dv8tion.jda.api.entities.User;

import java.net.http.HttpResponse;

public class LolData {

    public static Account getAccountData(String gameName, String tagLine) throws RequestFailedException {
        HttpResponse<String> response = (new Request("https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + gameName + "/" + tagLine + "/")).sendRequestToAPI();

        if(response.statusCode() != 200) throw new RequestFailedException(response.statusCode());

        try {
            return (new ObjectMapper()).readValue(response.body(), Account.class);
        } catch(JsonProcessingException exception) {
            System.err.println("Error JSON text and the class doesn't match.");
        }

        return null;
    }

    public static Rank[] getLeagueData(User user) throws AccountNotFoundException, RequestFailedException {
        UserData data = new UserData(user);

        if(!data.hasData()) throw new AccountNotFoundException(user);

        HttpResponse<String> response = (new Request("https://euw1.api.riotgames.com/lol/league/v4/entries/by-puuid/" + data.getPUUID())).sendRequestToAPI();

        if(response.statusCode() != 200) throw new RequestFailedException(response.statusCode());

        try {
            return (new ObjectMapper()).readValue(response.body(), Rank[].class);
        } catch(JsonProcessingException exception) {
            System.err.println(exception.getMessage());
        }

        return null;
    }

    /*
    public static JSONArray getChampionsData() throws RequestFailedException {
        HttpResponse<String> response = (new Request("https://ddragon.leagueoflegends.com/cdn/15.10.1/data/fr_FR/champion.json")).sendRequestToAPI();

        if(response.statusCode() != 200) throw new RequestFailedException(response.statusCode());

        return new JSONArray(response.body());
    }
    */

    public static Status getStatusData() throws RequestFailedException {
        HttpResponse<String> response = (new Request("https://euw1.api.riotgames.com/lol/status/v4/platform-data")).sendRequestToAPI();

        if(response.statusCode() != 200) throw new RequestFailedException(response.statusCode());

        try {
            return (new ObjectMapper()).readValue(response.body(), Status.class);
        } catch(JsonProcessingException exception) {
            System.err.println(exception.getMessage());
        }

        return null;
    }
}
