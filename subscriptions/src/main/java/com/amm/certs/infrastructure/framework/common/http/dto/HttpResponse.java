package com.amm.certs.infrastructure.framework.common.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HttpResponse<T>(
        @JsonProperty("error") HttpError error,
        @JsonProperty("success") T success
) {
    public static <T> HttpResponse ofSuccess(T success) {
        return new HttpResponse<T>(HttpError.ofEmpty.get(), success);
    }
    public static HttpResponse ofError(HttpError error) {
        return new HttpResponse(error, new HttpEmptyData());
    }
}

record HttpEmptyData() {
}
