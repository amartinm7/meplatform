package com.amm.certs.fixtures.certification;

import com.amm.certs.domain.certification.model.Certification;
import com.amm.certs.domain.certification.model.CertificationName;
import com.amm.certs.domain.certification.model.EndDate;
import com.amm.certs.domain.certification.model.Id;
import com.amm.certs.domain.certification.model.StartDate;
import com.amm.certs.domain.certification.model.Status;
import com.amm.certs.domain.certification.model.StatusEnum;
import com.amm.certs.domain.common.model.CreatedAt;
import com.amm.certs.domain.common.model.ModifiedAt;
import com.amm.certs.domain.common.model.SubscriptionId;
import com.amm.certs.domain.common.model.UserId;
import com.amm.certs.domain.common.model.Version;
import com.amm.certs.fixtures.common.CommonFixture;
import com.amm.certs.infrastructure.framework.certification.api.retrieveall.HttpPaginationData;
import com.amm.certs.infrastructure.framework.certification.repository.jpa.JpaCertification;
import com.amm.certs.infrastructure.framework.config.OffsetDateTimeHandler;
import com.amm.certs.domain.certification.model.paginate.CertificationPage;
import com.amm.certs.domain.certification.model.paginate.Page;
import com.amm.certs.domain.certification.model.paginate.Pagination;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class CertificationFixture extends CommonFixture {
    public static final String ANY_STATUS = StatusEnum.ACTIVE.name();
    public static final StatusEnum ANY_STATUS_ACTIVE = StatusEnum.ACTIVE;
    public static final String URI_NEW_CERTIFICATION = "/api/v1/certification";
    public static final String ANY_START_DATE_STR = "2020-08-18T00:00:00Z";
    public static final String ANY_END_DATE_STR = "2020-12-18T00:00:00Z";

    public static final UUID ID = UUID.fromString("b476c4bb-46af-4151-8999-40689ddcd00e");
    public static final String CERTIFICATION_NAME = "CERTIFICATION_NAME";
    public static final OffsetDateTime START_DATE = new OffsetDateTimeHandler().toOffsetDateTime(ANY_START_DATE_STR);
    public static final OffsetDateTime END_DATE = new OffsetDateTimeHandler().toOffsetDateTime(ANY_END_DATE_STR);
    public static final Id ANY_ID = new Id(ID);
    public static final Certification ANY_CERTIFICATION =
        new Certification(
            new Id(ID),
            new CertificationName(CERTIFICATION_NAME),
            new UserId(USER_ID),
            new SubscriptionId(SUBSCRIPTION_ID),
            new StartDate(START_DATE),
            new EndDate(END_DATE),
            new Version(VERSION),
            new Status(StatusEnum.valueOf(ANY_STATUS)),
            new CreatedAt(CREATED_AT),
            new ModifiedAt(MODIFIED_AT)
        );

    public static final List<Certification> ANY_CERTIFICATION_LIST = List.of(ANY_CERTIFICATION);

    public static final JpaCertification ANY_JPA_REQUEST =
        new JpaCertification(
            ID,
            CERTIFICATION_NAME,
            USER_ID,
            SUBSCRIPTION_ID,
            START_DATE,
            END_DATE,
            VERSION,
            ANY_STATUS,
            CREATED_AT,
            MODIFIED_AT
        );
    public static final List<JpaCertification> ANY_JPA_CERTIFICATION_LIST = List.of(ANY_JPA_REQUEST);
    public static final Integer ANY_PAGE_NUMBER = 0; // TODO TAKE CARE WITH THIS, BECAUSE SQL PAGE STARTS WITH ZERO INDEX, IF YOU ASKS FOR PAGE=1 AND THERE ONLY A ROW, YOUR ARE ASKING FOR THE ELEVENTH ROW, SO YOU WILL HAVE NOTHING.
    public static final Integer ANY_RESULTS_PER_PAGE = 10;
    public static final Pagination ANY_PAGINATION = new Pagination(ANY_PAGE_NUMBER, ANY_RESULTS_PER_PAGE);
    public static final Integer ANY_TOTAL_PAGES = 1;
    public static final Long ANY_TOTAL_ELEMENTS = 1L;
    public static final Page ANY_PAGE = new Page(ANY_PAGE_NUMBER, ANY_RESULTS_PER_PAGE, ANY_TOTAL_PAGES, ANY_TOTAL_ELEMENTS);
    public static final HttpPaginationData ANY_HTTP_PAGINATION = new HttpPaginationData(ANY_PAGE_NUMBER, ANY_RESULTS_PER_PAGE, ANY_TOTAL_PAGES, ANY_TOTAL_ELEMENTS);
    public static final CertificationPage ANY_CERTIFICATION_PAGE = new CertificationPage(ANY_CERTIFICATION_LIST, ANY_PAGE);
}
