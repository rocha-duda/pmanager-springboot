package com.rochaduda.pmanager.infrastructure.dto;


import java.time.LocalDate;



import lombok.Data;

@Data
public class SaveProjectDataDTO {
    
    private final String name;
    private final String description;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final String status;
}
