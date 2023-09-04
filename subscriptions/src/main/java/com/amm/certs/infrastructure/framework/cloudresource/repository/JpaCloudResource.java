package com.amm.certs.infrastructure.framework.cloudresource.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "RESOURCE")
public class JpaCloudResource {
    @Id
    @Column(name = "ID")
    UUID id;
    @Column(name = "RESOURCE_NAME")
    String certificationName;
    @Column(name = "USER_ID")
    UUID userId;
    @Column(name = "SUBSCRIPTION_ID")
    UUID subscriptionId;
    @Column(name = "VERSION")
    Long version;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    OffsetDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_AT")
    OffsetDateTime modifiedAt;

    public JpaCloudResource() {
    }

    public JpaCloudResource(UUID id, String certificationName, UUID userId, UUID subscriptionId, Long version, OffsetDateTime createdAt, OffsetDateTime modifiedAt) {
        this.id = id;
        this.certificationName = certificationName;
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.version = version;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(UUID subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(OffsetDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaCloudResource that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCertificationName(), that.getCertificationName()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getSubscriptionId(), that.getSubscriptionId()) && Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getModifiedAt(), that.getModifiedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCertificationName(), getUserId(), getSubscriptionId(), getVersion(), getCreatedAt(), getModifiedAt());
    }
}
