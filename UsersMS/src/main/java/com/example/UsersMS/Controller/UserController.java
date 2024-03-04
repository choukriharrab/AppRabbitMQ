package com.example.UsersMS.Controller;

import com.example.UsersMS.Client.TaskClient;
import com.example.UsersMS.Entity.DTO.UserRequestDto;
import com.example.UsersMS.Entity.DTO.UserResponseDto;
import com.example.UsersMS.Entity.User;
import com.example.UsersMS.MyExceptions.*;
import com.example.UsersMS.Service.UserServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@Builder
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserServiceImp userService;
    @Autowired
    private final TaskClient taskClient;
    private UserResponseDto user;
    private List<User> users;
    @Autowired
    public UserController(UserServiceImp userService, TaskClient taskClient) {
        this.userService = userService;
        this.taskClient = taskClient;
    }


    //Read
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() throws NotFoundException {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }


    //Create
    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDto userDto) throws AlreadyExistsException, DataNotValidException {
            return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userDto) throws NotFoundException, DataNotValidException {
            return new ResponseEntity<>(userService.updateUser(id,userDto), HttpStatus.ACCEPTED);
    }
    //Delete
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) throws NotFoundException {
            userService.deleteUser(id);
            return "User id: "+id+" deleted!";
    }
}


