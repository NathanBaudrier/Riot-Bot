package fr.nathanpasdutout.riotapi;

import fr.nathanpasdutout.Main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {

    private final String url;

    /**
     * Constructor of the Request class.
     *
     * @param url Desired URL to access Riot's API.
     */
    Request(String url) {
        this.url = url;
    }

    /**
     *
     * @return Return the response of the request.
     */
    public HttpResponse<String> sendRequestToAPI() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(this.url))
                .header("X-Riot-Token", Main.getEnv().get("RIOT_TOKEN"))
                .header("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
                .GET()
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(IOException e) {
            System.err.println("Connection as failed: " + e.getMessage());
        } catch(InterruptedException e) {
            System.err.println("Connection was interrupted: " + e.getMessage());
        }

        return null;
    }

    public HttpResponse<String> sendSimpleRequest() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(this.url))
                .GET()
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(IOException e) {
            System.err.println("Connection as failed: " + e.getMessage());
        } catch(InterruptedException e) {
            System.err.println("Connection was interrupted: " + e.getMessage());
        }

        return null;
    }
}
