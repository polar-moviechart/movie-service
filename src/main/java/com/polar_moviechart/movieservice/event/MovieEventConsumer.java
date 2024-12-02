package com.polar_moviechart.movieservice.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polar_moviechart.movieservice.event.dto.MessageDto;
import com.polar_moviechart.movieservice.exception.ErrorCode;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import com.polar_moviechart.movieservice.repository.MovieRepository;
import com.polar_moviechart.movieservice.event.dto.MovieLikeMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class MovieEventConsumer {
    private final MovieRepository movieRepository;
    private final ObjectMapper objectMapper;

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
        // TODO: 실제 좋아요 눌렀는지 확인 검증. 중복 방지 등등
        log.info("영화 좋아요 메시지 수신: {}", message);
        // 좋아요 이벤트 처리 로직
        movieRepository.findByCode(message.getCode())
                .ifPresent(movie -> {
                    movie.addLikeCount(message.getValue());
                });
    }

    private void handleMovieRatingEvent(MovieLikeMessageDto message) {
        log.info("영화 평점 메시지 수신: {}", message);
        // 평점 이벤트 처리 로직
    }
}
