package com.example.UsersMS.Component;

import com.example.UsersMS.Utils.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMqGetUserTasks {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Value("${user.tasks.queue}")
    private String getUserTasksQueue;

    @Value("${user.tasks.routingKey}")
    private String getUserTasksRoutingKey;


    public List<Task> getUserTasks(Long userId, String getUserTasksRoutingKey) {
        System.out.println("hadi raha getUser");
//        CorrelationData correlationData = new CorrelationData();
//        Object message = rabbitTemplate.convertSendAndReceive(getUserTasksQueue, userId, correlationData);
        System.out.println("safi daz");
//        return (List<Task>) message;

        // Send a message and wait for a response
        Message responseMessage = rabbitTemplate.sendAndReceive("tasksExchange", getUserTasksRoutingKey, rabbitTemplate.getMessageConverter().toMessage(userId, null));
        // Process the response message
        if (responseMessage != null && responseMessage.getBody() != null) {
            // Deserialize the response body into a list of strings
            return (List<Task>) rabbitTemplate.getMessageConverter().fromMessage(responseMessage);
        } else {
            return null;
        }


    }

    public List<Task> call(String message, String routingKey) {
        // Send a message and wait for a response
        Message responseMessage = rabbitTemplate.sendAndReceive("tasksExchange", routingKey, rabbitTemplate.getMessageConverter().toMessage(message, null));
        // Process the response message
        if (responseMessage != null && responseMessage.getBody() != null) {
            // Deserialize the response body into a list of strings
            return (List<Task>) rabbitTemplate.getMessageConverter().fromMessage(responseMessage);
        } else {
            return null;
        }
    }
}
