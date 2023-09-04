package com.amm.certs.domain.cloudresource.repository;

import com.amm.certs.domain.cloudresource.model.CloudResource;

public interface CloudResourceWriteRepository {
    void save (CloudResource cloudResource);
}
