package com.example.quiz2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "id cannot be empty")
    @Size(min = 5, max = 10, message = "id must be between 5-10")
    private String id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3, message = "name cannot be less than 3 letters")
    private String name;

    @NotNull(message = "age cannot be empty")
    @Min(value = 15, message = "age cannot be less than 15")
    private int age;

    @NotNull(message = "balance cannot be empty")
    @Min(value = 0, message = "balance cannot be less than zero")
    private double balance;

    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "//d[customer],[librarian]//d", message = "role must be either customer or librarian")
    private String role;
}
