package com.amm.certs.infrastructure.certification.api.retrieve;

import com.amm.certs.application.certification.retrieve.RetrieveCertificationService;
import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.RetrieveCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.api.retrieve.RetrieveCertificationController;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class RetrieveCertificationControllerTest {
    private RetrieveCertificationService retrieveCertificationService = mock(RetrieveCertificationService.class);
    private RetrieveCertificationController retrieveCertificationController =
            new RetrieveCertificationController(retrieveCertificationService);

    @Test
    void should_Retrieve_a_certification() {
        mock_service();
        ResponseEntity<HttpResponse> response = retrieveCertificationController.execute(CertificationFixture.ID);
        verify(retrieveCertificationService, times(1)).execute(RetrieveCertificationFixture.ANY_RETRIEVE_CERTIFICATION_REQUEST);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(RetrieveCertificationFixture.ANY_HTTP_RETRIEVE_RESPONSE);
    }

    private void mock_service() {
        when(retrieveCertificationService.execute(RetrieveCertificationFixture.ANY_RETRIEVE_CERTIFICATION_REQUEST))
                .thenReturn(RetrieveCertificationFixture.ANY_RETRIEVE_CERTIFICATION_RESPONSE);
    }
}
