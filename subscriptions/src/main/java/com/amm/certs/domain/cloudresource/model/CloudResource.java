package com.amm.certs.domain.cloudresource.model;

import com.amm.certs.domain.common.model.CreatedAt;
import com.amm.certs.domain.common.model.ModifiedAt;
import com.amm.certs.domain.common.model.SubscriptionId;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.domain.common.model.Version;

public record CloudResource(
    CloudResourceId id,
    CloudResourceName name,
    UserId userId,
    SubscriptionId subscriptionId,
    Version version,
    CreatedAt createdAt,
    ModifiedAt modifiedAt
) {
}
