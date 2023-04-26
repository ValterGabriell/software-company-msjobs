package io.github.valtergabriell.valtergabriel.application.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateJobsResponse {
    private String message;
    private String jobId;
    private String url;
}
