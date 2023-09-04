package com.amm.certs.infrastructure.framework.certification.api.delete;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public record HttpDeleteCertificationData(
        @JsonProperty("id")
        UUID id
) {
}
