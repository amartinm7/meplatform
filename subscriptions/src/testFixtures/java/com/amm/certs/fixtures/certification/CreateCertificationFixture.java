package com.amm.certs.fixtures.certification;

import com.amm.certs.application.certification.create.CreateNewCertificationRequest;
import com.amm.certs.application.certification.create.CreateNewCertificationResponse;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.fixtures.common.CommonFixture;
import com.amm.certs.infrastructure.framework.certification.api.create.HttpNewCertificationData;
import com.amm.certs.infrastructure.framework.certification.api.create.HttpNewCertificationRequest;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;

public class CreateCertificationFixture extends CertificationFixture {

    public static final HttpNewCertificationRequest ANY_HTTP_CREATE_REQUEST =
            new HttpNewCertificationRequest(
                    ID,
                    CERTIFICATION_NAME,
                    CommonFixture.USER_ID,
                    CommonFixture.SUBSCRIPTION_ID,
                    START_DATE,
                    END_DATE,
                    ANY_STATUS_ACTIVE
            );

    public static final HttpResponse ANY_HTTP_CREATE_RESPONSE =
            HttpResponse.ofSuccess(new HttpNewCertificationData("%s/%s".formatted(URI_NEW_CERTIFICATION, ID.toString())));

    public static final CreateNewCertificationRequest ANY_CREATE_NEW_CERTIFICATION_REQUEST =
            new CreateNewCertificationRequest(ANY_CERTIFICATION);

    public static final CreateNewCertificationResponse ANY_CREATE_NEW_CERTIFICATION_RESPONSE =
            new CreateNewCertificationResponse(new Id(ID));
}
