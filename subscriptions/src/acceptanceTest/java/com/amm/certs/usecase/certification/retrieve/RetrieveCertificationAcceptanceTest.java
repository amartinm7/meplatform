package com.amm.certs.usecase.certification.retrieve;

import com.amm.certs.SpringbootAcceptanceTest;
import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.RetrieveCertificationFixture;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RetrieveCertificationAcceptanceTest extends SpringbootAcceptanceTest {

    @Test
    void should_retrieve_a_certification() throws JsonProcessingException {
        ResponseEntity<HttpResponse> response = restTemplate.exchange(
                "http://localhost:%d/%s/%s".formatted(port, URI, CertificationFixture.ID),
                HttpMethod.GET,
                httpEntityHandler.defaultHttpEntity(),
                HttpResponse.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(toJSON(response.getBody())).isEqualTo(toJSON(RetrieveCertificationFixture.ANY_HTTP_RETRIEVE_RESPONSE));
    }

    private static final String URI = "/api/v1/certification";
}
