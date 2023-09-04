package com.amm.certs.application.certification.retrieveall;

import com.amm.certs.domain.certification.model.paginate.Pagination;
import com.amm.certs.domain.common.model.UserId;

public record RetrieveAllCertificationRequest(UserId userId, Pagination pagination) {
}
