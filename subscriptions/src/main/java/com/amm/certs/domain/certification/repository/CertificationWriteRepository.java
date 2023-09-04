package com.amm.certs.domain.certification.repository;

import com.amm.certs.domain.certification.model.Certification;
import com.amm.certs.domain.certification.model.Id;

public interface CertificationWriteRepository {
    public void save(Certification certification);

    public void update(Certification certification);

    public void deleteById(Id id);
}
