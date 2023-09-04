package com.amm.certs.infrastructure.framework.certification.api.retrieveall;

import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationRequest;
import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationResponse;
import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationService;
import com.amm.certs.domain.certification.model.Certification;
import com.amm.certs.domain.certification.model.paginate.Page;
import com.amm.certs.domain.certification.model.paginate.Pagination;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.infrastructure.framework.certification.api.retrieve.HttpRetrieveCertificationData;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import jakarta.validation.Valid;
import java.util.function.Function;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetrieveAllCertificationController {

    private RetrieveAllCertificationService retrieveAllCertificationService;

    public RetrieveAllCertificationController(
        RetrieveAllCertificationService retrieveAllCertificationService
    ) {
        this.retrieveAllCertificationService = retrieveAllCertificationService;
    }

    @RequestMapping(
        value = "/api/v1/certification/",
        method = RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpResponse> execute(
        // @RequestHeader(SWISSRE_AUTH_HEADER) String meplatformAuthHeader,
        @ModelAttribute @Valid HttpRetrieveAllCertificationRequest httpRequest
    ) {
        RetrieveAllCertificationResponse response = retrieveAllCertificationService.execute(transformToRequest.apply(httpRequest));
        return ResponseEntity.status(HttpStatus.OK).body(transformToHttp.apply(response));
    }

    private Function<HttpRetrieveAllCertificationRequest, RetrieveAllCertificationRequest> transformToRequest = (httpRequest) ->
        new RetrieveAllCertificationRequest(
            new UserId(httpRequest.userId()),
            new Pagination(httpRequest.page(), httpRequest.resultsPerPage())
        );
    private Function<Certification, HttpRetrieveCertificationData> certificationToHttp = (certification) ->
        new HttpRetrieveCertificationData(
            certification.id().value(),
            certification.certificationName().value(),
            certification.userId().value(),
            certification.subscriptionId().value(),
            certification.startDate().value(),
            certification.endDate().value(),
            certification.version().value(),
            certification.createdAt().value(),
            certification.modifiedAt().value()
        );
    private Function<Page, HttpPaginationData> paginationToHttp = (page) ->
        new HttpPaginationData(
            page.pageNumber(),
            page.resultsPerPage(),
            page.totalPages(),
            page.totalElements()
        );
    private Function<RetrieveAllCertificationResponse, HttpResponse> transformToHttp = (response) ->
        HttpResponse.ofSuccess(
            new HttpRetrieveAllCertificationData(
                response.certificationPage().certificationList().stream().map(certificationToHttp).toList(),
                paginationToHttp.apply(response.certificationPage().page())
            )
        );
    private static final String SWISSRE_AUTH_HEADER = "X-Meplatform-User-Info";
}
