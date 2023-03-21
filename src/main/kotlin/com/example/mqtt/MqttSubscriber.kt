package com.example.mqtt

import com.google.gson.Gson
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

data class MqttPayload(val topic: String, val message: String)

@Component
class MqttSubscriber(private val webSocketHandler: WebSocketHandler) : MqttCallback {

    @Value("\${mqtt.topic1}")
    private lateinit var topic1: String

    @Value("\${mqtt.topic2}")
    private lateinit var topic2: String

    private val gson = Gson()

    override fun connectionLost(cause: Throwable?) {
        println("Connection to MQTT broker lost")
    }

    override fun messageArrived(topic: String, message: MqttMessage?) {
        val payload = MqttPayload(topic, message.toString())
        val json = gson.toJson(payload)
        webSocketHandler.broadcast(TextMessage(json))
    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {
        // not needed
    }
}
