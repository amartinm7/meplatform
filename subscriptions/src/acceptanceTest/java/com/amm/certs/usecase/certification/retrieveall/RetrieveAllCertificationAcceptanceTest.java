package com.amm.certs.usecase.certification.retrieveall;

import com.amm.certs.SpringbootAcceptanceTest;
import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.fixtures.certification.RetrieveAllCertificationFixture;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RetrieveAllCertificationAcceptanceTest extends SpringbootAcceptanceTest {

    @Test
    void should_retrieve_all_certifications() throws JsonProcessingException {
        ResponseEntity<HttpResponse> response = restTemplate.exchange(
            "http://localhost:%d/%s?userId=%s&page=%s&resultsPerPage=%s"
                .formatted(port, URI, CertificationFixture.USER_ID, CertificationFixture.ANY_PAGE_NUMBER, CertificationFixture.ANY_RESULTS_PER_PAGE),
            HttpMethod.GET,
            httpEntityHandler.defaultHttpEntity(),
            HttpResponse.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(toJSON(response.getBody())).isEqualTo(toJSON(RetrieveAllCertificationFixture.ANY_HTTP_RETRIEVE_ALL_RESPONSE));
    }

    private static final String URI = "/api/v1/certification/";
}
