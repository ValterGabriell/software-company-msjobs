package io.github.valtergabriell.valtergabriel.application;


import io.github.valtergabriell.valtergabriel.application.domain.Job;
import io.github.valtergabriell.valtergabriel.application.domain.dto.CreateJobsResponse;
import io.github.valtergabriell.valtergabriel.exception.RequestException;
import io.github.valtergabriell.valtergabriel.infra.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

@Service
public class JobService {

    private final JobRepository jobRepo;

    public JobService(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }


    /**
     * Method to create a job for specific manager and employer
     *
     * @param orderJob object that receive cnpj of manager and cpf of employer that has to do the work
     * @return object resCreatedJobs that represents the manager of job and the employer that jobs has associated
     */
    public CreateJobsResponse createNewJob(Job orderJob, String leadId, String colaboratorId) {
        LocalDate localDate = LocalDate.now();
        if (grantThatFinishDateIsBiggerThanCreationDate(localDate, orderJob.getFinishDay())) {
            orderJob.setCreationDay(localDate);
            orderJob.setFinished(false);
            orderJob.setCanceled(false);
            orderJob.setWantDelete(false);
            orderJob.setLeadId(leadId);
            orderJob.setColaboratorId(colaboratorId);
        } else {
            throw new RequestException("Dia de finalização tem que ser maior do que o dia de criação do trabalho");
        }
        jobRepo.save(orderJob);

        CreateJobsResponse createJobsResponse = new CreateJobsResponse();
        createJobsResponse.setMessage("Trabalho para o colaborador com identificação " + orderJob.getColaboratorId() + " criado. Líder responsável: " + orderJob.getLeadId());
        String jobId = orderJob.getId();
        createJobsResponse.setJobId(jobId);
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().query("jobId=" + jobId).build().toUriString();
        createJobsResponse.setUrl(uri);
        return createJobsResponse;
    }

    private Boolean grantThatFinishDateIsBiggerThanCreationDate(LocalDate date, LocalDate finishDate) {
        return finishDate.isAfter(date);
    }
}
