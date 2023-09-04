package com.amm.certs.fixtures.cloudresource;

import com.amm.certs.domain.cloudresource.model.CloudResource;
import com.amm.certs.domain.cloudresource.model.CloudResourceId;
import com.amm.certs.domain.cloudresource.model.CloudResourceName;
import com.amm.certs.domain.common.model.CreatedAt;
import com.amm.certs.domain.common.model.ModifiedAt;
import com.amm.certs.domain.common.model.SubscriptionId;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.domain.common.model.Version;
import com.amm.certs.fixtures.common.CommonFixture;
import com.amm.certs.infrastructure.framework.cloudresource.repository.JpaCloudResource;
import java.util.List;
import java.util.UUID;

public class CloudResourceFixture extends CommonFixture {

    public static final UUID ID = UUID.fromString("a7d2a603-36ee-400c-9786-4c3cd0ffedc7");
    public static final String RESOURCE_NAME = "RESOURCE_NAME";
    public static final CloudResourceId ANY_ID = new CloudResourceId(ID);

    public static final CloudResource ANY_CLOUD_RESOURCE =
        new CloudResource(
            new CloudResourceId(ID),
            new CloudResourceName(RESOURCE_NAME),
            new UserId(USER_ID),
            new SubscriptionId(SUBSCRIPTION_ID),
            new Version(VERSION),
            new CreatedAt(CREATED_AT),
            new ModifiedAt(MODIFIED_AT)
        );

    public static final List<CloudResource> ANY_CLOUD_RESOURCE_LIST = List.of(ANY_CLOUD_RESOURCE);

    public static final JpaCloudResource ANY_JPA_REQUEST =
        new JpaCloudResource(
            ID,
            RESOURCE_NAME,
            USER_ID,
            SUBSCRIPTION_ID,
            VERSION,
            CREATED_AT,
            MODIFIED_AT
        );
    public static final List<JpaCloudResource> ANY_JPA_CLOUD_RESOURCE_LIST = List.of(ANY_JPA_REQUEST);
}
