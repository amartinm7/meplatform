package com.amm.certs.infrastructure.certification.api.retrieveall;

import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationRequest;
import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationService;
import com.amm.certs.fixtures.certification.RetrieveAllCertificationFixture;
import com.amm.certs.infrastructure.SpringbootContractTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RetrieveAllCertificationControllerContractTest extends SpringbootContractTest {

    @Autowired
    private RetrieveAllCertificationService retrieveAllCertificationService;

    @Test
    void should_retrieve_all_certifications() throws Exception {
        //Given
        mock_service();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isOk());

        verify(retrieveAllCertificationService, times(1)).execute(any(RetrieveAllCertificationRequest.class));
    }

    @ParameterizedTest(name = "{index}: {0} {1}")
    @MethodSource("provider")
    void should_validate_request_body_and_return_bad_request(String testName, String uri) throws Exception {
        mockMvc.perform(newHttpRequestBadRequest(uri))
            .andExpect(status().isBadRequest());

        verify(retrieveAllCertificationService, times(0)).execute(any());
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("Null userId", "/api/v1/certification/?page=0&resultsPerPage=10"),
            Arguments.of("Not valid ", "/api/v1/certification/?userId=&page=0&resultsPerPage=10"),
            Arguments.of("Null page", "/api/v1/certification/?userId=ccb58458-8d3d-4191-b09c-7eb24994e84c&&resultsPerPage=10"),
            Arguments.of("Empty page", "/api/v1/certification/?userId=ccb58458-8d3d-4191-b09c-7eb24994e84c&page=&resultsPerPage=10"),
            Arguments.of("Null resultsPerPage", "/api/v1/certification/?userId=ccb58458-8d3d-4191-b09c-7eb24994e84c&page=0"),
            Arguments.of("Empty resultsPerPage", "/api/v1/certification/?userId=ccb58458-8d3d-4191-b09c-7eb24994e84c&page=0&resultsPerPage=")
        );
    }

    private RequestBuilder newHttpRequestBadRequest(String uri) {
        return MockMvcRequestBuilders.get(uri)
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.get("/api/v1/certification/?userId=ccb58458-8d3d-4191-b09c-7eb24994e84c&page=0&resultsPerPage=10")
            .contentType("application/json");
    }

    private void mock_service() {
        when(retrieveAllCertificationService.execute(RetrieveAllCertificationFixture.ANY_RETRIEVE_ALL_CERTIFICATION_REQUEST))
            .thenReturn(RetrieveAllCertificationFixture.ANY_RETRIEVE_ALL_CERTIFICATION_RESPONSE);
    }
}
