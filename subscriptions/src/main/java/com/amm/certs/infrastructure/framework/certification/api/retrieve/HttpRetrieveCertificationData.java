package com.amm.certs.infrastructure.framework.certification.api.retrieve;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import com.amm.certs.infrastructure.framework.common.json.CustomOffsetDateTimeDeserializer;
import java.time.OffsetDateTime;
import java.util.UUID;

public record HttpRetrieveCertificationData(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("certificationName")
        String certificationName,
        @JsonProperty("userId")
        UUID userId,
        @JsonProperty("subscriptionId")
        UUID subscriptionId,
        @JsonProperty("startDate")
        @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
        @JsonSerialize(using = OffsetDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        OffsetDateTime startDate,
        @JsonProperty("endDate")
        @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
        @JsonSerialize(using = OffsetDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        OffsetDateTime endDate,
        @JsonProperty("version")
        Long version,
        @JsonProperty("createdAt")
        @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
        @JsonSerialize(using = OffsetDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        OffsetDateTime createdAt,
        @JsonProperty("modifiedAt")
        @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
        @JsonSerialize(using = OffsetDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        OffsetDateTime modifiedAt
) {
}
