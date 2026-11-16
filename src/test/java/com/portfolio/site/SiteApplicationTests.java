package com.portfolio.site;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "spring.datasource.url=jdbc:h2:mem:portfolio-teste"
)
class SiteApplicationTests {

    @LocalServerPort
    private int porta;

    @Test
    void redirecionaParaLoginSemMudarOEnderecoDoSite() throws Exception {
        HttpRequest pedido = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + porta + "/admin.html"))
            .header("Accept", "text/html")
            .GET()
            .build();

        try (HttpClient cliente = HttpClient.newHttpClient()) {
            HttpResponse<Void> resposta = cliente.send(pedido, HttpResponse.BodyHandlers.discarding());

            assertEquals(302, resposta.statusCode());
            assertEquals("/login", resposta.headers().firstValue("Location").orElseThrow());
        }
    }
}
