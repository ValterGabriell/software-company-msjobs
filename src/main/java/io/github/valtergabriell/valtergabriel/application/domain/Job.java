package io.github.valtergabriell.valtergabriel.application.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private Boolean isFinished;
    private Boolean wantDelete;
    private Boolean isCanceled;
    private LocalDate creationDay;
    private LocalDate finishDay;
    private Long leadId;
    private Long colaboratorId;

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public Long getColaboratorId() {
        return colaboratorId;
    }

    public void setColaboratorId(Long colaboratorId) {
        this.colaboratorId = colaboratorId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public Boolean getWantDelete() {
        return wantDelete;
    }

    public void setWantDelete(Boolean wantDelete) {
        this.wantDelete = wantDelete;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public LocalDate getCreationDay() {
        return creationDay;
    }

    public void setCreationDay(LocalDate creationDay) {
        this.creationDay = creationDay;
    }

    public LocalDate getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(LocalDate finishDay) {
        this.finishDay = finishDay;
    }

    public void toDomain(Job job) {
        this.creationDay = job.creationDay;
        this.creationDay = job.creationDay;
    }

}
