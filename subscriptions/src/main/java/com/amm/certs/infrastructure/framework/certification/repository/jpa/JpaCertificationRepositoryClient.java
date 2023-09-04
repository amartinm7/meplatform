package com.amm.certs.infrastructure.framework.certification.repository.jpa;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaCertificationRepositoryClient extends JpaRepository<JpaCertification, UUID> {

    // TODO TAKE CARE WITH THIS, BECAUSE SQL PAGE STARTS WITH ZERO INDEX,
    //  IF YOU ASKS FOR PAGE=1 AND THERE ONLY A ROW, YOUR ARE ASKING FOR THE ELEVENTH ROW, SO YOU WILL HAVE NOTHING.
    @Query("SELECT CERT FROM JpaCertification CERT WHERE CERT.userId = :userId")
    Page<JpaCertification> findByUserId(
        @Param("userId") UUID userId,
        Pageable pageable
    );
}
