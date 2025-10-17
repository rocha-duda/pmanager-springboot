package com.rochaduda.pmanager.infrastructure.dto;


import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaveProjectDataDTO {

    @NotNull(message = "Name cannot be empty")
    @Size(min=1, max=80, message = "Invalid name")
    private final String name;

    @NotNull(message ="Description cannot be empty" )
    @Size(min=1, max=150, message="Invalid description")
    private final String description;

    @NotNull(message = "Inital date cannot be empty")
    private final LocalDate initialDate;

    @NotNull(message = "Final date cannot be empty")
    private final LocalDate finalDate;


    private final String status;

    private final Set<String> memberIds;

    @AssertTrue(message = "Dates are not consistent")
    private boolean isInitialDateBeforeFinalDate(){
        return initialDate.isBefore(finalDate);
    }
}
