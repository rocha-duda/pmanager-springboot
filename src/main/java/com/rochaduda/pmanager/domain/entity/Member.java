package com.rochaduda.pmanager.domain.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "secret", nullable = false, length = 36)
    private String secret;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "email", nullable = false, length = 80)
    private String email;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ManyToMany(mappedBy = "members")
    private List<Project> projects;

}
