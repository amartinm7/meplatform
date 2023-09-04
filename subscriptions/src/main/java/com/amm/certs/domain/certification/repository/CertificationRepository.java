package com.amm.certs.domain.certification.repository;

public interface CertificationRepository extends CertificationReadRepository, CertificationWriteRepository {
    // IDEA TO IMPROVE, IF IT WAS NEEDED
    // CREATE a CertificationReadRepositoryImpl and CertificationWriteRepositoryImpl
    // USE IN THE JpaCertificationRepository both CertificationReadRepositoryImpl, CertificationWriteRepositoryImpl collaborators
    // IN ORDER TO HAVE THE ISOLATED RESPONSIBILITY
}
