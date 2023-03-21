package com.example.mqttwebdisplay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.example.mqtt")
class MqttWebDisplayApplication

fun main(args: Array<String>) {
    runApplication<MqttWebDisplayApplication>(*args)
}
