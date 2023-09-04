package com.amm.certs.domain.certification.repository;

import com.amm.certs.domain.certification.model.Certification;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.domain.certification.model.paginate.CertificationPage;
import com.amm.certs.domain.certification.model.paginate.Pagination;

public interface CertificationReadRepository {

    public Certification findById(Id id);

    public CertificationPage findByUserId(UserId userId, Pagination pagination);
}
