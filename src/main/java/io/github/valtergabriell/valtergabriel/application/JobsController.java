package io.github.valtergabriell.valtergabriel.application;

import io.github.valtergabriell.valtergabriel.application.domain.Job;
import io.github.valtergabriell.valtergabriel.application.domain.dto.CreateJobsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        return new ResponseEntity<>(allJobsFromColaborator, HttpStatus.OK);
    }

    @GetMapping(params = {"leadId"})
    public ResponseEntity<List<Job>> findAllJobsByLead(@RequestParam("leadId") Long leadId) {
        List<Job> allJobsFromLead = jobService.getAllJobsFromLead(leadId);
        return new ResponseEntity<>(allJobsFromLead, HttpStatus.OK);
    }


    @GetMapping(params = {"jobId"})
    public ResponseEntity<Job> findJobById(@RequestParam("jobId") String jobId) {
        Job job = jobService.getJobById(jobId);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/canceled")
    public ResponseEntity<List<Job>> findAllCanceledJobs() {
        List<Job> jobs = jobService.getAllCanceledJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/not-canceled")
    public ResponseEntity<List<Job>> findAllNotCanceledJobs() {
        List<Job> jobs = jobService.getAllNotCanceledJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/open")
    public ResponseEntity<List<Job>> findAllOpenJobs() {
        List<Job> jobs = jobService.getAllOpenJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/finished")
    public ResponseEntity<List<Job>> findAllFinishedJobs() {
        List<Job> jobs = jobService.getAllFinishedJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PutMapping(params = {"jobId", "finishDate"})
    public ResponseEntity<Job> updateFinishDateJob(@RequestParam String jobId, @RequestParam LocalDate finishDate) {
        Job job = jobService.updateFinishDateJob(jobId, finishDate);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PutMapping(value = "/finished", params = {"jobId"})
    public ResponseEntity<Job> updateFinishedJob(@RequestParam String jobId) {
        Job job = jobService.updateFinishedJob(jobId);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PutMapping(value = "/canceled", params = {"jobId"})
    public ResponseEntity<Job> updateCanceledJob(@RequestParam String jobId) {
        Job job = jobService.updateCanceledJob(jobId);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping(params = {"jobId"})
    public ResponseEntity<?> deleteJob(@RequestParam String jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "delete-all-jobs-by-colaborator", params = {"colaboratorId"})
    public ResponseEntity<?> deleteAllJobsByColaboratorId(@RequestParam Long colaboratorId) {
        jobService.deleteAllJobsByColaboratorId(colaboratorId);
        return ResponseEntity.noContent().build();
    }


}
