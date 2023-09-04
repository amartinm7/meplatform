package com.amm.certs.infrastructure.framework.certification.api.update;

import com.amm.certs.application.certification.update.UpdateCertificationRequest;
import com.amm.certs.application.certification.update.UpdateCertificationResponse;
import com.amm.certs.application.certification.update.UpdateCertificationService;
import com.amm.certs.infrastructure.framework.config.OffsetDateTimeHandler;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.amm.certs.domain.certification.model.Certification;
import com.amm.certs.domain.certification.model.Status;
import com.amm.certs.domain.common.model.CreatedAt;
import com.amm.certs.domain.certification.model.EndDate;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.domain.common.model.ModifiedAt;
import com.amm.certs.domain.certification.model.CertificationName;
import com.amm.certs.domain.certification.model.StartDate;
import com.amm.certs.domain.common.model.SubscriptionId;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.domain.common.model.Version;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateCertificationController {

    private UpdateCertificationService updateCertificationService;

    private OffsetDateTimeHandler offsetDateTimeHandler;

    public UpdateCertificationController(
            UpdateCertificationService updateCertificationService,
            OffsetDateTimeHandler offsetDateTimeHandler
    ) {
        this.updateCertificationService = updateCertificationService;
        this.offsetDateTimeHandler = offsetDateTimeHandler;
    }

    @RequestMapping(
            value = "/api/v1/certification/{id}",
            method = RequestMethod.PUT
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpResponse> execute(
            @PathVariable
            @JsonProperty("id")
            @NotNull(message = "id cannot be null")
            UUID id,
            @RequestBody
            @Valid
            HttpUpdateCertificationRequest httpUpdateCertificationRequest
    ) {
        UpdateCertificationResponse response = updateCertificationService.execute(transformToRequest.apply(id, httpUpdateCertificationRequest));
        return ResponseEntity.status(HttpStatus.OK).body(transformToHttp.apply(response));
    }

    private BiFunction<UUID, HttpUpdateCertificationRequest, UpdateCertificationRequest> transformToRequest = (id, request) ->
            new UpdateCertificationRequest(
                    new Certification(
                            new Id(id),
                            new CertificationName(request.certificationName()),
                            new UserId(request.userId()),
                            new SubscriptionId(request.subscriptionId()),
                            new StartDate(request.startDate()),
                            new EndDate(request.endDate()),
                            new Version(VERSION),
                            new Status(request.status()),
                            new CreatedAt(offsetDateTimeHandler.now()),
                            new ModifiedAt(offsetDateTimeHandler.now())
                    )
            );

    private Function<UpdateCertificationResponse, HttpResponse> transformToHttp = (response) ->
            HttpResponse.ofSuccess(new HttpUpdateCertificationData(response.id().value()));

    private static final Long VERSION = 1L;
}
