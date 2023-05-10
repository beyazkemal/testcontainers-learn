package com.kemalbeyaz;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PingPongClientTest {

    private static final String API_URL_FORMAT = "http://%s:%s";
    private static GenericContainer<?> container;
    private PingPongClient pingPongClient;

    @BeforeAll
    static void beforeAll() {
        container = new GenericContainer<>("ping-pong-server:0.0.1-SNAPSHOT")
                .withExposedPorts(3434)
                .waitingFor(Wait.forHttp("/").forStatusCode(404))
                .withStartupTimeout(Duration.ofSeconds(10));
        container.start();
    }

    @BeforeEach
    void setUp() {
        String apiUrl = String.format(API_URL_FORMAT, container.getHost(), container.getMappedPort(3434));
        pingPongClient = new PingPongClient(apiUrl);
    }

    @Test
    void shouldRequestPongMessage() throws IOException, InterruptedException {
        String requested = pingPongClient.requestPing();
        assertEquals("pong", requested);
    }

    @Test
    void shouldRequestPingMessage() throws IOException, InterruptedException {
        String requested = pingPongClient.requestPong();
        assertEquals("ping", requested);
    }

    @AfterAll
    static void afterAll() {
        container.stop();
    }
}