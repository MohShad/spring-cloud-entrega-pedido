package br.com.entregapedido.entregaservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRabbitMQ {

    public static final String EXCHANGE_NAME = "exchange.entrega.pedido.producer";
    public static final String DEFAULT_PARSING_QUEUE = "queue.entrega.pedido.producer";
    public static final String ROUTING_KEY = "routingKey.entrega.pedido.producer";

    @Bean
    public TopicExchange tipsExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue defaultParsingQueue() {
        return new Queue(DEFAULT_PARSING_QUEUE);
    }

    @Bean
    public Binding queueToExchangeBinding() {
        return BindingBuilder.bind(defaultParsingQueue()).to(tipsExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}