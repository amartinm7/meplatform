package com.amm.certs.infrastructure.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfig {
    @Bean
   public OffsetDateTimeHandler offsetDateTimeHandler() {
       return new OffsetDateTimeHandler();
   }
}
