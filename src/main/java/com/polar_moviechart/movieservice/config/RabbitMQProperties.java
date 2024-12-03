package com.polar_moviechart.movieservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "app.rabbitmq") // yml의 app.rabbitmq를 매핑
public class RabbitMQProperties {

    private Map<String, String> exchange;     // 교환기 정보 (main, dlq)
    private Map<String, String> queues;       // 큐 정보
    private Map<String, String> routingKeys;  // 라우팅 키 정보

    // Getters and Setters
    public Map<String, String> getExchange() {
        return exchange;
    }

    public void setExchange(Map<String, String> exchange) {
        this.exchange = exchange;
    }

    public Map<String, String> getQueues() {
        return queues;
    }

    public void setQueues(Map<String, String> queues) {
        this.queues = queues;
    }

    public Map<String, String> getRoutingKeys() {
        return routingKeys;
    }

    public void setRoutingKeys(Map<String, String> routingKeys) {
        this.routingKeys = routingKeys;
    }
}