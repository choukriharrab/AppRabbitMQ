package com.example.UsersMS.Service;

import com.example.UsersMS.Entity.DTO.UserRequestDto;
import com.example.UsersMS.Entity.DTO.UserResponseDto;
import com.example.UsersMS.MyExceptions.AlreadyExistsException;
import com.example.UsersMS.MyExceptions.EmptyEntryException;
import com.example.UsersMS.MyExceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers() throws NotFoundException;
    UserResponseDto createUser(UserRequestDto userDto) throws AlreadyExistsException;
    void deleteUser(Long id) throws  NotFoundException, EmptyEntryException;
    Object getUserById(Long id) throws  NotFoundException, EmptyEntryException ;
    UserResponseDto updateUser(Long id, UserRequestDto userDto) throws NotFoundException;


    }
