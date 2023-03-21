package com.example.mqtt

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MqttConfig {

    @Value("\${mqtt.url}")
    private lateinit var mqttUrl: String

    @Value("\${mqtt.username}")
    private lateinit var mqttUsername: String

    @Value("\${mqtt.password}")
    private lateinit var mqttPassword: String

    @Value("\${mqtt.topic1}")
    private lateinit var mqttTopic1: String

    @Value("\${mqtt.topic2}")
    private lateinit var mqttTopic2: String


    @Bean
    fun mqttClient(mqttSubscriber: MqttSubscriber): MqttClient {
        val persistence = MemoryPersistence()
        val clientId = MqttClient.generateClientId()
        val client = MqttClient(mqttUrl, clientId, persistence)
        client.setCallback(mqttSubscriber)

        val options = MqttConnectOptions().apply {
            isCleanSession = true
            userName = mqttUsername
            password = mqttPassword.toCharArray()
        }

        client.connect(options)
        client.subscribe(mqttTopic1)
        client.subscribe(mqttTopic2)
        return client
    }
}
