package com.amm.certs.infrastructure.framework.certification.api.retrieve;

import com.amm.certs.application.certification.retrieve.RetrieveCertificationRequest;
import com.amm.certs.application.certification.retrieve.RetrieveCertificationResponse;
import com.amm.certs.application.certification.retrieve.RetrieveCertificationService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetrieveCertificationController {

    private RetrieveCertificationService retrieveCertificationService;

    public RetrieveCertificationController(
            RetrieveCertificationService retrieveCertificationService
    ) {
        this.retrieveCertificationService = retrieveCertificationService;
    }

    @RequestMapping(
            value = "/api/v1/certification/{id}",
            method = RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpResponse> execute(
            @PathVariable
            @JsonProperty("id")
            @NotNull(message = "id cannot be blank")
            UUID id
    ) {
        RetrieveCertificationResponse response = retrieveCertificationService.execute(transformToRequest.apply(id));
        return ResponseEntity.status(HttpStatus.OK).body(transformToHttp.apply(response));
    }

    private Function<UUID, RetrieveCertificationRequest> transformToRequest = (id) ->
            new RetrieveCertificationRequest(new Id(id));

    private Function<RetrieveCertificationResponse, HttpResponse> transformToHttp = (response) ->
            HttpResponse.ofSuccess(
                    new HttpRetrieveCertificationData(
                        response.certification().id().value(),
                        response.certification().certificationName().value(),
                        response.certification().userId().value(),
                        response.certification().subscriptionId().value(),
                        response.certification().startDate().value(),
                        response.certification().endDate().value(),
                        response.certification().version().value(),
                        response.certification().createdAt().value(),
                        response.certification().modifiedAt().value()
                    )
            );
}
