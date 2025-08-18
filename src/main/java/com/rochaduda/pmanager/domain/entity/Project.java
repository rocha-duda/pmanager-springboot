package com.rochaduda.pmanager.domain.entity;

import java.time.LocalDate;

import com.rochaduda.pmanager.domain.model.ProjectStatus;

public class Project {

    private String id; 
    private String name;
    private String description;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private ProjectStatus status;

    
    
    public Project(String id, String name, String description, LocalDate initialDate, LocalDate finalDate,
            ProjectStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.status = status;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getInitialDate() {
        return initialDate;
    }
    public LocalDate getFinalDate() {
        return finalDate;
    }
    public ProjectStatus getStatus() {
        return status;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }
    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }
    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((initialDate == null) ? 0 : initialDate.hashCode());
        result = prime * result + ((finalDate == null) ? 0 : finalDate.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (initialDate == null) {
            if (other.initialDate != null)
                return false;
        } else if (!initialDate.equals(other.initialDate))
            return false;
        if (finalDate == null) {
            if (other.finalDate != null)
                return false;
        } else if (!finalDate.equals(other.finalDate))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

    

    
}
