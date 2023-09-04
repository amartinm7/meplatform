package com.amm.certs.infrastructure.framework.common.http.dto;

import java.util.function.Supplier;

public record HttpError(Integer code, String message) {
    public static Supplier<HttpError> ofEmpty = () -> new HttpError(0, "");
}
