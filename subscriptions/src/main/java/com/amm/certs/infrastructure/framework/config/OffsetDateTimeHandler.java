package com.amm.certs.infrastructure.framework.config;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

public class OffsetDateTimeHandler {

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public OffsetDateTimeHandler() {
        //
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public OffsetDateTime now() {
        return OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public OffsetDateTime toOffsetDateTime(String sdate) {
        return OffsetDateTime.parse(sdate).truncatedTo(ChronoUnit.SECONDS);
    }

    public OffsetDateTime toOffsetDateTimeFrom(Timestamp timestamp) {
        return OffsetDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("UTC")).truncatedTo(ChronoUnit.SECONDS);
    }

    public String toStringUTC(OffsetDateTime anyDate) {
        return dtf.format(anyDate.truncatedTo(ChronoUnit.SECONDS));
    }
}
