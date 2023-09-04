package com.amm.certs.infrastructure.certification.api.update;

import com.amm.certs.application.certification.update.UpdateCertificationRequest;
import com.amm.certs.application.certification.update.UpdateCertificationService;
import com.amm.certs.fixtures.certification.UpdateCertificationFixture;
import com.amm.certs.fixtures.common.CommonFixture;
import com.amm.certs.infrastructure.framework.config.OffsetDateTimeHandler;
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

public class UpdateCertificationControllerContractTest extends SpringbootContractTest {

    @Autowired
    private UpdateCertificationService updateCertificationService;

    @Autowired
    private OffsetDateTimeHandler offsetDateTimeHandler;

    @Test
    void should_update_a_certification() throws Exception {
        //Given
        mock_service();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isOk());

        verify(updateCertificationService, times(1)).execute(any(UpdateCertificationRequest.class));
    }

    @Test
    void should_validate_path_param_and_return_bad_request() throws Exception {
        mockMvc.perform(newHttpRequestBadRequestByPathParam())
            .andExpect(status().isBadRequest());

        verify(updateCertificationService, times(0)).execute(any());
    }


    @ParameterizedTest(name = "{index}: {0} {1}")
    @MethodSource("provider")
    void should_validate_request_body_and_return_bad_request(String testName, String context) throws Exception {
        mockMvc.perform(newHttpRequestBadRequest(context))
            .andExpect(status().isBadRequest());

        verify(updateCertificationService, times(0)).execute(any());
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("Null certificationName", "{ \"certificationName\":, \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty certificationName", "{ \"certificationName\": \"\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null userId", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": , \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty userId", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Not valid userId", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"NOT_VALID_ID\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null subscriptionId", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": , \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty subscriptionId", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Not valid subscriptionId", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"NOT_VALID_ID\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null startDate", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": , \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty startDate", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Not valid startDate", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"NOT_VALID_DATE\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null endDate", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": , \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty endDate", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Not valid endDate", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"NOT_VALID_DATE\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null status", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-08-18T00:00:00Z\", \"status\": }"),
            Arguments.of("Empty status", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\":  \"2020-08-18T00:00:00Z\", \"status\": \"\"}"),
            Arguments.of("Not valid status", "{ \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-08-18T00:00:00Z\", \"status\": \"NOT_VALID_STATUS\"}")
        );
    }

    private RequestBuilder newHttpRequestBadRequestByPathParam() {
        return MockMvcRequestBuilders.put("/api/v1/certification/NOT_VALID_ID")
            .content("{\"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}")
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestBadRequest(String content) {
        return MockMvcRequestBuilders.put("/api/v1/certification/b476c4bb-46af-4151-8999-40689ddcd00e")
            .content(content)
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.put("/api/v1/certification/b476c4bb-46af-4151-8999-40689ddcd00e")
            .content("{\"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}")
            .contentType("application/json");
    }

    private void mock_service() {
        when(updateCertificationService.execute(UpdateCertificationFixture.ANY_UPDATE_CERTIFICATION_REQUEST))
            .thenReturn(UpdateCertificationFixture.ANY_UPDATE_CERTIFICATION_RESPONSE);
        when(offsetDateTimeHandler.now()).thenReturn(CommonFixture.CREATED_AT);
    }
}
