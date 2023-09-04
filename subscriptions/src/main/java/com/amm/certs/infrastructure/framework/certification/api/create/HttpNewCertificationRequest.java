package com.amm.certs.infrastructure.framework.certification.api.create;

import com.amm.certs.domain.certification.model.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import com.amm.certs.infrastructure.framework.common.json.CustomOffsetDateTimeDeserializer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

public record HttpNewCertificationRequest(
        @JsonProperty("id")
        @NotNull(message = "id cannot be null")
        UUID id,
        @JsonProperty("certificationName")
        @NotNull(message = "certificationName cannot be null")
        @NotEmpty(message = "certificationName cannot be empty")
        String certificationName,
        @JsonProperty("userId")
        @NotNull(message = "userId cannot be null")
        UUID userId,
        @JsonProperty("subscriptionId")
        @NotNull(message = "subscriptionId cannot be null")
        UUID subscriptionId,
        @JsonProperty("startDate")
        @NotNull(message = "certificationName cannot be null")
        @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
        @JsonSerialize(using = OffsetDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        OffsetDateTime startDate,
        @JsonProperty("endDate")
        @NotNull(message = "certificationName cannot be null")
        @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
        @JsonSerialize(using = OffsetDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        OffsetDateTime endDate,
        @JsonProperty("status")
        @NotNull(message = "status cannot be null")
        StatusEnum status
) {
}

