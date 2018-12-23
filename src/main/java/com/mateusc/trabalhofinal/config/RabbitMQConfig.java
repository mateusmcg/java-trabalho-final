package com.mateusc.trabalhofinal.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {
    public static final String SELL_QUEUE = "stock-exchange-sell-queue";
    public static final String BUY_QUEUE = "stock-exchange-buy-queue";
    private static final String EXCHANGE_MESSAGES = "messages-exchange";
    private static final String QUEUE_DEAD_MESSAGES = "dead-messages-queue";
 
    @Bean
    Queue sellQueue() {
    	return QueueBuilder.durable(SELL_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_MESSAGES)
                .withArgument("x-message-ttl", 60000) // Mensagem possui 1min de vida na fila original
                .build();
    }

    @Bean
    Queue buyQueue() {
    	return QueueBuilder.durable(BUY_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_MESSAGES)
                .withArgument("x-message-ttl", 60000) // Mensagem possui 1min de vida na fila original
                .build();
    }
 
    @Bean
    Queue deadMessagesQueue() {
        return QueueBuilder.durable(QUEUE_DEAD_MESSAGES).build();
    }
 
    @Bean
    Exchange messagesExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_MESSAGES).build();
    }
 
    @Bean
    Binding sellQueueBinding(TopicExchange messagesExchange) {
        return BindingBuilder.bind(sellQueue()).to(messagesExchange).with(sellQueue().getName());
    }

    @Bean
    Binding buyQueueBinding(TopicExchange messagesExchange) {
        return BindingBuilder.bind(buyQueue()).to(messagesExchange).with(buyQueue().getName());
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }
 
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar register) {
        register.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
 
    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }
 
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}