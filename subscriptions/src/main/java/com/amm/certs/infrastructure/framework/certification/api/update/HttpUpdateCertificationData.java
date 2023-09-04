package com.amm.certs.infrastructure.framework.certification.api.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public record HttpUpdateCertificationData(
        @JsonProperty("id")
        UUID id
) {
}
