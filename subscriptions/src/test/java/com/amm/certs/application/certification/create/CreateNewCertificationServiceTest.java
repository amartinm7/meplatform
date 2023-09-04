package com.amm.certs.application.certification.create;

import com.amm.certs.fixtures.certification.CreateCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateNewCertificationServiceTest {

    private JpaCertificationRepository repository = mock(JpaCertificationRepository.class);
    private CreateNewCertificationService service = new CreateNewCertificationService(repository);

    @Test
    void should_create_a_new_certification() {
        CreateNewCertificationResponse response = service.execute(CreateCertificationFixture.ANY_CREATE_NEW_CERTIFICATION_REQUEST);
        verify(repository, times(1)).save(CreateCertificationFixture.ANY_CERTIFICATION);
        assertThat(response).isEqualTo(CreateCertificationFixture.ANY_CREATE_NEW_CERTIFICATION_RESPONSE);
    }
}
