package com.example.TasksMS.Utils.DPCombinator;


import com.example.TasksMS.Entity.DTO.TaskRequestDto;
import com.example.TasksMS.MyExceptions.DataNotValidException;
import com.example.TasksMS.MyExceptions.EmptyEntryException;

import java.text.SimpleDateFormat;
import java.util.function.Function;
import java.sql.Date;


public interface TaskRegistrationValidator extends Function<TaskRequestDto, Boolean> {

    static TaskRegistrationValidator isEmptyStatus() throws EmptyEntryException{
        return taskRequestDto ->{
            if (taskRequestDto.getStatus().describeConstable().isEmpty()){
                throw new EmptyEntryException("Status can't be empty!");
            }
            return true;
        };
    }
    static TaskRegistrationValidator isEmptyTitle() throws EmptyEntryException{
        return taskRequestDto->{
        if (taskRequestDto.getTitle().isEmpty()) {
            throw new EmptyEntryException("Title can't be empty!");
        }
        return true;
        };
    }
    static TaskRegistrationValidator isValidDueDate(String dueDate) throws DataNotValidException{
        return taskRequestDto -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                Date date = Date.valueOf(dueDate);
                // Check if the parsed date matches the input string
                return dueDate.equals(date.toString());
            } catch (IllegalArgumentException e) {
                return false; // Parsing failed
            }

        };
    }

    default TaskRegistrationValidator and(TaskRegistrationValidator other) {
        return taskRequestDto -> {
            Boolean result = this.apply(taskRequestDto);
            return result.equals(true) || other.apply(taskRequestDto);
        };
    }
    //Not used just for future reference:
    default TaskRegistrationValidator or(TaskRegistrationValidator other) {
        return taskRequestDto -> {
            Boolean result = this.apply(taskRequestDto);
            return result.equals(true) || other.apply(taskRequestDto);
        };
    }

}
