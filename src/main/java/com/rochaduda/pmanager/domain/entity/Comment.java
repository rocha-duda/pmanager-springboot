package com.rochaduda.pmanager.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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


public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "description", nullable = false, length = 300) 
    private String description;

    @Column(name = "date", nullable = false, length = 300) 
    @Builder.Default
    private LocalDateTime commenLocalDateTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author")
    private Member author;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task associatedTask;
}   
