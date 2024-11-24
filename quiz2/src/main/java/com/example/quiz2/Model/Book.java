package com.example.quiz2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Singular;

@Data
@AllArgsConstructor
public class Book {
    @NotEmpty(message = "ID cannot be null")
    @Size(min = 5, max = 10, message = "id must be between 5-10")
    private String id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3, message = "name cannot be less than 3 letters")
    private String name;

    @NotNull(message = "number of pages cannot be empty")
    @Min(value = 0, message = "number of pages cannot be less than zero")
    private int number_of_pages;

    @NotNull(message = "price cannot be empty")
    @Min(value = 0, message = "price cannot be less than zero")
    private double price;

    @NotEmpty(message = "category cannot be empty")
    //@Pattern(regexp = "[novel][academic]", message = "category must be either novel or academic")
    private String category;

    @NotNull(message = "availability cannot be empty")
    @AssertTrue(message = "must be available")
    private boolean isAvailable;
}
