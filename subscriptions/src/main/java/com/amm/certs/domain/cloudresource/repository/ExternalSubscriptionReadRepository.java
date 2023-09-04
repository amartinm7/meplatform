package com.amm.certs.domain.cloudresource.repository;

import com.amm.certs.domain.cloudresource.model.CloudResource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ExternalSubscriptionReadRepository {
    CompletableFuture<List<CloudResource>> fetchAllCloudResources();
}
