package microservices.book.logs;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogsConsumer {
    @RabbitListener(queues = "logs.queue")
    public void log(final String msg,
                    @Header(name = "level") String level,
                    @Header(name = "amqp_appId") String appId) {
        System.out.println("Log Message: " + msg);
        Marker marker = MarkerFactory.getMarker(appId);
        switch (level) {
            case "INFO" -> log.info(marker, msg);
            case "ERROR" -> log.error(marker, msg);
            case "WARN" -> log.warn(marker, msg);
            case "DEBUG" -> log.debug(marker, msg);
        }
    }
}