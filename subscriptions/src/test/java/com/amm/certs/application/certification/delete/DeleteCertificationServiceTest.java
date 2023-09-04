package com.amm.certs.application.certification.delete;

import com.amm.certs.fixtures.certification.DeleteCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteCertificationServiceTest {
    private JpaCertificationRepository repository = mock(JpaCertificationRepository.class);
    private DeleteCertificationService deleteCertificationService =
            new DeleteCertificationService(repository);
    @Test
    void should_delete_a_certification() {
        DeleteCertificationResponse response = deleteCertificationService.execute(DeleteCertificationFixture.ANY_DELETE_CERTIFICATION_REQUEST);
        verify(repository, times(1)).deleteById(DeleteCertificationFixture.ANY_ID);
        assertThat(response).isEqualTo(DeleteCertificationFixture.ANY_DELETE_CERTIFICATION_RESPONSE);
    }
}
