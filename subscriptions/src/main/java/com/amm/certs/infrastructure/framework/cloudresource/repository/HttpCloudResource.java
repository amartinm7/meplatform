package com.amm.certs.infrastructure.framework.cloudresource.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public record HttpCloudResource(
    @JsonProperty("id")
    UUID id,
    @JsonProperty("resource_name")
    String resourceName,
    @JsonProperty("user_id")
    UUID userId,
    @JsonProperty("subscription_id")
    UUID subscriptionId
) {
}
