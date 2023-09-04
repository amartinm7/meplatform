package com.amm.identity.infrastructure.framework

import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.skyscreamer.jsonassert.Customization
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.skyscreamer.jsonassert.comparator.CustomComparator
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig {
    fun produceKafkaMessage(payload: String, topic: String) {
        createProducer().use { producer ->
            producer.send(ProducerRecord(topic, "a key", payload))
        }
    }

    private fun pollMessagesFromKafka(consumer: KafkaConsumer<String, String>) =
        consumer.poll(Duration.ofSeconds(1)).map { it.value() }

    protected fun verifyIsMatchingEventInTopic(
        expectedJsonEventString: String,
        topicName: String,
        pollingDuration: Int = 60,
        comparator: CustomComparator = defaultEventComparator,
    ) {
        val consumer = createAvroConsumer(topicName)
        val initialTime = LocalDateTime.now()
        val events = mutableListOf<String>()
        while (Duration.between(initialTime, LocalDateTime.now()).seconds < pollingDuration) {
            val eventsFromKafka = pollMessagesFromKafka(consumer)
            events.addAll(eventsFromKafka)
            val matchingEvent = eventsFromKafka.firstOrNull {
                kotlin.runCatching {
                    JSONAssert.assertEquals(it.toString(), expectedJsonEventString, comparator)
                }.isSuccess
            }
            if (matchingEvent != null) {
                consumer.close(Duration.ofSeconds(1))
                return
            }
        }
        consumer.close(Duration.ofSeconds(1))
        println("NO EVENTS FOUND IN TOPIC $topicName MATCHING WITH $expectedJsonEventString")
        println("FOUND EVENTS IN TOPIC:")
        events.forEach { println(it) }
        throw NoMatchingEventsFoundInTopic(topicName)
    }
    private fun createProducer() =
        KafkaProducer<String, String>(
            Properties().apply {
                this[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:$BROKER_PORT"
                this[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
                this[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
                this[ProducerConfig.ACKS_CONFIG] = "all"
                this[ProducerConfig.RETRIES_CONFIG] = 0
                this[ProducerConfig.LINGER_MS_CONFIG] = 1
            },
        )

    private fun createAvroConsumer(topic: String) =
        KafkaConsumer<String, String>(
            Properties().apply {
                this[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:$BROKER_PORT"
                this[ConsumerConfig.GROUP_ID_CONFIG] = "group-${UUID.randomUUID()}"
                this[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringDeserializer"
                this[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringDeserializer"
                this[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
                this[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = true
            },
        ).apply {
            subscribe(listOf(topic))
        }

    companion object {
        const val USERS_TOPIC_NAME = "pub.myapp.ms-app--identity.user.1"
        const val BROKER_PORT = 9092
        private val defaultEventComparator = CustomComparator(
            JSONCompareMode.STRICT,
            Customization.customization("updatedAt") { _, _ -> true },
            Customization.customization("publishedAt") { _, _ -> true },
            Customization.customization("eventTimestamp") { _, _ -> true },
        )
    }
}

class NoMatchingEventsFoundInTopic(val topic: String) : RuntimeException()
