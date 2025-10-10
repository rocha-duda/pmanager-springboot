package com.rochaduda.pmanager.domain.entity;


import java.util.Optional;

import com.rochaduda.pmanager.domain.model.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity 
@Data
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class Task {
    
    @Id  
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36) 
    private String id;

    @Column(name = "title", nullable = false, length = 80)  
    private String title;

    @Column(name = "number_of_days", nullable = false)  
    private Integer numberOfDays;
    
    @Column(name = "description", nullable = false, length = 150) 
    private String description;
    
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING) 
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assignedMember")
    private Member assignedMember;

}
