package com.algaworks.algafood.client;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.util.Objects.nonNull;

public class JsonTestClient {

    @Autowired
    private HttpClient httpClient = HttpClient.newHttpClient();
    @Autowired
    private String URI_HTTP = "https://jsonplaceholder.typicode.com/todos/1";

    public void sendRequisition() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URI_HTTP))
                .GET()
                .build();

        HttpResponse<String> send = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        System.out.println(send.toString());

        if(nonNull(send)){
            System.out.println(nonNull(send) ? "naoNulo" : "Nulo");
        } else {
            System.out.println("Nulo!");
        }
    }

}

