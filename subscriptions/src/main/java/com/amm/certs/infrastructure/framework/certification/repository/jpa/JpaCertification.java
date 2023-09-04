package com.amm.certs.infrastructure.framework.certification.repository.jpa;

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
@Table(name = "CERTIFICATION")
public class JpaCertification {
    @Id
    @Column(name = "ID")
    UUID id;
    @Column(name = "CERTIFICATION_NAME")
    String certificationName;
    @Column(name = "USER_ID")
    UUID userId;
    @Column(name = "SUBSCRIPTION_ID")
    UUID subscriptionId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    OffsetDateTime startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    OffsetDateTime endDate;
    @Column(name = "VERSION")
    Long version;
    @Column(name = "STATUS")
    String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    OffsetDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_AT")
    OffsetDateTime modifiedAt;

    public JpaCertification(){}

    public JpaCertification(UUID id, String certificationName, UUID userId, UUID subscriptionId, OffsetDateTime startDate, OffsetDateTime endDate, Long version, String status, OffsetDateTime createdAt, OffsetDateTime modifiedAt) {
        this.id = id;
        this.certificationName = certificationName;
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.version = version;
        this.status = status;
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

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(o instanceof JpaCertification that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCertificationName(), that.getCertificationName()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getSubscriptionId(), that.getSubscriptionId()) && Objects.equals(getStartDate(), that.getStartDate()) && Objects.equals(getEndDate(), that.getEndDate()) && Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getModifiedAt(), that.getModifiedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCertificationName(), getUserId(), getSubscriptionId(), getStartDate(), getEndDate(), getVersion(), getStatus(), getCreatedAt(), getModifiedAt());
    }
}
