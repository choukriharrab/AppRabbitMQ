package com.example.TasksMS.Service;


import com.example.TasksMS.Entity.DTO.TaskRequestDto;
import com.example.TasksMS.Entity.DTO.TaskResponseDto;
import com.example.TasksMS.MyExceptions.AlreadyExistsException;
import com.example.TasksMS.MyExceptions.NotFoundException;

import java.util.List;

public interface TaskService {
    public boolean isTaskIdExists(Long taskId);
    List<TaskResponseDto> getAllTasks() throws NotFoundException;
    TaskResponseDto createTask(TaskRequestDto taskDto) throws AlreadyExistsException, NotFoundException;
    TaskResponseDto getTaskById(Long id) throws NotFoundException;
    TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws NotFoundException;
    void deleteTask(Long id) throws NotFoundException;
    List<TaskResponseDto> findByUserId(Long userId);
}