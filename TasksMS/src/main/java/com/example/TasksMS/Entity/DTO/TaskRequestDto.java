package com.example.TasksMS.Entity.DTO;
import com.example.TasksMS.Utils.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private Status status;
    private Date dueDate;
}