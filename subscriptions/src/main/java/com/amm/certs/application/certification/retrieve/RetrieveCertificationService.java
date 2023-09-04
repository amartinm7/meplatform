package com.amm.certs.application.certification.retrieve;

import com.amm.certs.domain.certification.model.Certification;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;

public class RetrieveCertificationService {
    private JpaCertificationRepository repository;

    public RetrieveCertificationService(
            JpaCertificationRepository repository
    ) {
        this.repository = repository;
    }

    public RetrieveCertificationResponse execute(RetrieveCertificationRequest request) {
        Certification certification = repository.findById(request.id());
        return new RetrieveCertificationResponse(certification);
    }
}
