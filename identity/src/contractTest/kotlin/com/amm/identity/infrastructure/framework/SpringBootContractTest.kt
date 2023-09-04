package com.amm.identity.infrastructure.framework

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [KafkaConfig::class, DockerConfig::class, IdentityApplication::class])
@ActiveProfiles("test")
abstract class SpringBootContractTest {

}
