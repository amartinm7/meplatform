package com.amm.identity.infrastructure.framework

import java.io.File
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait

@Configuration
class DockerConfig {
    companion object {
        private val docker = DockerComposeContainer<Nothing>(File("docker-compose.yml"))
            .apply {
                withLocalCompose(true)
                waitingFor("zookeeper", Wait.defaultWaitStrategy())
                waitingFor("broker", Wait.defaultWaitStrategy())
                waitingFor("schema-registry", Wait.forHttp("/subjects"))
            }
        init {
            docker.start()
            Runtime.getRuntime().addShutdownHook(Thread { docker.stop() })
        }
    }
}
