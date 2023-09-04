package com.amm.certs.application.cloudresource.saveall;

import com.amm.certs.domain.cloudresource.model.CloudResource;
import com.amm.certs.fixtures.cloudresource.CloudResourceFixture;
import com.amm.certs.infrastructure.framework.cloudresource.repository.FeignExternalCloudResourceRepository;
import com.amm.certs.infrastructure.framework.cloudresource.repository.JpaCloudResourceRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaveAllCloudResourcesServiceTest {
    private FeignExternalCloudResourceRepository feignExternalCloudResourceRepository =
        mock(FeignExternalCloudResourceRepository.class);

    private JpaCloudResourceRepository jpaCloudResourceRepository = mock(JpaCloudResourceRepository.class);

    private SaveAllCloudResourcesService saveAllCloudResourcesService =
        new SaveAllCloudResourcesService(feignExternalCloudResourceRepository, jpaCloudResourceRepository);

    @Test
    void should_retrieve_and_save_all_certifications() {
        mock_repository();
        saveAllCloudResourcesService.execute();
        verify(feignExternalCloudResourceRepository, times(1)).fetchAllCloudResources();
        verify(jpaCloudResourceRepository, times(1)).save(CloudResourceFixture.ANY_CLOUD_RESOURCE);
    }

    private void mock_repository() {
        Supplier<List<CloudResource>> supplier = () -> CloudResourceFixture.ANY_CLOUD_RESOURCE_LIST;
        CompletableFuture.supplyAsync(supplier);
        when(feignExternalCloudResourceRepository.fetchAllCloudResources())
            .thenReturn(CompletableFuture.supplyAsync(supplier));
        doNothing().when(jpaCloudResourceRepository).save(CloudResourceFixture.ANY_CLOUD_RESOURCE);
    }
}
