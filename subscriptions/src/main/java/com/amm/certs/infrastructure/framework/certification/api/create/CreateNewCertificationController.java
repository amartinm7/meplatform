package com.amm.certs.infrastructure.framework.certification.api.create;

import com.amm.certs.application.certification.create.CreateNewCertificationRequest;
import com.amm.certs.application.certification.create.CreateNewCertificationResponse;
import com.amm.certs.application.certification.create.CreateNewCertificationService;
import com.amm.certs.domain.certification.model.Certification;
import com.amm.certs.domain.certification.model.CertificationName;
import com.amm.certs.domain.certification.model.EndDate;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.domain.certification.model.StartDate;
import com.amm.certs.domain.certification.model.Status;
import com.amm.certs.domain.common.model.CreatedAt;
import com.amm.certs.domain.common.model.ModifiedAt;
import com.amm.certs.domain.common.model.SubscriptionId;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.domain.common.model.Version;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import com.amm.certs.infrastructure.framework.config.OffsetDateTimeHandler;
import jakarta.validation.Valid;
import java.util.function.Function;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateNewCertificationController {

    private final CreateNewCertificationService createNewCertificationService;
    private OffsetDateTimeHandler offsetDateTimeHandler;

    public CreateNewCertificationController(
            CreateNewCertificationService createNewCertificationService,
            OffsetDateTimeHandler offsetDateTimeHandler
    ) {
        this.createNewCertificationService = createNewCertificationService;
        this.offsetDateTimeHandler = offsetDateTimeHandler;
    }

    @RequestMapping(
            value = "/api/v1/certification",
            method = RequestMethod.POST
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpResponse> execute(@RequestBody @Valid HttpNewCertificationRequest httpNewCertificationRequest) {
        CreateNewCertificationResponse response = createNewCertificationService.execute(transformToRequest.apply(httpNewCertificationRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(transformToHttp.apply(response));
    }

    private Function<HttpNewCertificationRequest, CreateNewCertificationRequest> transformToRequest = (request) ->
            new CreateNewCertificationRequest(
                    new Certification(
                            new Id(request.id()),
                            new CertificationName(request.certificationName()),
                            new UserId(request.userId()),
                            new SubscriptionId(request.subscriptionId()),
                            new StartDate(request.startDate()),
                            new EndDate(request.endDate()),
                            new Version(VERSION),
                            new Status(request.status()),
                            new CreatedAt(offsetDateTimeHandler.now()),
                            new ModifiedAt(offsetDateTimeHandler.now()
                            )
                    )
            );
    private Function<CreateNewCertificationResponse, HttpResponse> transformToHttp = (response) ->
            HttpResponse.ofSuccess(new HttpNewCertificationData(URI.formatted(response.id().value().toString())));

    private static final Long VERSION = 1L;
    private static final String URI = "/api/v1/certification/%s";
}
