package com.example.TasksMS.Entity;

import com.example.TasksMS.Utils.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", unique = true)
    @NotNull(message = "Title cannot be null!")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @NotNull(message = "Status cannot be null!")
    private Status status;

    @NotNull(message = "Due date cannot be null!")
    @Future(message = "Due date must be in the future!")
    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @NotNull(message = "User id cannot be null!")
    private Long userId;
}
