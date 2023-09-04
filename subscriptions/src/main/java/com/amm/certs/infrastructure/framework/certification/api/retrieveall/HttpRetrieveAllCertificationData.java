package com.amm.certs.infrastructure.framework.certification.api.retrieveall;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.amm.certs.infrastructure.framework.certification.api.retrieve.HttpRetrieveCertificationData;
import java.util.List;

public record HttpRetrieveAllCertificationData(
        @JsonProperty("certifications")
        List<HttpRetrieveCertificationData> certifications,

        @JsonProperty("pagination")
        HttpPaginationData pagination
) {
}
