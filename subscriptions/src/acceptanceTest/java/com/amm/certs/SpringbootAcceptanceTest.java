package com.amm.certs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amm.certs.fixtures.certification.CertificationFixture;
import com.amm.certs.infrastructure.CertificationsApplication;
import java.util.Map;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(
        classes = {RestTemplateConfig.class, CertificationsApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public abstract class SpringbootAcceptanceTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    protected HttpEntityHandler httpEntityHandler;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        truncateTable();
        insertRows();
    }

    static {
        MyDockerContainer dockerContainer = new MyDockerContainer();
        dockerContainer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> dockerContainer.stop()));
    }

    private void truncateTable() {
        jdbcTemplate.update(TRUNCATE_TABLE, Map.of());
    }

    private void insertRows() {
        jdbcTemplate.update(INSERT_ROW, Map.of());
    }

    private static final String TRUNCATE_TABLE = "TRUNCATE TABLE PUBLIC.CERTIFICATION";

    private static String SQL_COLUMNS = "ID, CERTIFICATION_NAME, USER_ID, SUBSCRIPTION_ID, START_DATE, END_DATE, VERSION, STATUS, CREATED_AT, MODIFIED_AT";
    private static Supplier<String> SQL_VALUES = () ->
            "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s', '%s'".formatted(
                    CertificationFixture.ID,
                    CertificationFixture.CERTIFICATION_NAME,
                    CertificationFixture.USER_ID,
                    CertificationFixture.SUBSCRIPTION_ID,
                    CertificationFixture.START_DATE,
                    CertificationFixture.END_DATE,
                    CertificationFixture.VERSION,
                    CertificationFixture.ANY_STATUS,
                    CertificationFixture.CREATED_AT,
                    CertificationFixture.MODIFIED_AT
            );
    private static final String INSERT_ROW =
            "INSERT INTO PUBLIC.CERTIFICATION (%s) VALUES (%s)".formatted(SQL_COLUMNS, SQL_VALUES.get());

    public String toJSON(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
