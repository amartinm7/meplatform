package com.amm.certs.fixtures.certification;

import com.amm.certs.application.certification.retrieve.RetrieveCertificationRequest;
import com.amm.certs.application.certification.retrieve.RetrieveCertificationResponse;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.fixtures.common.CommonFixture;
import com.amm.certs.infrastructure.framework.certification.api.retrieve.HttpRetrieveCertificationData;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;

public class RetrieveCertificationFixture extends CertificationFixture{

    public static final RetrieveCertificationRequest ANY_RETRIEVE_CERTIFICATION_REQUEST =
            new RetrieveCertificationRequest(new Id(ID));
    public static final RetrieveCertificationResponse ANY_RETRIEVE_CERTIFICATION_RESPONSE =
            new RetrieveCertificationResponse(ANY_CERTIFICATION);

    public static final HttpResponse ANY_HTTP_RETRIEVE_RESPONSE =
            HttpResponse.ofSuccess(
                    new HttpRetrieveCertificationData(
                            ID,
                            CERTIFICATION_NAME,
                            CommonFixture.USER_ID,
                            CommonFixture.SUBSCRIPTION_ID,
                            START_DATE,
                            END_DATE,
                            CommonFixture.VERSION,
                            CommonFixture.CREATED_AT,
                            CommonFixture.MODIFIED_AT
                    )
            );
}
