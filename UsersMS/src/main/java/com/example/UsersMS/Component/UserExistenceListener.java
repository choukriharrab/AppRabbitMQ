package com.example.UsersMS.Component;

import com.example.UsersMS.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserExistenceListener {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @RabbitListener(queues = "${user.existence.queue}")
    @SendTo("${user.existence.response.queue}")
    public String checkUserExistence(Long userId) {
        String response = String.valueOf(userRepo.existsById(userId));
        return response;
    }
}
