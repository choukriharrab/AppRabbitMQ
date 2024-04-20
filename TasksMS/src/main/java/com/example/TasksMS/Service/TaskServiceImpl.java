package com.example.TasksMS.Service;

import com.example.TasksMS.Component.RabbitMqUserExistenceClient;
import com.example.TasksMS.Entity.Task;
import com.example.TasksMS.Entity.DTO.TaskRequestDto;
import com.example.TasksMS.MyExceptions.AlreadyExistsException;
import com.example.TasksMS.MyExceptions.DataNotValidException;
import com.example.TasksMS.MyExceptions.EmptyEntryException;
import com.example.TasksMS.Utils.MappingProfile;
import com.example.TasksMS.Entity.DTO.TaskResponseDto;
import com.example.TasksMS.MyExceptions.NotFoundException;
import com.example.TasksMS.Repository.TaskRepo;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.TasksMS.Utils.DPCombinator.TaskRegistrationValidator.*;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{

    List<TaskResponseDto> tasks;

    @Autowired
    private final TaskRepo taskRepo;

    RabbitMqUserExistenceClient userExistenceClient;

    @RabbitListener(queues = "tasksQueue")
    @Transactional
    public void receiveMessage(Long userId) {
        taskRepo.deleteTasksByUserId(userId);
    }

    public boolean isTaskIdExists(Long taskId) {
        return taskRepo.existsById(taskId);
    }
    @Override
    public List<TaskResponseDto> getAllTasks() throws NotFoundException {
        if (taskRepo.findAll().isEmpty()) {
            throw new NotFoundException("No Task Found!");
        }else {
            return taskRepo.findAll().stream()
                    .map(MappingProfile::mapToDto)
                    .toList();
        }
    }
    @Override
    public TaskResponseDto createTask(TaskRequestDto taskDto) throws EmptyEntryException, DataNotValidException, NotFoundException {
        if (taskDto.getTitle().isEmpty()) throw new EmptyEntryException("Empty title entry!");
        if (taskDto.getStatus().describeConstable().isEmpty()) throw new EmptyEntryException("Empty status entry!");
        if (!isValidDueDate(taskDto.getDueDate().toString()).apply(taskDto)) throw new DataNotValidException("Invalid date format - Use yyyy-MM-dd instead!");
        if (taskDto.getUserId().describeConstable().isEmpty()) throw new EmptyEntryException("Empty User id entry!");
        if (!userExistenceClient.checkUserExistence(taskDto.getUserId())) throw new NotFoundException("User with id: "+taskDto.getUserId()+" NOT found!");
        var task = MappingProfile.mapToEntity(taskDto);
        try {
            return MappingProfile.mapToDto(taskRepo.save(task));
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("Title exists already!");
        }

    }
    @Override
    public TaskResponseDto getTaskById(Long id) throws NotFoundException {
            Task task = taskRepo.findById(id).orElseThrow(()->new NotFoundException("Task with id: "+id+" does NOT exist!"));
            return MappingProfile.mapToDto(task);
    }
    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskDto) throws NotFoundException, DataNotValidException {
        var task = taskRepo.findById(id).orElseThrow(()->new NotFoundException("Task with id: "+id+" does NOT exist!"));
        if (taskDto.getTitle()!=null)task.setTitle(taskDto.getTitle());
        if (taskDto.getDescription()!=null)task.setDescription(taskDto.getDescription());
        if (!userExistenceClient.checkUserExistence(taskDto.getUserId())) throw new NotFoundException("User with id: "+taskDto.getUserId()+" NOT found!");
        if (taskDto.getUserId()!=null)taskDto.setUserId(taskDto.getUserId());
        if (taskDto.getStatus()!=null)task.setStatus(taskDto.getStatus());
        if (isValidDueDate(taskDto.getDueDate().toString()).apply(taskDto)) throw new DataNotValidException("Invalid date format - Use yyyy-MM-dd instead!");
        if (task.getDueDate()!=null) task.setDueDate(task.getDueDate());
        return MappingProfile.mapToDto(taskRepo.save(task));
    }
    @Override
    @Transactional
    public void deleteTask(Long id) throws NotFoundException {
        if (taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
        }else {
            throw new NotFoundException("Task Not Found! (Can't delete nonexistent things!!)");
        }
    }
    @Override
    public List<TaskResponseDto> findByUserId(Long userId){
        return taskRepo.findByUserId(userId).stream()
                .map(MappingProfile::mapToDto)
                .toList();
    }

}