package com.kemalbeyaz.ping.pong.server;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public String pingEndpoint() {
        return "pong";
    }

    @GetMapping(value = "/pong", produces = MediaType.APPLICATION_JSON_VALUE)
    public String pongEndpoint() {
        return "ping";
    }
}
