package com.example.TasksMS.Utils;

import com.example.TasksMS.Entity.DTO.TaskRequestDto;
import com.example.TasksMS.Entity.DTO.TaskResponseDto;
import com.example.TasksMS.Entity.Task;
import org.modelmapper.ModelMapper;

public class MappingProfile {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Task mapToEntity(TaskRequestDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }

    public static TaskResponseDto mapToDto(Task task) {
        return modelMapper.map(task, TaskResponseDto.class);
    }
}

