package com.amm.certs.application.certification.retrieve;

import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.RetrieveCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RetrieveCertificationServiceTest {
    private JpaCertificationRepository repository = mock(JpaCertificationRepository.class);
    private RetrieveCertificationService retrieveCertificationService =
            new RetrieveCertificationService(repository);
    @Test
    void should_retrieve_a_certification() {
        mock_repository();
        RetrieveCertificationResponse response = retrieveCertificationService.execute(RetrieveCertificationFixture.ANY_RETRIEVE_CERTIFICATION_REQUEST);
        verify(repository, times(1)).findById(CertificationFixture.ANY_ID);
        assertThat(response).isEqualTo(RetrieveCertificationFixture.ANY_RETRIEVE_CERTIFICATION_RESPONSE);
    }

    private void mock_repository(){
        when(repository.findById(CertificationFixture.ANY_ID)).thenReturn(CertificationFixture.ANY_CERTIFICATION);
    }
}
