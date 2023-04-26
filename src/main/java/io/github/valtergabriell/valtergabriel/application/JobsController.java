package io.github.valtergabriell.valtergabriel.application;

import io.github.valtergabriell.valtergabriel.application.domain.Job;
import io.github.valtergabriell.valtergabriel.application.domain.dto.CreateJobsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("job")
public class JobsController {

    private final JobService jobService;

    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(value = "/create", params = {"leadId", "colaboratorId"})
    public ResponseEntity<CreateJobsResponse> createNewJob(@RequestBody Job orderJob, @RequestParam("leadId") Long leadId, @RequestParam("colaboratorId") Long colaboratorId) {
        CreateJobsResponse createJobsResponse = jobService.createNewJob(orderJob, leadId, colaboratorId);
        return new ResponseEntity<>(createJobsResponse, HttpStatus.CREATED);
    }

}
