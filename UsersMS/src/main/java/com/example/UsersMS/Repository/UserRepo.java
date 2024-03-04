package com.example.UsersMS.Repository;

import com.example.UsersMS.Entity.User;
import com.example.UsersMS.MyExceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public void deleteUserById(Long id) throws NotFoundException;

}
