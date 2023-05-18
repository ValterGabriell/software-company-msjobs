package io.github.valtergabriell.valtergabriel.infra;

import io.github.valtergabriell.valtergabriel.application.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {
    void deleteAllByColaboratorId(Long colaboratorId);
    List<Job> findByColaboratorId(Long colaboratorId);

    List<Job> findByLeadId(Long leadId);

    @Query(value = "SELECT j FROM Job j WHERE j.isCanceled = true")
    List<Job> findAllCanceledJobs();

    @Query(value = "SELECT j FROM Job j WHERE j.isFinished = false")
    List<Job> findAllOpenJobs();

    @Query(value = "SELECT j FROM Job j WHERE j.isFinished = true")
    List<Job> findAllFinishedJobs();

    @Query(value = "SELECT j FROM Job j WHERE j.isCanceled = false")
    List<Job> findAllNotCanceledJobs();

}
