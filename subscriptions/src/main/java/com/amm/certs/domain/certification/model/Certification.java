package com.amm.certs.domain.certification.model;

import com.amm.certs.domain.common.model.CreatedAt;
import com.amm.certs.domain.common.model.ModifiedAt;
import com.amm.certs.domain.common.model.SubscriptionId;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.domain.common.model.Version;

public record Certification(
        Id id,
        CertificationName certificationName,
        UserId userId,
        SubscriptionId subscriptionId,
        StartDate startDate,
        EndDate endDate,
        Version version,
        Status status,
        CreatedAt createdAt,
        ModifiedAt modifiedAt
) {
}
