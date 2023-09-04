package com.amm.certs.infrastructure.framework.cloudresource.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class FeignExternalCloudResourceRepositoryClient {

    public String providerCloudResourceURI;

    private ObjectMapper objectMapper;

    public FeignExternalCloudResourceRepositoryClient(
        String providerCloudResourceURI,
        ObjectMapper objectMapper
    ) {
        this.providerCloudResourceURI = providerCloudResourceURI;
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<List<HttpCloudResource>> fetchAllCloudResources() {
        HttpClient client = HttpClient.newHttpClient();
        return client.sendAsync(httpRequestForAllCloudResource(), HttpResponse.BodyHandlers.ofString())
            .thenApply(response -> parseCloudResource.apply(response.body()));
    }

    private Function<String, List<HttpCloudResource>> parseCloudResource = (responseBody) -> {
        try {
            return objectMapper.readValue(
                responseBody,
                new com.fasterxml.jackson.core.type.TypeReference<ArrayList<HttpCloudResource>>() {}
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("FeignExternalCloudResourceRepository, error parsing response body", e);
        }
    };

    private HttpRequest httpRequestForAllCloudResource(){
        try {
            return HttpRequest.newBuilder()
                .uri(new URI(providerCloudResourceURI))
                .header("Content-Type", "application/json")
                .timeout(Duration.of(15, ChronoUnit.SECONDS))
                .GET()
                .build();
        } catch (URISyntaxException ex) {
            throw new RuntimeException("FeignExternalCloudResourceRepository, error calling to URI: %".formatted(providerCloudResourceURI), ex);
        }
    }
}
