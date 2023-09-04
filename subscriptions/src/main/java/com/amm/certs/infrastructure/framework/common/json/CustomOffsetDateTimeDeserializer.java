package com.amm.certs.infrastructure.framework.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import java.io.IOException;
import java.time.OffsetDateTime;

public class CustomOffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        return InstantDeserializer.OFFSET_DATE_TIME.deserialize(jsonParser, context);
    }
}
