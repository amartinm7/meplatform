package com.amm.certs.infrastructure.framework.certification.api.create;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HttpNewCertificationData(
        @JsonProperty("uri")
        String uri
) {
}
