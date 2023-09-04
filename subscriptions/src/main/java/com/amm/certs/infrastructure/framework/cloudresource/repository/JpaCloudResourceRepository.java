package com.amm.certs.infrastructure.framework.cloudresource.repository;

import com.amm.certs.domain.cloudresource.model.CloudResource;
import com.amm.certs.domain.cloudresource.repository.CloudResourceRepository;
import java.util.function.Function;

public class JpaCloudResourceRepository implements CloudResourceRepository {

    private JpaCloudResourceRepositoryClient jpaCloudResourceRepositoryClient;

    public JpaCloudResourceRepository(JpaCloudResourceRepositoryClient jpaCloudResourceRepositoryClient) {
        this.jpaCloudResourceRepositoryClient = jpaCloudResourceRepositoryClient;
    }

    @Override
    public void save(CloudResource cloudResource) {
        jpaCloudResourceRepositoryClient.save(cloudResourceToJpa.apply(cloudResource));
    }

    private Function<CloudResource, JpaCloudResource> cloudResourceToJpa = (cloudResource) ->
        new JpaCloudResource(
            cloudResource.id().value(),
            cloudResource.name().value(),
            cloudResource.userId().value(),
            cloudResource.subscriptionId().value(),
            cloudResource.version().value(),
            cloudResource.createdAt().value(),
            cloudResource.modifiedAt().value()
        );
}
