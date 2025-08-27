package com.rochaduda.pmanager.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rochaduda.pmanager.domain.applicationservice.ProjectService;
import com.rochaduda.pmanager.domain.entity.Project;
import com.rochaduda.pmanager.infrastructure.dto.ProjectDTO;
import com.rochaduda.pmanager.infrastructure.dto.SaveProjectDataDTO;

import jakarta.validation.Valid;

import static com.rochaduda.pmanager.infrastructure.controller.RestConstants.PATH_PROJECTS;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(PATH_PROJECTS)
@RequiredArgsConstructor
public class ProjectRestResource {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid SaveProjectDataDTO saveProjectDataDTO){
       Project project = projectService.createProject(saveProjectDataDTO);

       return ResponseEntity
        .created(URI.create(PATH_PROJECTS + "/" + project.getId()))
        .body(ProjectDTO.create(project));
    }
}
