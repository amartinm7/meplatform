package com.amm.certs.infrastructure.framework.certification.api.delete;

import com.amm.certs.application.certification.delete.DeleteCertificationRequest;
import com.amm.certs.application.certification.delete.DeleteCertificationResponse;
import com.amm.certs.application.certification.delete.DeleteCertificationService;
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
public class DeleteCertificationController {

    private DeleteCertificationService deleteCertificationService;

    public DeleteCertificationController(
            DeleteCertificationService deleteCertificationService
    ) {
        this.deleteCertificationService = deleteCertificationService;
    }

    @RequestMapping(
            value = "/api/v1/certification/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpResponse> execute(
            @PathVariable
            @JsonProperty("id")
            @NotNull(message = "id cannot be blank")
            UUID id
    ) {
        DeleteCertificationResponse response = deleteCertificationService.execute(transformToRequest.apply(id));
        return ResponseEntity.status(HttpStatus.OK).body(transformToHttp.apply(response));
    }

    private Function<UUID, DeleteCertificationRequest> transformToRequest = (id) ->
            new DeleteCertificationRequest(new Id(id));

    private Function<DeleteCertificationResponse, HttpResponse> transformToHttp = (response) ->
            HttpResponse.ofSuccess(new HttpDeleteCertificationData(response.id().value()));
}
