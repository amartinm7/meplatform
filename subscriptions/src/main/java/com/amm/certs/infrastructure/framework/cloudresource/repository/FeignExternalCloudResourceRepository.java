package com.amm.certs.infrastructure.framework.cloudresource.repository;

import com.amm.certs.domain.cloudresource.model.CloudResource;
import com.amm.certs.domain.cloudresource.model.CloudResourceId;
import com.amm.certs.domain.cloudresource.model.CloudResourceName;
import com.amm.certs.domain.cloudresource.repository.ExternalCloudResourceRepository;
import com.amm.certs.domain.common.model.CreatedAt;
import com.amm.certs.domain.common.model.ModifiedAt;
import com.amm.certs.domain.common.model.SubscriptionId;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.domain.common.model.Version;
import com.amm.certs.infrastructure.framework.config.OffsetDateTimeHandler;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class FeignExternalCloudResourceRepository implements ExternalCloudResourceRepository {

    private FeignExternalCloudResourceRepositoryClient feignExternalCloudResourceRepositoryClient;

    private OffsetDateTimeHandler offsetDateTimeHandler;

    public FeignExternalCloudResourceRepository(
        FeignExternalCloudResourceRepositoryClient feignExternalCloudResourceRepositoryClient,
        OffsetDateTimeHandler offsetDateTimeHandler
    ) {
        this.feignExternalCloudResourceRepositoryClient = feignExternalCloudResourceRepositoryClient;
        this.offsetDateTimeHandler = offsetDateTimeHandler;
    }

    @Override
    public CompletableFuture<List<CloudResource>> fetchAllCloudResources() {
        return feignExternalCloudResourceRepositoryClient.fetchAllCloudResources().thenApply(httpToDomainList);
    }

    private Function<HttpCloudResource,CloudResource> httpToDomain = (httpCloudResource) ->
        new CloudResource(
            new CloudResourceId(httpCloudResource.id()),
            new CloudResourceName(httpCloudResource.resourceName()),
            new UserId(httpCloudResource.userId()),
            new SubscriptionId(httpCloudResource.subscriptionId()),
            new Version(1L),
            new CreatedAt(offsetDateTimeHandler.now()),
            new ModifiedAt(offsetDateTimeHandler.now())
        );
    private Function<List<HttpCloudResource>,List<CloudResource>> httpToDomainList = (httpCloudResourceList) ->
        httpCloudResourceList.stream().map(httpToDomain).toList();


}
