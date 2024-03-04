package com.example.UsersMS.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String getTasksRouting;

    @Value("${user.existence.queue}")
    private String userExistenceQueue;

    @Value("${user.existence.response.queue}")
    private String userExistenceResponseQueue;

    @Value("${user.tasks.queue}")
    private String getUserTasksQueue;

    @Value("${user.tasksResponse.queue}")
    private String getUserTasksResponseQueue;


    @Value("${user.tasks.routingKey}")
    private String routingKey;

    @Bean
    public Queue getUserTasksQueue() {
        return new Queue(getUserTasksQueue);
    }

    @Bean
    public Queue getUserTasksResponseQueue() {
        return new Queue(getUserTasksResponseQueue);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding bindingUserTasks() {
        return BindingBuilder.bind(getUserTasksQueue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding UserTasksResponse() {
        return BindingBuilder.bind(getUserTasksResponseQueue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate newRabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setReplyTimeout(10000);
        rabbitTemplate.setReceiveTimeout(10000);
        return rabbitTemplate;
    }


}
