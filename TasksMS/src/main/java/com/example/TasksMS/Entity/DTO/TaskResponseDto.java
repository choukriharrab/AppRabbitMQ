package com.example.TasksMS.Entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private String status;
    private Date dueDate;

}