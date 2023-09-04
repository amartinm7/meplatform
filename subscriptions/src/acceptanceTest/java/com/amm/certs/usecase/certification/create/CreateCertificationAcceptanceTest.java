package com.amm.certs.usecase.certification.create;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.amm.certs.SpringbootAcceptanceTest;
import com.amm.certs.fixtures.certification.CreateCertificationFixture;
import com.amm.certs.infrastructure.framework.certification.api.create.HttpNewCertificationRequest;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreateCertificationAcceptanceTest extends SpringbootAcceptanceTest {

    @Test
    void should_create_a_certification() throws JsonProcessingException {
        HttpEntity<HttpNewCertificationRequest> httpRequest =
                httpEntityHandler.getHttpEntity(CreateCertificationFixture.ANY_HTTP_CREATE_REQUEST);
        ResponseEntity<HttpResponse> response = restTemplate.exchange(
                "http://localhost:%d/%s".formatted(port, URI),
                HttpMethod.POST,
                httpRequest,
                HttpResponse.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(toJSON(response.getBody())).isEqualTo(toJSON(CreateCertificationFixture.ANY_HTTP_CREATE_RESPONSE));
    }

    private static final String URI = "/api/v1/certification";
}
