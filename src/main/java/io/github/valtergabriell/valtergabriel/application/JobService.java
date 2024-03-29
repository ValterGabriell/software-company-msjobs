package io.github.valtergabriell.valtergabriel.application;


import io.github.valtergabriell.valtergabriel.application.domain.Job;
import io.github.valtergabriell.valtergabriel.application.domain.dto.Colaborator;
import io.github.valtergabriell.valtergabriel.application.domain.dto.CreateJobsResponse;
import io.github.valtergabriell.valtergabriel.application.domain.dto.Lead;
import io.github.valtergabriell.valtergabriel.application.domain.dto.Response;
import io.github.valtergabriell.valtergabriel.exception.RequestExceptions;
import io.github.valtergabriell.valtergabriel.infra.JobRepository;
import io.github.valtergabriell.valtergabriel.infra.api.ColaboratorApiConnection;
import io.github.valtergabriell.valtergabriel.infra.api.LeadApiConnection;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepo;
    private final LeadApiConnection leadApiConnection;
    private final ColaboratorApiConnection colaboratorApiConnection;

    public JobService(JobRepository jobRepo, LeadApiConnection leadApiConnection, ColaboratorApiConnection colaboratorApiConnection) {
        this.jobRepo = jobRepo;
        this.leadApiConnection = leadApiConnection;
        this.colaboratorApiConnection = colaboratorApiConnection;
    }


    /**
     * Method to create a job for specific manager and employer
     *
     * @param orderJob object that receive cnpj of manager and cpf of employer that has to do the work
     * @return object resCreatedJobs that represents the manager of job and the employer that jobs has associated
     */
    public CreateJobsResponse createNewJob(Job orderJob, Long leadId, Long colaboratorId) {
        throwingErroWhenLeadOrColaboratorAreNotValid(leadId, colaboratorId);

        LocalDate localDate = LocalDate.now();
        if (grantThatFinishDateIsBiggerThanCreationDate(localDate, orderJob.getFinishDay())) {
            orderJob.setCreationDay(localDate);
            orderJob.setFinished(false);
            orderJob.setCanceled(false);
            orderJob.setLeadId(leadId);
            orderJob.setColaboratorId(colaboratorId);
        } else {
            throw new RequestExceptions("Dia de finalização tem que ser maior do que o dia de criação do trabalho");
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

    public Job getJobById(String jobId) {
        return jobRepo.findById(jobId).orElseThrow(() -> new RequestExceptions("Trabalho não encontrado!"));
    }

    public List<Job> getAllJobsFromColaborator(Long colaboratorId) {
        return jobRepo.findByColaboratorId(colaboratorId);
    }

    private void throwingErroWhenLeadOrColaboratorAreNotValid(Long leadId, Long colaboratorId) {
        Response<Colaborator> colaboratorResponse = verifyIfColaboratorIsValid(colaboratorId);
        Response<Lead> leadResponse = verifyIfLeadIsValid(leadId);

        if (colaboratorResponse.getData() == null) {
            throw new RequestExceptions(colaboratorResponse.getMessage());
        }

        if (leadResponse.getData() == null) {
            throw new RequestExceptions(colaboratorResponse.getMessage());
        }
    }

    private Boolean grantThatFinishDateIsBiggerThanCreationDate(LocalDate date, LocalDate finishDate) {
        return finishDate.isAfter(date);
    }

    private Response<Colaborator> verifyIfColaboratorIsValid(Long cpf) {
        return colaboratorApiConnection.getColaboratorById(cpf);
    }

    private Response<Lead> verifyIfLeadIsValid(Long cnpj) {
        return leadApiConnection.findLeadById(cnpj);
    }

    public List<Job> getAllJobsFromLead(Long leadId) {
        return jobRepo.findByLeadId(leadId);
    }

    public List<Job> getAllCanceledJobs() {
        return jobRepo.findAllCanceledJobs();
    }

    public List<Job> getAllNotCanceledJobs() {
        return jobRepo.findAllNotCanceledJobs();
    }

    public List<Job> getAllFinishedJobs() {
        return jobRepo.findAllFinishedJobs();
    }

    public List<Job> getAllOpenJobs() {
        return jobRepo.findAllOpenJobs();
    }

    public void deleteJob(String jobId) {
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new RequestExceptions("Trabalho não encontrado!"));
        jobRepo.delete(job);
    }

    public Job updateFinishDateJob(String jobId, LocalDate newFinisheDate) {
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new RequestExceptions("Trabalho não encontrado!"));
        if (job.getFinished() || job.getCanceled()){
            throw new RequestExceptions("Não se pode atualizar a data final de um trabalho que está finalizado ou cancelado!!!");
        }
        job.setFinishDay(newFinisheDate);
        jobRepo.save(job);
        return job;
    }

    public Job updateFinishedJob(String jobId) {
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new RequestExceptions("Trabalho não encontrado!"));
        if (job.getCanceled()){
            throw new RequestExceptions("Não se pode atualizar a finalização um trabalho que está cancelado!!!");
        }
        job.setFinished(!job.getFinished());
        jobRepo.save(job);
        return job;
    }

    public Job updateCanceledJob(String jobId) {
        Job job = jobRepo.findById(jobId).orElseThrow(() -> new RequestExceptions("Trabalho não encontrado!"));
        if (job.getFinished()){
            throw new RequestExceptions("Não se pode atualizar o cancelamento de um trabalho que está finalizado!!!");
        }
        job.setCanceled(!job.getCanceled());
        jobRepo.save(job);
        return job;
    }

    public void deleteAllJobsByColaboratorId(Long colaboratorId) {
        List<Job> jobs = jobRepo.findAll().stream().filter(j -> j.getColaboratorId().equals(colaboratorId)).toList();
        jobRepo.deleteAll(jobs);
    }
}
