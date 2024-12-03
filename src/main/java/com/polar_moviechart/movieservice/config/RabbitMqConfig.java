package com.polar_moviechart.movieservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {
    private final RabbitMQProperties properties;

    // 메인 교환기
    @Bean
    public DirectExchange mainExchange() {
        return new DirectExchange(properties.getExchange().get("main"));
    }
    // DLQ 교환기
    @Bean
    public DirectExchange dlqExchange() {
        return new DirectExchange(properties.getExchange().get("dlq"));
    }
    // 영화 좋아요
    @Bean
    public Queue movieLikeQueue() {
        return QueueBuilder.durable(properties.getQueues().get("movie-like"))
                .withArgument("x-dead-letter-exchange", properties.getExchange().get("dlq"))
                .withArgument("x-dead-letter-routing-key", properties.getRoutingKeys().get("dlq"))
                .build();
    }
    // 영화 평점
    @Bean
    public Queue movieRatingQueue() {
        return QueueBuilder.durable(properties.getQueues().get("movie-rating"))
                .withArgument("x-dead-letter-exchange", properties.getExchange().get("dlq"))
                .withArgument("x-dead-letter-routing-key", properties.getRoutingKeys().get("dlq"))
                .build();
    }

    // 메인 큐와 교환기 바인딩
    @Bean
    public Binding movieLikeBinding() {
        return BindingBuilder.bind(movieLikeQueue()).to(mainExchange()).with("movie.like");
    }
    @Bean
    public Binding movieRatingBinding() {
        return BindingBuilder.bind(movieRatingQueue()).to(mainExchange()).with("movie.rating");
    }

    @Bean
    public Queue dlq() {
        return QueueBuilder.durable(properties.getQueues().get("dlq")).build();
    }

    // DLQ 바인딩
    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(dlq()).to(dlqExchange()).with("dlq.key");
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
        return converter;
    }
}
