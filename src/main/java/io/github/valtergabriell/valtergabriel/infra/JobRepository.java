package io.github.valtergabriell.valtergabriel.infra;

import io.github.valtergabriell.valtergabriel.application.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
