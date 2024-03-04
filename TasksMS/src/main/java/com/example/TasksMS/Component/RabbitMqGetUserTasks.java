package com.example.TasksMS.Component;

import com.example.TasksMS.Entity.DTO.TaskResponseDto;
import com.example.TasksMS.Service.TaskService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMqGetUserTasks {

    @Autowired
    private TaskService taskService;

    @RabbitListener(queues = "${user.tasks.queue}")
    @SendTo("${user.tasksResponse.queue}")
    public Object getUserTasks(Long userId) {
        List<TaskResponseDto> tasks = taskService.findByUserId(userId);
        return tasks;
    }
}