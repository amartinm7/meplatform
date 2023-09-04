package com.amm.certs.application.certification.update;

import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;

public class UpdateCertificationService {
    private final JpaCertificationRepository jpaCertificationRepository;

    public UpdateCertificationService(JpaCertificationRepository jpaCertificationRepository) {
        this.jpaCertificationRepository = jpaCertificationRepository;
    }

    public UpdateCertificationResponse execute(UpdateCertificationRequest request) {
        jpaCertificationRepository.update(request.certification());
        // TODO fire domain event
        return new UpdateCertificationResponse(request.certification().id());
    }
}
