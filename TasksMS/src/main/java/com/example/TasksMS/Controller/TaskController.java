package com.example.TasksMS.Controller;

import com.example.TasksMS.Entity.DTO.TaskRequestDto;
import com.example.TasksMS.MyExceptions.*;
import com.example.TasksMS.Service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.TasksMS.Entity.Task;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private List<Task> tasks;
    private final TaskService taskService;
    // add CRUD methods
    // add ExceptionHandling
    // create custom exceptions NotFoundException, DataNotValidException, etc.
    // BONUS : Add Swagger documentation (OpenAPI)!
    //Read
    @GetMapping("/")
    public ResponseEntity<?> getAllTasks() throws NotFoundException {
        if (taskService.getAllTasks().isEmpty()) {
            throw new NotFoundException("No Task Found!");
        } else {
            return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) throws NotFoundException {
        if(taskService.isTaskIdExists(id)){
            return new ResponseEntity<>(taskService.getTaskById(id),HttpStatus.OK);
        }
        throw new NotFoundException("Task with id: "+id +" doesn't Exist!");
    }
    //Create
    @PostMapping("/")
    @Validated
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequestDto taskDto, BindingResult bindingResult) throws NotFoundException {

                return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }
    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto userDto) {
        try {
            return new ResponseEntity<>(taskService.updateTask(id,userDto), HttpStatus.ACCEPTED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    //Delete
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) throws NotFoundException {
        try {
            taskService.deleteTask(id);
            return "Task id: "+id+" deleted!";
        } catch (NotFoundException e) {
            throw new NotFoundException("Task Not Found!");
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable("userId") Long userId) throws NotFoundException {
        return new ResponseEntity<>(taskService.findByUserId(userId), HttpStatus.OK);
    }




}

