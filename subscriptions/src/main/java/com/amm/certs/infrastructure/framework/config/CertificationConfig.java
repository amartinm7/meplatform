package com.amm.certs.infrastructure.framework.config;

import com.amm.certs.application.certification.create.CreateNewCertificationService;
import com.amm.certs.application.certification.delete.DeleteCertificationService;
import com.amm.certs.application.certification.retrieve.RetrieveCertificationService;
import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationService;
import com.amm.certs.application.certification.update.UpdateCertificationService;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepositoryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CertificationConfig {
    @Bean
    public JpaCertificationRepository jpaCertificationRepository(
            JpaCertificationRepositoryClient jpaCertificationRepositoryClient
    ) {
        return new JpaCertificationRepository(jpaCertificationRepositoryClient);
    }

    @Bean
    public CreateNewCertificationService createNewCertificationService(
            JpaCertificationRepository jpaCertificationRepository
    ) {
        return new CreateNewCertificationService(jpaCertificationRepository);
    }

    @Bean
    public DeleteCertificationService deleteCertificationService(
            JpaCertificationRepository jpaCertificationRepository
    ) {
        return new DeleteCertificationService(jpaCertificationRepository);
    }

    @Bean
    public UpdateCertificationService updateCertificationService(
            JpaCertificationRepository jpaCertificationRepository
    ) {
        return new UpdateCertificationService(jpaCertificationRepository);
    }

    @Bean
    public RetrieveCertificationService retrieveCertificationService(
            JpaCertificationRepository jpaCertificationRepository
    ) {
        return new RetrieveCertificationService(jpaCertificationRepository);
    }

    @Bean
    public RetrieveAllCertificationService retrieveAllCertificationService(
            JpaCertificationRepository jpaCertificationRepository
    ) {
        return new RetrieveAllCertificationService(jpaCertificationRepository);
    }
}
