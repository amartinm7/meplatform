package com.amm.certs.fixtures.certification;

import com.amm.certs.application.certification.delete.DeleteCertificationRequest;
import com.amm.certs.application.certification.delete.DeleteCertificationResponse;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.infrastructure.framework.certification.api.delete.HttpDeleteCertificationData;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;

public class DeleteCertificationFixture extends CertificationFixture{
    public static final DeleteCertificationRequest ANY_DELETE_CERTIFICATION_REQUEST =
            new DeleteCertificationRequest(new Id(ID));
    public static final DeleteCertificationResponse ANY_DELETE_CERTIFICATION_RESPONSE =
            new DeleteCertificationResponse(new Id(ID));
    public static final HttpResponse ANY_HTTP_DELETE_RESPONSE =
            HttpResponse.ofSuccess(new HttpDeleteCertificationData(ID));
}
