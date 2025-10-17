package com.rochaduda.pmanager.infrastructure.dto;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.rochaduda.pmanager.domain.entity.Project;
import com.rochaduda.pmanager.domain.model.ProjectStatus;
import com.rochaduda.pmanager.domain.entity.Member;

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
    private final Set<String> membersIds;

    public static ProjectDTO create(Project project) {
        return new ProjectDTO(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getInitialDate(),
            project.getFinalDate(),
            project.getStatus(),
            Optional
                .ofNullable(project.getMembers())
                .orElse(List.of())
                .stream()
                .map(Member::getId)
                .collect(Collectors.toSet())
        );
    }
}
