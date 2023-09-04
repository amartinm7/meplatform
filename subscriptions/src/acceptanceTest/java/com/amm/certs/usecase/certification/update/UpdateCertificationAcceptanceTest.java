package com.amm.certs.usecase.certification.update;

import com.amm.certs.SpringbootAcceptanceTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.UpdateCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.api.update.HttpUpdateCertificationRequest;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UpdateCertificationAcceptanceTest extends SpringbootAcceptanceTest {

    @Test
    void should_update_a_certification() throws JsonProcessingException {
        HttpEntity<HttpUpdateCertificationRequest> httpRequest =
                httpEntityHandler.getHttpEntity(UpdateCertificationFixture.ANY_HTTP_UPDATE_REQUEST);
        ResponseEntity<HttpResponse> response = restTemplate.exchange(
                "http://localhost:%d/%s/%s".formatted(port, URI, CertificationFixture.ID),
                HttpMethod.PUT,
                httpRequest,
                HttpResponse.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(toJSON(response.getBody())).isEqualTo(toJSON(UpdateCertificationFixture.ANY_HTTP_UPDATE_RESPONSE));
    }

    private static final String URI = "/api/v1/certification";
}
