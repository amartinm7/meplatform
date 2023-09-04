package com.amm.certs.application.certification.create;

import com.amm.certs.domain.certification.repository.CertificationRepository;

public class CreateNewCertificationService {
    private final CertificationRepository certificationRepository;
    public CreateNewCertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public CreateNewCertificationResponse execute(CreateNewCertificationRequest request) {
        certificationRepository.save(request.certification());
        // TODO fire domain event
        return new CreateNewCertificationResponse(request.certification().id());
    }
}

