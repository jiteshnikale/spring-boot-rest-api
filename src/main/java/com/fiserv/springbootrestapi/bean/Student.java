package com.fiserv.springbootrestapi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Student {
    private int id;

    @Size(min = 2, message = "First Name should be at least 2 characters")
    private String firstName;

    @Size(min = 2, message = "Last Name should be at least 2 characters")
    private String lastName;

    //@Past @JsonIgnore
}
