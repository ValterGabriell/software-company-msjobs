package io.github.valtergabriell.valtergabriel.infra;

import io.github.valtergabriell.valtergabriel.application.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByColaboratorId(Long colaboratorId);
}
