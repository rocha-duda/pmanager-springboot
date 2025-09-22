package com.rochaduda.pmanager.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaveTaskDataDTO {
    
    @NotNull(message = "title cannot be empty")
    @Size(min=1, max=80, message = "Invalid title")
    private final String title;

    @NotNull(message ="Description cannot be empty" )
    @Size(min=1, max=150, message="Invalid description")
    private final String description;

    @NotNull(message = "number of days cannot be empty")
    @Positive(message = "number of days must be positive")
    private final Integer numberOfDays;

    private final String status;

}
