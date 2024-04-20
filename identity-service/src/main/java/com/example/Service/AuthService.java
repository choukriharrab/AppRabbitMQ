package com.example.Service;

import com.example.Entity.UserCredential;
import com.example.Repository.UserCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserCredentialRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String saveUser(UserCredential credential){
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repo.save(credential);
        return "User added successfully!";
    }
}
