package com.polar_moviechart.movieservice.domain.service.event;

import com.polar_moviechart.movieservice.domain.repository.MovieRepository;
import com.polar_moviechart.movieservice.domain.service.event.dto.MessageDto;
import com.polar_moviechart.movieservice.domain.service.event.dto.MovieLikeMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class MovieEventConsumer {
    private final MovieRepository movieRepository;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(MessageDto message) {
        log.info("메시지 수신: {}", message);

        switch (message.getType()) {
            case LIKE:
                handleMovieLikeEvent((MovieLikeMessageDto) message);
                break;
            case RATING:
                handleMovieRatingEvent(message);
                break;
            default:
                log.warn("올바르지 않은 메시지 타입: {}", message.getType());
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

    private void handleMovieRatingEvent(MessageDto message) {
        log.info("영화 평점 메시지 수신: {}", message);
        // 평점 이벤트 처리 로직
    }
}
