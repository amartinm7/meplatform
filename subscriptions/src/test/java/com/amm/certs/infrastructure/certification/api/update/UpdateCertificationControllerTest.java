package com.amm.certs.infrastructure.certification.api.update;

import com.amm.certs.application.certification.update.UpdateCertificationService;
import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.UpdateCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.api.update.UpdateCertificationController;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import com.amm.certs.infrastructure.framework.config.OffsetDateTimeHandler;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class UpdateCertificationControllerTest {
    private UpdateCertificationService updateCertificationService = mock(UpdateCertificationService.class);

    private OffsetDateTimeHandler offsetDateTimeHandler = mock(OffsetDateTimeHandler.class);

    private UpdateCertificationController updateCertificationController =
            new UpdateCertificationController(updateCertificationService, offsetDateTimeHandler);

    @Test
    void should_update_a_certification() {
        mock_offsetDateTimeHandler_now();
        mock_service();
        ResponseEntity<HttpResponse> response = updateCertificationController.execute(
                CertificationFixture.ID,
                UpdateCertificationFixture.ANY_HTTP_UPDATE_REQUEST
        );
        verify(updateCertificationService, times(1)).execute(UpdateCertificationFixture.ANY_UPDATE_CERTIFICATION_REQUEST);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(UpdateCertificationFixture.ANY_HTTP_UPDATE_RESPONSE);
    }

    private void mock_offsetDateTimeHandler_now() {
        when(offsetDateTimeHandler.now()).thenReturn(CertificationFixture.CREATED_AT);
    }

    private void mock_service() {
        when(updateCertificationService.execute(UpdateCertificationFixture.ANY_UPDATE_CERTIFICATION_REQUEST))
                .thenReturn(UpdateCertificationFixture.ANY_UPDATE_CERTIFICATION_RESPONSE);
    }
}
