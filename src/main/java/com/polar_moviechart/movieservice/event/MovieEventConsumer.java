package com.polar_moviechart.movieservice.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polar_moviechart.movieservice.domain.service.movie.MovieCommandService;
import com.polar_moviechart.movieservice.exception.ErrorCode;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import com.polar_moviechart.movieservice.handler.UserServiceHandler;
import com.polar_moviechart.movieservice.repository.MovieRepository;
import com.polar_moviechart.movieservice.event.dto.MovieLikeMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class MovieEventConsumer {
    private final ObjectMapper objectMapper;
    private final MovieCommandService movieCommandService;
    private final UserServiceHandler userServiceHandler;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(String message) {
        log.info("메시지 수신: {}", message);
        try {
            // JSON 파싱
            JsonNode node = objectMapper.readTree(message);
            JsonNode typeNode = node.get("type");
            String type = typeNode.asText();
            switch (type) {
                case "LIKE":
                    handleMovieLikeEvent(objectMapper.treeToValue(node, MovieLikeMessageDto.class));
                    break;
                case "RATING":
//                handleMovieRatingEvent(message);
                    break;
                default:
                    log.warn("올바르지 않은 메시지 타입: {}", type);
            }
        } catch (JsonProcessingException e) {
            log.error("메시지 파싱 오류: {}", message, e);
            throw new MovieBusinessException(ErrorCode.MESSAGE_PARSE_ERROR);
        }
    }

    private void handleMovieLikeEvent(MovieLikeMessageDto message) {
        log.info("영화 좋아요 메시지 수신: {}", message);
        userServiceHandler.validateUserMovieLikeState(message);
        movieCommandService.updateLike(message);
    }

    private void handleMovieRatingEvent(MovieLikeMessageDto message) {
        log.info("영화 평점 메시지 수신: {}", message);
        // 평점 이벤트 처리 로직
    }
}
