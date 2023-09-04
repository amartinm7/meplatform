package com.amm.certs.fixtures.common;

import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.infrastructure.framework.config.OffsetDateTimeHandler;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CommonFixture {
    public static final String ANY_AUDIT_DATE_STR = "2020-08-18T00:00:00Z";

    public static final UUID USER_ID = UUID.fromString("ccb58458-8d3d-4191-b09c-7eb24994e84c");
    public static final UserId ANY_USER_ID = new UserId(USER_ID);
    public static final UUID SUBSCRIPTION_ID = UUID.fromString("fd7cb91e-e95e-4603-9bfc-402ff5a495ec");
    public static final Long VERSION = 1L;
    public static final OffsetDateTime CREATED_AT = new OffsetDateTimeHandler().toOffsetDateTime(ANY_AUDIT_DATE_STR);
    public static final OffsetDateTime MODIFIED_AT = new OffsetDateTimeHandler().toOffsetDateTime(ANY_AUDIT_DATE_STR);
}
