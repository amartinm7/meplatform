package com.amm.certs.infrastructure.framework.certification.api.retrieveall;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record HttpRetrieveAllCertificationRequest(
        @JsonProperty("userId")
        @NotNull(message = "userId cannot be null")
        UUID userId,
        @JsonProperty("page")
        @NotNull(message = "pageNumber cannot be null")
        @Max(value = 999999999)
        Integer page,
        @JsonProperty("resultsPerPage")
        @NotNull(message = "resultsPerPage cannot be null")
        @Max(value = 999999999)
        Integer resultsPerPage
) {
}
