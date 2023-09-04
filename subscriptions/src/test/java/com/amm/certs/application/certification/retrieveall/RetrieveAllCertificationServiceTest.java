package com.amm.certs.application.certification.retrieveall;

import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.RetrieveAllCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RetrieveAllCertificationServiceTest {
    private JpaCertificationRepository repository = mock(JpaCertificationRepository.class);
    private RetrieveAllCertificationService retrieveAllCertificationService =
        new RetrieveAllCertificationService(repository);
    @Test
    void should_retrieve_all_certifications_for_a_user() {
        mock_repository();
        RetrieveAllCertificationResponse response = retrieveAllCertificationService.execute(RetrieveAllCertificationFixture.ANY_RETRIEVE_ALL_CERTIFICATION_REQUEST);
        verify(repository, times(1)).findByUserId(CertificationFixture.ANY_USER_ID, CertificationFixture.ANY_PAGINATION);
        assertThat(response).isEqualTo(RetrieveAllCertificationFixture.ANY_RETRIEVE_ALL_CERTIFICATION_RESPONSE);
    }

    private void mock_repository(){
        when(repository.findByUserId(CertificationFixture.ANY_USER_ID, CertificationFixture.ANY_PAGINATION))
            .thenReturn(CertificationFixture.ANY_CERTIFICATION_PAGE);
    }
}
