package io.github.valtergabriell.valtergabriel.application;

import io.github.valtergabriell.valtergabriel.application.domain.Job;
import io.github.valtergabriell.valtergabriel.application.domain.dto.CreateJobsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("job")
public class JobsController {

    private final JobService jobService;

    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(params = {"leadId", "colaboratorId"})
    public ResponseEntity<CreateJobsResponse> createNewJob(@RequestBody Job orderJob, @RequestParam("leadId") Long leadId, @RequestParam("colaboratorId") Long colaboratorId) {
        CreateJobsResponse createJobsResponse = jobService.createNewJob(orderJob, leadId, colaboratorId);
        return new ResponseEntity<>(createJobsResponse, HttpStatus.CREATED);
    }

    @GetMapping(params = {"colaboratorId"})
    public ResponseEntity<List<Job>> findAllJobsByColaborator(@RequestParam("colaboratorId") Long colaboratorId) {
        List<Job> allJobsFromColaborator = jobService.getAllJobsFromColaborator(colaboratorId);
        return new ResponseEntity<>(allJobsFromColaborator, HttpStatus.CREATED);
    }

}
