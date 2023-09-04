package com.amm.certs.infrastructure.framework.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.amm.certs.infrastructure.framework.common.json.CustomOffsetDateTimeDeserializer;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfig {

    private static final String DATE_TIME_PATTERN_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER =
            new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_ISO_8601));

    @Bean
    public ObjectMapper objectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .deserializerByType(OffsetDateTime.class, new CustomOffsetDateTimeDeserializer())
                .serializers(LOCAL_DATETIME_SERIALIZER)
                .serializationInclusion(JsonInclude.Include.NON_NULL).build();
    }
}
