package com.amm.certs.infrastructure.certification.api.create;

import com.amm.certs.application.certification.create.CreateNewCertificationRequest;
import com.amm.certs.application.certification.create.CreateNewCertificationService;
import com.amm.certs.fixtures.certification.CreateCertificationFixture;
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

public class CreateNewCertificationControllerContractTest extends SpringbootContractTest {

    @Autowired
    private CreateNewCertificationService createNewCertificationService;

    @Autowired
    private OffsetDateTimeHandler offsetDateTimeHandler;

    @Test
    void should_create_a_certification() throws Exception {
        //Given
        mock_service();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isCreated());

        verify(createNewCertificationService, times(1)).execute(any(CreateNewCertificationRequest.class));
    }

    @ParameterizedTest(name = "{index}: {0} {1}")
    @MethodSource("provider")
    void should_validate_request_body_and_return_bad_request(String testName, String context) throws Exception {
        mockMvc.perform(newHttpRequestBadRequest(context))
            .andExpect(status().isBadRequest());

        verify(createNewCertificationService, times(0)).execute(any());
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("Null id", "{\"id\": , \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty id", "{\"id\": \"\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Not valid id", "{\"id\": \"NOT_VALID_ID\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null certificationName", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\":, \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty certificationName", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null userId", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": , \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty userId", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Not valid userId", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"NOT_VALID_ID\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null subscriptionId", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": , \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty subscriptionId", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Not valid subscriptionId", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"NOT_VALID_ID\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null startDate", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": , \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty startDate", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Not valid startDate", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"NOT_VALID_DATE\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null endDate", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \", \"status\": \"ACTIVE\"}"),
            Arguments.of("Empty endDate", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"\", \"status\": \"ACTIVE\"}"),
            Arguments.of("Null status", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-08-18T00:00:00Z\", \"status\": }"),
            Arguments.of("Empty status", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\":\"2020-08-18T00:00:00Z\", \"status\": \"\" }"),
            Arguments.of("Not valid status", "{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-08-18T00:00:00Z\", \"status\": \"NOT_VALID_STATUS\"}")
        );
    }

    private RequestBuilder newHttpRequestBadRequest(String content) {
        return MockMvcRequestBuilders.post("/api/v1/certification")
            .content(content)
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.post("/api/v1/certification")
            .content("{\"id\": \"b476c4bb-46af-4151-8999-40689ddcd00e\", \"certificationName\": \"CERTIFICATION_NAME\", \"userId\": \"ccb58458-8d3d-4191-b09c-7eb24994e84c\", \"subscriptionId\": \"fd7cb91e-e95e-4603-9bfc-402ff5a495ec\", \"startDate\": \"2020-08-18T00:00:00Z\", \"endDate\": \"2020-12-18T00:00:00Z\", \"status\": \"ACTIVE\"}")
            .contentType("application/json");
    }

    private void mock_service() {
        when(createNewCertificationService.execute(CreateCertificationFixture.ANY_CREATE_NEW_CERTIFICATION_REQUEST))
            .thenReturn(CreateCertificationFixture.ANY_CREATE_NEW_CERTIFICATION_RESPONSE);
        when(offsetDateTimeHandler.now()).thenReturn(CommonFixture.CREATED_AT);
    }
}
