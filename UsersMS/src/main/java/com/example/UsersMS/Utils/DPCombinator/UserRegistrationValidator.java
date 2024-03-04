package com.example.UsersMS.Utils.DPCombinator;


import com.example.UsersMS.Entity.DTO.UserRequestDto;
import com.example.UsersMS.MyExceptions.DataNotValidException;
import com.example.UsersMS.MyExceptions.EmptyEntryException;

import java.util.function.Function;
import java.util.regex.Pattern;

public interface UserRegistrationValidator extends Function<UserRequestDto, Boolean> {
    static UserRegistrationValidator isEmailValid() throws DataNotValidException, EmptyEntryException, NullPointerException {
        // Regular expression pattern for validating email addresses
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}(?!\\.)[a-zA-Z]{2,}$";


        return  user-> Pattern.compile(emailPattern).matcher(user.getEmail()).matches();

    }
    static UserRegistrationValidator isValidFirstName() throws EmptyEntryException{

        return user -> !(user.getFirstName().isEmpty());
    }

    static UserRegistrationValidator isValidLastName(){

        return user ->  !(user.getFirstName().isEmpty());
    }



    default UserRegistrationValidator and(UserRegistrationValidator other) {
        return userRequestDto -> {
            Boolean result = this.apply(userRequestDto);
            return result.equals(true) || other.apply(userRequestDto);
        };
    }

    default UserRegistrationValidator or(UserRegistrationValidator other) {
        return userRequestDto -> {
            Boolean result = this.apply(userRequestDto);
            return result.equals(true) || other.apply(userRequestDto);
        };
    }

}
