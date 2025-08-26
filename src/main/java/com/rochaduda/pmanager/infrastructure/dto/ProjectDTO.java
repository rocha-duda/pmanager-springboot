package com.rochaduda.pmanager.infrastructure.dto;

import java.time.LocalDate;

import com.rochaduda.pmanager.domain.entity.Project;
import com.rochaduda.pmanager.domain.model.ProjectStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDTO {

    private final String id;
    private final String name;
    private final String description;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final ProjectStatus status;

    public static ProjectDTO create(Project project) {
        return new ProjectDTO(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getInitialDate(),
            project.getFinalDate(),
            project.getStatus()
        );
    }
}
