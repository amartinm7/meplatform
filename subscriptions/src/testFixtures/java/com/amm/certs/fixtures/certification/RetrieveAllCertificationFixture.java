package com.amm.certs.fixtures.certification;

import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationRequest;
import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationResponse;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.infrastructure.framework.certification.api.retrieve.HttpRetrieveCertificationData;
import com.amm.certs.infrastructure.framework.certification.api.retrieveall.HttpRetrieveAllCertificationData;
import com.amm.certs.infrastructure.framework.certification.api.retrieveall.HttpRetrieveAllCertificationRequest;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import com.amm.certs.domain.certification.model.paginate.CertificationPage;
import java.util.List;

public class RetrieveAllCertificationFixture extends CertificationFixture {
    public static final HttpRetrieveAllCertificationRequest ANY_HTTP_RETRIEVE_ALL_REQUEST =
            new HttpRetrieveAllCertificationRequest(USER_ID, ANY_PAGE_NUMBER, ANY_RESULTS_PER_PAGE);
    public static final RetrieveAllCertificationRequest ANY_RETRIEVE_ALL_CERTIFICATION_REQUEST =
            new RetrieveAllCertificationRequest(new UserId(USER_ID), ANY_PAGINATION);
    public static final RetrieveAllCertificationResponse ANY_RETRIEVE_ALL_CERTIFICATION_RESPONSE =
            new RetrieveAllCertificationResponse(
                new CertificationPage(List.of(ANY_CERTIFICATION), ANY_PAGE)
            );
    public static final HttpResponse ANY_HTTP_RETRIEVE_ALL_RESPONSE =
            HttpResponse.ofSuccess(
                    new HttpRetrieveAllCertificationData(
                            List.of(
                                    new HttpRetrieveCertificationData(
                                            ID,
                                            CERTIFICATION_NAME,
                                            USER_ID,
                                            SUBSCRIPTION_ID,
                                            START_DATE,
                                            END_DATE,
                                            VERSION,
                                            CREATED_AT,
                                            MODIFIED_AT
                                    )
                            ),
                            ANY_HTTP_PAGINATION
                    )
            );
}
