package microservices.book.multiplication.challenge;

//import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class ChallengeEventPub {

//    private final AmqpTemplate amqpTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final String challengesTopicExchange;

//    public ChallengeEventPub(final AmqpTemplate amqpTemplate,
//                             @Value("${amqp.exchange.attempts}")
//                             final String challengesTopicExchange) {
//        this.amqpTemplate = amqpTemplate;
//        this.challengesTopicExchange = challengesTopicExchange;
//    }

    public ChallengeEventPub(final RabbitTemplate rabbitTemplate,
                             @Value("${amqp.exchange.attempts}")
                             final String challengesTopicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.challengesTopicExchange = challengesTopicExchange;
    }

    public void challengeSolved(final ChallengeAttempt challengeAttempt) {
            ChallengeSolvedEvent event = buildEvent(challengeAttempt);
            // Routing Key is 'attempt.correct' or 'attempt.wrong'
            String routingKey = "attempt." + (event.isCorrect() ?
                    "correct" : "wrong");
//        amqpTemplate.convertAndSend(challengesTopicExchange,
//                routingKey,
//                event);
//            MessageProperties properties = MessagePropertiesBuilder.newInstance()
//                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
//                    .build();
//
//            rabbitTemplate.getMessageConverter().toMessage(challengeAttempt, properties);
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
            return message;
        };
            try {
                rabbitTemplate.convertAndSend(challengesTopicExchange, routingKey, event, messagePostProcessor);
            } catch (RuntimeException ce){
                System.err.println("Received 503 Service Unavailable: " + ce.getMessage());
                throw new ServiceUnavailableException("External API is down for maintenance.");
            }
//        rabbitTemplate.convertAndSend(challengesTopicExchange, routingKey, event);
    }

    private ChallengeSolvedEvent buildEvent(final ChallengeAttempt attempt) {
        return new ChallengeSolvedEvent(attempt.getId(),
                attempt.isCorrect(), attempt.getFactorA(),
                attempt.getFactorB(), attempt.getUser().getId(),
                attempt.getUser().getAlias());
    }
}