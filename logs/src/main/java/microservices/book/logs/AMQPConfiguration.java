package microservices.book.logs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Primary;

@Configuration
public class AMQPConfiguration {
    @Primary
    @Bean
    public TopicExchange logsExchange() {
        return ExchangeBuilder.topicExchange("logs.topic")
                .durable(true)
                .build();
    }
    @Bean
    public Queue logsQueue() {
        return QueueBuilder.durable("logs.queue").build();
    }

    @Bean
    public Binding logsBinding(final Queue logsQueue,
                               final TopicExchange logsExchange) {
        return BindingBuilder.bind(logsQueue)
                .to(logsExchange).with("#");
    }

}