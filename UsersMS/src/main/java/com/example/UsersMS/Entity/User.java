package com.example.UsersMS.Entity;

import com.example.UsersMS.Utils.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 255)
    @Column(name = "first_name", nullable = false)
    @NotNull(message = "First name cannot be null!")
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name", nullable = false)
    @NotNull(message = "Last name cannot be null!")
    private String lastName;

    @Size(max = 255)
    @Column(name = "email", unique = true)
    private String email;

    @Transient
    private List<Task> tasks;
}
