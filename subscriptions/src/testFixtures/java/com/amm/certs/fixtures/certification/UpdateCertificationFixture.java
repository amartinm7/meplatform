package com.amm.certs.fixtures.certification;

import com.amm.certs.application.certification.update.UpdateCertificationRequest;
import com.amm.certs.application.certification.update.UpdateCertificationResponse;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.infrastructure.framework.certification.api.update.HttpUpdateCertificationData;
import com.amm.certs.infrastructure.framework.certification.api.update.HttpUpdateCertificationRequest;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;

public class UpdateCertificationFixture extends CertificationFixture {
    public static final HttpUpdateCertificationRequest ANY_HTTP_UPDATE_REQUEST =
            new HttpUpdateCertificationRequest(
                    CERTIFICATION_NAME,
                    USER_ID,
                    SUBSCRIPTION_ID,
                    START_DATE,
                    END_DATE,
                    ANY_STATUS_ACTIVE
            );

    public static final UpdateCertificationRequest ANY_UPDATE_CERTIFICATION_REQUEST =
            new UpdateCertificationRequest(ANY_CERTIFICATION);

    public static final UpdateCertificationResponse ANY_UPDATE_CERTIFICATION_RESPONSE =
            new UpdateCertificationResponse(new Id(ID));
    public static final HttpResponse ANY_HTTP_UPDATE_RESPONSE =
            HttpResponse.ofSuccess(new HttpUpdateCertificationData(ID));
}
