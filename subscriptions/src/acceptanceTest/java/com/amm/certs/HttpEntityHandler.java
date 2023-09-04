package com.amm.certs;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpEntityHandler {
    public HttpEntity defaultHttpEntity() {
        return new HttpEntity<>(getRequestHeaders());
    }

    public <T> HttpEntity<T> getHttpEntity(T request) {
        return new HttpEntity<T>(request, getRequestHeaders());
    }

    private HttpHeaders getRequestHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return requestHeaders;
    }
}
