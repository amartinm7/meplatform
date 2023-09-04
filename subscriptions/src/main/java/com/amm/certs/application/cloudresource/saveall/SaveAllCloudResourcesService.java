package com.amm.certs.application.cloudresource.saveall;

import com.amm.certs.domain.cloudresource.model.CloudResource;
import com.amm.certs.domain.cloudresource.repository.CloudResourceRepository;
import com.amm.certs.domain.cloudresource.repository.ExternalCloudResourceRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SaveAllCloudResourcesService {

    private final ExternalCloudResourceRepository externalCloudResourceRepository;

    private CloudResourceRepository cloudResourceRepository;

    public SaveAllCloudResourcesService(
        ExternalCloudResourceRepository externalCloudResourceRepository,
        CloudResourceRepository cloudResourceRepository
    ) {
        this.externalCloudResourceRepository = externalCloudResourceRepository;
        this.cloudResourceRepository = cloudResourceRepository;
    }

    public void execute() {
        CompletableFuture<List<CloudResource>> future = externalCloudResourceRepository.fetchAllCloudResources();
        future.thenAccept(saveAll).join();
    }

    private final Consumer<List<CloudResource>> saveAll = (cloudResourceList) ->
        cloudResourceList.parallelStream().forEach(cloudResourceRepository::save);// TODO FIRE DOMAIN EVENT WHEN SAVES
}
