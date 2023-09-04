package com.amm.certs.domain.certification.model.paginate;

import com.amm.certs.domain.certification.model.Certification;
import java.util.List;

public record CertificationPage(
    List<Certification> certificationList,
    Page page
) {
}
