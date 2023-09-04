package com.amm.certs.infrastructure.certification.api.retrieveall;

import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationService;
import com.amm.certs.fixtures.certification.RetrieveAllCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.api.retrieveall.RetrieveAllCertificationController;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class RetrieveAllCertificationControllerTest {
    private RetrieveAllCertificationService retrieveAllCertificationService = mock(RetrieveAllCertificationService.class);
    private RetrieveAllCertificationController retrieveAllCertificationController =
        new RetrieveAllCertificationController(retrieveAllCertificationService);

    @Test
    void should_retrieve_all_certifications_for_a_user() {
        mock_service();
        ResponseEntity<HttpResponse> response = retrieveAllCertificationController.execute(RetrieveAllCertificationFixture.ANY_HTTP_RETRIEVE_ALL_REQUEST);
        verify(retrieveAllCertificationService, times(1)).execute(RetrieveAllCertificationFixture.ANY_RETRIEVE_ALL_CERTIFICATION_REQUEST);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(RetrieveAllCertificationFixture.ANY_HTTP_RETRIEVE_ALL_RESPONSE);
    }

    private void mock_service() {
        when(retrieveAllCertificationService.execute(RetrieveAllCertificationFixture.ANY_RETRIEVE_ALL_CERTIFICATION_REQUEST))
            .thenReturn(RetrieveAllCertificationFixture.ANY_RETRIEVE_ALL_CERTIFICATION_RESPONSE);
    }
}
