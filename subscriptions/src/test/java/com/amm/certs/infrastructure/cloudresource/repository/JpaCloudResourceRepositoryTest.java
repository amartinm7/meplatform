package com.amm.certs.infrastructure.cloudresource.repository;

import com.amm.certs.fixtures.cloudresource.CloudResourceFixture;
import com.amm.certs.infrastructure.framework.cloudresource.repository.JpaCloudResourceRepository;
import com.amm.certs.infrastructure.framework.cloudresource.repository.JpaCloudResourceRepositoryClient;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JpaCloudResourceRepositoryTest {
    private JpaCloudResourceRepositoryClient jpaClient = mock(JpaCloudResourceRepositoryClient.class);
    private JpaCloudResourceRepository repository = new JpaCloudResourceRepository(jpaClient);

    @Test
    void should_save_a_new_certification() {
        repository.save(CloudResourceFixture.ANY_CLOUD_RESOURCE);
        verify(jpaClient, times(1)).save(CloudResourceFixture.ANY_JPA_REQUEST);
    }
}
