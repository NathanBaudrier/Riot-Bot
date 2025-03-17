package fr.nathanpasdutout;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://euw1.api.riotgames.com/lol/platform/v3/champion-rotations"))
                .header("X-Riot-Token", Main.getEnv().get("RIOT_TOKEN"))
                .header("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Statut : " + response.statusCode());
        System.out.println("Corps : " + response.body());
    }
}