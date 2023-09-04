package com.amm.certs.infrastructure.certification.api.retrieve;

import com.amm.certs.application.certification.retrieve.RetrieveCertificationRequest;
import com.amm.certs.application.certification.retrieve.RetrieveCertificationService;
import com.amm.certs.fixtures.certification.RetrieveCertificationFixture;
import com.amm.certs.infrastructure.SpringbootContractTest;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RetrieveCertificationControllerContractTest extends SpringbootContractTest {

    @Autowired
    private RetrieveCertificationService retrieveCertificationService;

    @Test
    void should_retrieve_a_certification() throws Exception {
        //Given
        mock_service();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isOk());

        verify(retrieveCertificationService, times(1)).execute(any(RetrieveCertificationRequest.class));
    }

    @Test
    void should_validate_path_param_and_return_bad_request() throws Exception {
        mockMvc.perform(newHttpRequestBadRequestByPathParam())
            .andExpect(status().isBadRequest());

        verify(retrieveCertificationService, times(0)).execute(any());
    }

    private RequestBuilder newHttpRequestBadRequestByPathParam() {
        return MockMvcRequestBuilders.get("/api/v1/certification/NOT_VALID_ID")
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.get("/api/v1/certification/b476c4bb-46af-4151-8999-40689ddcd00e")
            .contentType("application/json");
    }

    private void mock_service() {
        when(retrieveCertificationService.execute(RetrieveCertificationFixture.ANY_RETRIEVE_CERTIFICATION_REQUEST))
            .thenReturn(RetrieveCertificationFixture.ANY_RETRIEVE_CERTIFICATION_RESPONSE);
    }
}
