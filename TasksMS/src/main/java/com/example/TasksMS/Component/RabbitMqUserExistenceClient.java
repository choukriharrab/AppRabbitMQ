package com.example.TasksMS.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMqUserExistenceClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${user.existence.queue}")
    private String userExistenceQueue;

    public Boolean checkUserExistence(Long userId) {
        Object response = rabbitTemplate.convertSendAndReceive(userExistenceQueue, userId);
        String stringValue = ""+response;
        return Boolean.parseBoolean(stringValue);
    }
}
