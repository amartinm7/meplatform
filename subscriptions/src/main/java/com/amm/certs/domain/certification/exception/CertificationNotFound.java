package com.amm.certs.domain.certification.exception;

import com.amm.certs.domain.certification.model.Id;

public class CertificationNotFound extends RuntimeException{
    public CertificationNotFound(Id id) {
        super("Certification not found: %s".formatted(id.value()));
    }
}
