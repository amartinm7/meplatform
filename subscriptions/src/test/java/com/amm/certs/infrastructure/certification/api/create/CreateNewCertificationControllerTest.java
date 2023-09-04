package com.amm.certs.infrastructure.certification.api.create;

import com.amm.certs.application.certification.create.CreateNewCertificationService;
import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.CreateCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.api.create.CreateNewCertificationController;
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

class CreateNewCertificationControllerTest {
    private CreateNewCertificationService createNewCertificationService = mock(CreateNewCertificationService.class);

    private OffsetDateTimeHandler offsetDateTimeHandler = mock(OffsetDateTimeHandler.class);

    private CreateNewCertificationController createNewCertificationController =
            new CreateNewCertificationController(createNewCertificationService, offsetDateTimeHandler);

    @Test
    void should_create_a_new_certification() {
        mock_offsetDateTimeHandler_now();
        mock_service();
        ResponseEntity<HttpResponse> response = createNewCertificationController.execute(CreateCertificationFixture.ANY_HTTP_CREATE_REQUEST);
        verify(createNewCertificationService, times(1)).execute(CreateCertificationFixture.ANY_CREATE_NEW_CERTIFICATION_REQUEST);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(CreateCertificationFixture.ANY_HTTP_CREATE_RESPONSE);
    }

    private void mock_offsetDateTimeHandler_now() {
        when(offsetDateTimeHandler.now()).thenReturn(CertificationFixture.CREATED_AT);
    }

    private void mock_service() {
        when(createNewCertificationService.execute(CreateCertificationFixture.ANY_CREATE_NEW_CERTIFICATION_REQUEST))
                .thenReturn(CreateCertificationFixture.ANY_CREATE_NEW_CERTIFICATION_RESPONSE);
    }
}
