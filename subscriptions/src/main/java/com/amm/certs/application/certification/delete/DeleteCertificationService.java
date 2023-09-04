package com.amm.certs.application.certification.delete;

import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;

public class DeleteCertificationService {

    private JpaCertificationRepository repository;

    public DeleteCertificationService(JpaCertificationRepository repository) {
        this.repository = repository;
    }

    public DeleteCertificationResponse execute(DeleteCertificationRequest request) {
        repository.deleteById(request.id());
        // TODO fire domain event
        return new DeleteCertificationResponse(request.id());
    }
}
