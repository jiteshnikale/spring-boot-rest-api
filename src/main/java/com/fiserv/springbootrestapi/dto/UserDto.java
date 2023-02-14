package com.fiserv.springbootrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "User First Name should not be null or empty")
    private String firstName;
    @NotEmpty(message = "User Last Name should not be null or empty")
    private String lastName;

    @NotEmpty(message = "User Email should not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;
}
