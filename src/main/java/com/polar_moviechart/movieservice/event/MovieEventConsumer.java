package com.polar_moviechart.movieservice.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polar_moviechart.movieservice.domain.service.movie.MovieCommandService;
import com.polar_moviechart.movieservice.event.dto.MovieRatingMessageDto;
import com.polar_moviechart.movieservice.exception.ErrorCode;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import com.polar_moviechart.movieservice.handler.UserServiceHandler;
import com.polar_moviechart.movieservice.event.dto.MovieLikeMessageDto;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class MovieEventConsumer {
    private final ObjectMapper objectMapper;
    private final MovieCommandService movieCommandService;
    private final UserServiceHandler userServiceHandler;

    @RabbitListener(queues = {
            "${app.rabbitmq.queues.movie-like}",
            "${app.rabbitmq.queues.movie-rating}"})
    public void consumeMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        log.info("메시지 수신: {}", message);
        try {
            // JSON 파싱
            JsonNode node = objectMapper.readTree(message);
            String type = node.get("type").asText();
            switch (node.get("type").asText()) {
                case "LIKE":
                    handleMovieLikeEvent(objectMapper.treeToValue(node, MovieLikeMessageDto.class));
                    break;
                case "RATING":
                    handleMovieRatingEvent(objectMapper.treeToValue(node, MovieRatingMessageDto.class));
                    break;
                default:
                    log.warn("올바르지 않은 메시지 타입: {}", type);
            }
            channel.basicAck(deliveryTag, false); // 성공적으로 처리되었음을 RabbitMQ에 알림
        } catch (Exception e) {
            safeBasicNack(channel, deliveryTag, false); // DLQ로 메시지를 이동하도록 설정
            throw new MovieBusinessException(ErrorCode.MESSAGE_PROCESS_EXCEPTION);
        }
    }

    private void handleMovieLikeEvent(MovieLikeMessageDto message) {
        log.info("영화 좋아요 메시지 수신: {}", message);
        userServiceHandler.validateUserMovieLikeState(message);
        movieCommandService.updateLike(message);
    }

    private void handleMovieRatingEvent(MovieRatingMessageDto message) {
        log.info("영화 평점 메시지 수신: {}", message);
        userServiceHandler.validateUserRatingState(message);
    }

    private void safeBasicNack(Channel channel, long deliveryTag, boolean multiple) {
        try {
            channel.basicNack(deliveryTag, multiple,false);
            log.info("메시지를 NACK 처리하였습니다. deliveryTag: {}, requeue: {}", deliveryTag, false);
        } catch (IOException ioException) {
            log.error("NACK 처리 중 예외 발생. deliveryTag: {}, requeue: {}", deliveryTag, false, ioException);
        }
    }
}
