package com.amm.certs.infrastructure.certification.api.delete;

import com.amm.certs.application.certification.delete.DeleteCertificationRequest;
import com.amm.certs.application.certification.delete.DeleteCertificationService;
import com.amm.certs.fixtures.certification.DeleteCertificationFixture;
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

public class DeleteCertificationControllerContractTest extends SpringbootContractTest {

    @Autowired
    private DeleteCertificationService deleteCertificationService;

    @Test
    void should_delete_a_certification() throws Exception {
        //Given
        mock_service();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isOk());

        verify(deleteCertificationService, times(1)).execute(any(DeleteCertificationRequest.class));
    }

    @Test
    void should_validate_path_param_and_return_bad_request() throws Exception {
        mockMvc.perform(newHttpRequestBadRequestByPathParam())
            .andExpect(status().isBadRequest());

        verify(deleteCertificationService, times(0)).execute(any());
    }

    private RequestBuilder newHttpRequestBadRequestByPathParam() {
        return MockMvcRequestBuilders.delete("/api/v1/certification/NOT_VALID_ID")
            .content("{\"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\"}")
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.delete("/api/v1/certification/b476c4bb-46af-4151-8999-40689ddcd00e")
            .content("{\"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\"}")
            .contentType("application/json");
    }

    private void mock_service() {
        when(deleteCertificationService.execute(DeleteCertificationFixture.ANY_DELETE_CERTIFICATION_REQUEST))
            .thenReturn(DeleteCertificationFixture.ANY_DELETE_CERTIFICATION_RESPONSE);
    }
}
