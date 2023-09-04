package com.amm.certs.infrastructure.framework.certification.repository.jpa;

import com.amm.certs.domain.certification.exception.CertificationNotFound;
import com.amm.certs.domain.certification.model.StatusEnum;
import com.amm.certs.domain.certification.repository.CertificationRepository;
import com.amm.certs.domain.certification.model.Certification;
import com.amm.certs.domain.certification.model.Status;
import com.amm.certs.domain.common.model.CreatedAt;
import com.amm.certs.domain.certification.model.EndDate;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.domain.common.model.ModifiedAt;
import com.amm.certs.domain.certification.model.CertificationName;
import com.amm.certs.domain.certification.model.StartDate;
import com.amm.certs.domain.common.model.SubscriptionId;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.domain.common.model.Version;
import com.amm.certs.domain.certification.model.paginate.CertificationPage;
import com.amm.certs.domain.certification.model.paginate.Pagination;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class JpaCertificationRepository implements CertificationRepository {

    private final JpaCertificationRepositoryClient jpaCertificationRepositoryClient;

    public JpaCertificationRepository(
        JpaCertificationRepositoryClient jpaCertificationRepositoryClient
    ) {
        this.jpaCertificationRepositoryClient = jpaCertificationRepositoryClient;
    }

    @Override
    // @CacheEvict(value = "certification", key = "#id")
    public void save(Certification certification) {
        jpaCertificationRepositoryClient.save(transformToJpa.apply(certification));
    }

    @Override
    // @CacheEvict(value = "certification", key = "#id")
    public void update(Certification certification) {
        Version version = findById(certification.id()).version();
        jpaCertificationRepositoryClient.save(transformToJpaWithVersion.apply(certification, version));
    }

    @Override
    // @CacheEvict(value = "certification", key = "#id")
    public void deleteById(Id id) {
        jpaCertificationRepositoryClient.deleteById(id.value());
    }

    @Override
    // @Cacheable(value = "certification", key = "#id")
    public Certification findById(Id id) {
        JpaCertification jpaCertification =
            jpaCertificationRepositoryClient.findById(id.value())
                .orElseThrow(() -> new CertificationNotFound(id));
        return transformToDomain.apply(jpaCertification);
    }

    @Override
    public CertificationPage findByUserId(UserId userId, Pagination pagination) {
        Page<JpaCertification> page = jpaCertificationRepositoryClient.findByUserId(userId.value(), paginationToRequest.apply(pagination));
        return pageToDomainPage.apply(page);
    }

    private Function<Pagination, PageRequest> paginationToRequest = (pagination) ->
        PageRequest.of(
            pagination.page(),
            pagination.resultsPerPage(),
            Sort.by(Sort.Direction.ASC, "id")
        );

    private Function<JpaCertification, Certification> transformToDomain = (jpa) ->
        new Certification(
            new Id(jpa.id),
            new CertificationName(jpa.certificationName),
            new UserId(jpa.userId),
            new SubscriptionId(jpa.subscriptionId),
            new StartDate(jpa.startDate),
            new EndDate(jpa.endDate),
            new Version(jpa.version),
            new Status(StatusEnum.valueOf(jpa.status)),
            new CreatedAt(jpa.createdAt),
            new ModifiedAt(jpa.modifiedAt)
        );

    private Function<List<JpaCertification>,List<Certification>> jpaListToDomainList = (jpaList) ->
        jpaList.stream().map(transformToDomain).toList();

    private Function<Page<JpaCertification>, CertificationPage> pageToDomainPage = (page) ->
        new CertificationPage(
            jpaListToDomainList.apply(page.getContent()),
            new com.amm.certs.domain.certification.model.paginate.Page(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
            )
        );

    private Function<Certification, JpaCertification> transformToJpa = (certification) ->
        new JpaCertification(
            certification.id().value(),
            certification.certificationName().value(),
            certification.userId().value(),
            certification.subscriptionId().value(),
            certification.startDate().value(),
            certification.endDate().value(),
            certification.version().value(),
            certification.status().value().name(),
            certification.createdAt().value(),
            certification.modifiedAt().value()
        );

    private BiFunction<Certification, Version, JpaCertification> transformToJpaWithVersion = (certification, version) ->
        new JpaCertification(
            certification.id().value(),
            certification.certificationName().value(),
            certification.userId().value(),
            certification.subscriptionId().value(),
            certification.startDate().value(),
            certification.endDate().value(),
            version.value(),
            certification.status().value().name(),
            certification.createdAt().value(),
            certification.modifiedAt().value()
        );
}

