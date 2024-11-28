package com.polar_moviechart.movieservice.domain.service.event;

import com.polar_moviechart.movieservice.domain.service.event.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MovieEventConsumer {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(MessageDto message) {
        log.info("메시지 수신: {}", message);

        // 메시지 타입에 따라 적절히 처리
        switch (message.getType()) {
            case LIKE:
                handleMovieLikeEvent(message);
                break;
            case RATING:
                handleMovieRatingEvent(message);
                break;
            default:
                log.warn("올바르지 않은 메시지 타입: {}", message.getType());
        }
    }

    private void handleMovieLikeEvent(MessageDto message) {
        log.info("Processing movie like event: {}", message);
        // 좋아요 이벤트 처리 로직
    }

    private void handleMovieRatingEvent(MessageDto message) {
        log.info("Processing movie rating event: {}", message);
        // 평점 이벤트 처리 로직
    }
}
