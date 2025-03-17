package fr.nathanpasdutout.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {

    private final String url;

    Request(String url) {
        this.url = url;
    }

    public HttpResponse<String> send() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(this.url))
                .header("X-Riot-Token", "RGAPI-a04f37e0-ef83-469e-b76f-b74e5aff1b39")
                .header("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
