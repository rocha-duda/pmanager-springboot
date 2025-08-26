package com.rochaduda.pmanager.domain.entity;

import java.time.LocalDate;

import com.rochaduda.pmanager.domain.model.ProjectStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Estou dizendo que essa classe é uma entidade, o SB vai entender isso e vai ativar os mecanismos de persisntecia pra ela
@Table(name = "project")
@Data
@Builder //Cria uma classe com o nome Builder, ajuda a construir objetos mais complexos 
@AllArgsConstructor //Os contrutures passam a existir
@NoArgsConstructor // Tem construtor vazio também
public class Project {

    @Id //Toda entity tem um Id e precisamos indicar qual é 
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36) 
    private String id;

    @Column(name = "name", nullable = false, length = 80)  
    private String name;
    
    @Column(name = "description", nullable = false, length = 150) 
    private String description;
    
    @Column(name = "initial_date", nullable = false) 
    private LocalDate initialDate;
    
    @Column(name = "final_date", nullable = false) 
    private LocalDate finalDate;
    
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING) 
    private ProjectStatus status;

    

    

    
}
