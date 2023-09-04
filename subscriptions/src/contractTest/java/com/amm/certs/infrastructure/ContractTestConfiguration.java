package com.amm.certs.infrastructure;

import com.amm.certs.application.certification.create.CreateNewCertificationService;
import com.amm.certs.application.certification.delete.DeleteCertificationService;
import com.amm.certs.application.certification.retrieve.RetrieveCertificationService;
import com.amm.certs.application.certification.retrieveall.RetrieveAllCertificationService;
import com.amm.certs.application.certification.update.UpdateCertificationService;
import com.amm.certs.infrastructure.framework.config.OffsetDateTimeHandler;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ContractTestConfiguration {
    @MockBean
    public OffsetDateTimeHandler offsetDateTimeHandler;

    @MockBean
    public CreateNewCertificationService createNewCertificationService;

    @MockBean
    public UpdateCertificationService updateCertificationService;

    @MockBean
    public DeleteCertificationService deleteCertificationService;

    @MockBean
    public RetrieveCertificationService retrieveCertificationService;

    @MockBean
    public RetrieveAllCertificationService retrieveAllCertificationService;
}
