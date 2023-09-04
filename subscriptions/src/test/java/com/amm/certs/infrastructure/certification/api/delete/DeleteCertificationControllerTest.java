package com.amm.certs.infrastructure.certification.api.delete;

import com.amm.certs.application.certification.delete.DeleteCertificationService;
import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.DeleteCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.api.delete.DeleteCertificationController;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class DeleteCertificationControllerTest {
    private DeleteCertificationService deleteCertificationService = mock(DeleteCertificationService.class);
    private DeleteCertificationController deleteCertificationController =
            new DeleteCertificationController(deleteCertificationService);

    @Test
    void should_delete_a_certification() {
        mock_service();
        ResponseEntity<HttpResponse> response = deleteCertificationController.execute(CertificationFixture.ID);
        verify(deleteCertificationService, times(1)).execute(DeleteCertificationFixture.ANY_DELETE_CERTIFICATION_REQUEST);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(DeleteCertificationFixture.ANY_HTTP_DELETE_RESPONSE);
    }

    private void mock_service() {
        when(deleteCertificationService.execute(DeleteCertificationFixture.ANY_DELETE_CERTIFICATION_REQUEST))
                .thenReturn(DeleteCertificationFixture.ANY_DELETE_CERTIFICATION_RESPONSE);
    }
}
