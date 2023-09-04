package com.amm.certs.infrastructure.framework.certification.api.retrieveall;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HttpPaginationData(
        @JsonProperty("pageNumber")
        Integer page,
        @JsonProperty("resultsPerPage")
        Integer resultsPerPage,
        @JsonProperty("totalPages")
        Integer totalPages,
        @JsonProperty("totalElements")
        Long totalElements
) {
}
