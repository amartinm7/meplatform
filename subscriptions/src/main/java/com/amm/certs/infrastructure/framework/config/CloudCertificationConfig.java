package com.amm.certs.infrastructure.framework.config;

import com.amm.certs.application.cloudresource.saveall.SaveAllCloudResourcesService;
import com.amm.certs.domain.cloudresource.repository.CloudResourceRepository;
import com.amm.certs.domain.cloudresource.repository.ExternalCloudResourceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amm.certs.infrastructure.framework.cloudresource.repository.FeignExternalCloudResourceRepository;
import com.amm.certs.infrastructure.framework.cloudresource.repository.FeignExternalCloudResourceRepositoryClient;
import com.amm.certs.infrastructure.framework.cloudresource.repository.JpaCloudResourceRepository;
import com.amm.certs.infrastructure.framework.cloudresource.repository.JpaCloudResourceRepositoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudCertificationConfig {
    @Bean
    public FeignExternalCloudResourceRepositoryClient feignExternalCloudResourceRepositoryClient(
        @Value("${app.ms-provider-cloud-resource.url}")
        String providerCloudResourceURI,
        ObjectMapper objectMapper
    ) {
        return new FeignExternalCloudResourceRepositoryClient(providerCloudResourceURI, objectMapper);
    }

    @Bean
    public ExternalCloudResourceRepository externalCloudResourceRepository(
        FeignExternalCloudResourceRepositoryClient feignExternalCloudResourceRepositoryClient,
        OffsetDateTimeHandler offsetDateTimeHandler
    ) {
        return new FeignExternalCloudResourceRepository(
            feignExternalCloudResourceRepositoryClient,
            offsetDateTimeHandler);
    }

    @Bean
    public CloudResourceRepository cloudResourceRepository(
        JpaCloudResourceRepositoryClient jpaCloudResourceRepositoryClient
    ) {
        return new JpaCloudResourceRepository(jpaCloudResourceRepositoryClient);
    }

    @Bean
    public SaveAllCloudResourcesService retrieveAllSubscriptions(
        ExternalCloudResourceRepository externalCloudResourceRepository,
        CloudResourceRepository cloudResourceRepository
    ) {
        return new SaveAllCloudResourcesService(
            externalCloudResourceRepository,
            cloudResourceRepository
        );
    }
}
