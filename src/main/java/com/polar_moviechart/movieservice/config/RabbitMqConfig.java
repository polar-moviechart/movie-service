package com.polar_moviechart.movieservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Bean
    public Queue userServiceQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // JSON 형식의 메시지를 직렬화하고 역직렬할 수 있도록 설정
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter converter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
//        converter.setUseProjectionForInterfaces(true); // 메시지로 인터페이스 사용 가능
        return converter;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(3); // 동시에 처리할 소비자 수
        factory.setMaxConcurrentConsumers(10); // 최대 소비자 수
        return factory;
    }
}