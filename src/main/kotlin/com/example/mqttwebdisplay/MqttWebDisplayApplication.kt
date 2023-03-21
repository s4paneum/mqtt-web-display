package com.example.mqttwebdisplay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MqttWebDisplayApplication

fun main(args: Array<String>) {
    runApplication<MqttWebDisplayApplication>(*args)
}
