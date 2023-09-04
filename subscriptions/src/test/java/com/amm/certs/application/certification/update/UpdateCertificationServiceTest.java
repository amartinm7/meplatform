package com.amm.certs.application.certification.update;

import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.UpdateCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UpdateCertificationServiceTest {
    private JpaCertificationRepository repository = mock(JpaCertificationRepository.class);
    private UpdateCertificationService service = new UpdateCertificationService(repository);

    @Test
    void should_update_a_new_certification() {
        UpdateCertificationResponse response = service.execute(UpdateCertificationFixture.ANY_UPDATE_CERTIFICATION_REQUEST);
        verify(repository, times(1)).update(CertificationFixture.ANY_CERTIFICATION);
        assertThat(response).isEqualTo(UpdateCertificationFixture.ANY_UPDATE_CERTIFICATION_RESPONSE);
    }
}
