package com.rochaduda.pmanager.domain.applicationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.rochaduda.pmanager.domain.entity.Project;
import com.rochaduda.pmanager.domain.model.ProjectStatus;
import com.rochaduda.pmanager.domain.repository.ProjectRepository;
import com.rochaduda.pmanager.infrastructure.dto.SaveProjectDataDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

   

    private final ProjectRepository projectRepository;

    @Transactional
    public Project createProject(SaveProjectDataDTO saveProjectData) {
        Project project = Project.builder()
            .name(saveProjectData.getName())
            .description(saveProjectData.getDescription())
            .initialDate(saveProjectData.getInitialDate())
            .finalDate(saveProjectData.getFinalDate())
            .status(ProjectStatus.PENDING)
            .build();

        log.info("Project Created: {}", project);

        return projectRepository.save(project);
    }
}
