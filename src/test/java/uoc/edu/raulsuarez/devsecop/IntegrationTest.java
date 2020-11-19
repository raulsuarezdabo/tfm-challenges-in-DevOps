package uoc.edu.raulsuarez.devsecop;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import uoc.edu.raulsuarez.devsecop.dto.Operations;

import java.util.Collections;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    private static final String HOST = "http://localhost";


    @LocalServerPort private int port;

    @Autowired
    WebTestClient client;

    @Test
    public void pathToHome() {
        client.get().uri( HOST + ":" + port)
                .exchange().expectStatus().isOk();
    }

    @Test
    public void addTestOk() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.put("items[0]", Collections.singletonList("1"));
        data.put("items[1]", Collections.singletonList("1"));
        data.put("operation", Collections.singletonList(Operations.ADD));
        client.post().uri(HOST + ":" + port + "/index").body(BodyInserters.fromFormData(data))
                .exchange().expectStatus().isOk();
    }

    @Test
    public void subTestOk() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.put("items[0]", Collections.singletonList("1"));
        data.put("items[1]", Collections.singletonList("1"));
        data.put("operation", Collections.singletonList(Operations.SUB));
        client.post().uri(HOST + ":" + port + "/index").body(BodyInserters.fromFormData(data))
                .exchange().expectStatus().isOk();
    }

    @Test
    public void mulTestOk() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.put("items[0]", Collections.singletonList("1"));
        data.put("items[1]", Collections.singletonList("1"));
        data.put("operation", Collections.singletonList(Operations.MUL));
        client.post().uri(HOST + ":" + port + "/index").body(BodyInserters.fromFormData(data))
                .exchange().expectStatus().isOk();
    }

    @Test
    public void divTestOk() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.put("items[0]", Collections.singletonList("1"));
        data.put("items[1]", Collections.singletonList("1"));
        data.put("operation", Collections.singletonList(Operations.DIV));
        client.post().uri(HOST + ":" + port + "/index").body(BodyInserters.fromFormData(data))
                .exchange().expectStatus().isOk();
    }

    @Test
    public void divTestKo() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.put("items[0]", Collections.singletonList("1"));
        data.put("items[1]", Collections.singletonList("0"));
        data.put("operation", Collections.singletonList(Operations.DIV));
        client.post().uri(HOST + ":" + port + "/index").body(BodyInserters.fromFormData(data))
                .exchange().expectStatus().is5xxServerError();
    }
}
