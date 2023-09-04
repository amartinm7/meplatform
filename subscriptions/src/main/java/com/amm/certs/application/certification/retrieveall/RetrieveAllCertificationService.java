package com.amm.certs.application.certification.retrieveall;

import com.amm.certs.domain.certification.model.paginate.CertificationPage;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;

public class RetrieveAllCertificationService {
    private final JpaCertificationRepository jpaCertificationRepository;

    public RetrieveAllCertificationService(JpaCertificationRepository jpaCertificationRepository) {

        this.jpaCertificationRepository = jpaCertificationRepository;
    }

    public RetrieveAllCertificationResponse execute(RetrieveAllCertificationRequest request) {
        CertificationPage certificationPage = jpaCertificationRepository.findByUserId(request.userId(), request.pagination());
        return new RetrieveAllCertificationResponse(certificationPage);
    }
}
