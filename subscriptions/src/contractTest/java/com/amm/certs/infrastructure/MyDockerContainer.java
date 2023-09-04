package com.amm.certs.infrastructure;

import java.io.File;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class MyDockerContainer {

    private final DockerComposeContainer dockerContainer;

    public MyDockerContainer() {
        dockerContainer = new DockerComposeContainer<>(new File("docker-compose.yml"))
                .withLocalCompose(true)
                .waitingFor("database", Wait.defaultWaitStrategy());
        // .waitingFor("wiremock", Wait.defaultWaitStrategy());
    }

    public void start() {
        this.dockerContainer.start();
    }

    public void stop() {
        this.dockerContainer.stop();
    }
}
