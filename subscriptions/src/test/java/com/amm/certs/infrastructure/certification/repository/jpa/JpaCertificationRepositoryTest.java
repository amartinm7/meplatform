package com.amm.certs.infrastructure.certification.repository.jpa;

import com.amm.certs.domain.certification.exception.CertificationNotFound;
import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertification;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepository;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertificationRepositoryClient;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

class JpaCertificationRepositoryTest {
    private JpaCertificationRepositoryClient jpaClient = mock(JpaCertificationRepositoryClient.class);
    private JpaCertificationRepository repository = new JpaCertificationRepository(jpaClient);

    @Test
    void should_save_a_new_certification() {
        repository.save(CertificationFixture.ANY_CERTIFICATION);
        verify(jpaClient, times(1)).save(CertificationFixture.ANY_JPA_REQUEST);
    }

    @Test
    void should_delete_a_certification() {
        repository.deleteById(CertificationFixture.ANY_ID);
        verify(jpaClient, times(1)).deleteById(CertificationFixture.ANY_ID.value());
    }

    @Test
    void should_update_a_certification() {
        mock_repository();
        repository.update(CertificationFixture.ANY_CERTIFICATION);
        verify(jpaClient, times(1)).findById(CertificationFixture.ANY_ID.value());
        verify(jpaClient, times(1)).save(CertificationFixture.ANY_JPA_REQUEST);
    }

    @Test
    void should_throw_exception_when_not_exists_a_certification_for_updating() {
        assertThrows(CertificationNotFound.class, () -> {
            repository.update(CertificationFixture.ANY_CERTIFICATION);
        });
    }

    @Test
    void should_retrieve_a_certification_by_id() {
        mock_repository();
        repository.findById(CertificationFixture.ANY_ID);
        verify(jpaClient, times(1)).findById(CertificationFixture.ANY_ID.value());
    }

    @Test
    void should_throw_exception_when_not_exists_a_certification_for_searching_by_id() {
        assertThrows(CertificationNotFound.class, () -> {
            repository.findById(CertificationFixture.ANY_ID);
        });
    }

    @Test
    void should_retrieves_all_certifications_by_user() {
        mock_repository();
        repository.findByUserId(CertificationFixture.ANY_USER_ID, CertificationFixture.ANY_PAGINATION);
        verify(jpaClient, times(1)).findByUserId(CertificationFixture.USER_ID, ANY_PAGEABLE);
    }

    private void mock_repository() {
        when(jpaClient.findById(CertificationFixture.ANY_ID.value())).thenReturn(Optional.of(CertificationFixture.ANY_JPA_REQUEST));
        when(jpaClient.save(CertificationFixture.ANY_JPA_REQUEST)).thenReturn(CertificationFixture.ANY_JPA_REQUEST);
        when(jpaClient.findByUserId(CertificationFixture.USER_ID, ANY_PAGEABLE)).thenReturn(ANY_PAGE);
    }

    private static final Page<JpaCertification> ANY_PAGE = new PageImpl(CertificationFixture.ANY_JPA_CERTIFICATION_LIST);

    private static final Pageable ANY_PAGEABLE =
        PageRequest.of(
            CertificationFixture.ANY_PAGINATION.page(),
            CertificationFixture.ANY_PAGINATION.resultsPerPage(),
            Sort.by(Sort.Direction.ASC, "id")
        );
}
