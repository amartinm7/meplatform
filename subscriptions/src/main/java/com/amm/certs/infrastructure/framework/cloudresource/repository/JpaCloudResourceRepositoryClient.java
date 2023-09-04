package com.amm.certs.infrastructure.framework.cloudresource.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCloudResourceRepositoryClient extends JpaRepository<JpaCloudResource, UUID> {
}
