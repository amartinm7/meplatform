package com.amm.identity.infrastructure.framework.config

import com.amm.identity.infrastructure.framework.user.stream.UserConsumerHandler
import java.nio.charset.StandardCharsets
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfig {

    @Bean
    fun userConsumer(
        userConsumerHandler: UserConsumerHandler
    ): (ByteArray) -> Unit =
        { event -> userConsumerHandler.handle(String(event, StandardCharsets.UTF_8)) }

    @Bean
    fun userConsumerHandler(): UserConsumerHandler =
        UserConsumerHandler()
}
