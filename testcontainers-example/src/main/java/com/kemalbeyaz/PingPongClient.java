package com.kemalbeyaz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class PingPongClient {

    private static final Logger LOG = LoggerFactory.getLogger(PingPongClient.class);

    private static final String PING_PATH = "/ping";
    private static final String PONG_PATH = "/pong";

    private final String apiUrl;

    public PingPongClient(final String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String requestPing() throws IOException, InterruptedException {
        LOG.info("I'm requesting a ping. Pong I will get the answer.");
        HttpResponse<String> httpResponse = sendRequest(PING_PATH);
        String body = httpResponse.body();
        LOG.info("To my ping request, its server replied: {}", body);
        return body;
    }

    public String requestPong() throws IOException, InterruptedException {
        LOG.info("I'm requesting a pong. Ping I will get the answer.");
        HttpResponse<String> httpResponse = sendRequest(PONG_PATH);
        String body = httpResponse.body();
        LOG.info("To my pong request, its server replied: {}", body);
        return body;
    }

    private HttpResponse<String> sendRequest(final String path) throws IOException, InterruptedException {
        var httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_2)
                .build();

        var request = HttpRequest.newBuilder(URI.create(apiUrl + path)).build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}