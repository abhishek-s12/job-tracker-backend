package com.abhishek.jobtracker.service;

import java.util.List;
import com.abhishek.jobtracker.entity.User;

import com.abhishek.jobtracker.entity.Job;
import com.abhishek.jobtracker.entity.JobApplication;
import com.abhishek.jobtracker.entity.User;
import com.abhishek.jobtracker.repository.JobApplicationRepository;
import com.abhishek.jobtracker.repository.JobRepository;
import com.abhishek.jobtracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.abhishek.jobtracker.exception.DuplicateApplicationException;
import java.time.LocalDateTime;



@Service
public class JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public JobApplicationService(
            JobApplicationRepository applicationRepository,
            UserRepository userRepository,
            JobRepository jobRepository) {

        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }
    public List<JobApplication> getApplicationsByUser(
        Long userId) {

    User user = userRepository.findById(userId)
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found"));

    return applicationRepository.findByUser(user);
}

    public JobApplication apply(Long userId, Long jobId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (applicationRepository
            .findByUserAndJob(user, job)
            .isPresent()) {

        throw new DuplicateApplicationException(
                "You have already applied for this job");
        }

        JobApplication application = JobApplication.builder()
                .user(user)
                .job(job)
                .appliedAt(LocalDateTime.now())
                .build();

        return applicationRepository.save(application);
    }

    public List<JobApplication> getApplicantsByJob(
        Long jobId) {

    Job job = jobRepository.findById(jobId)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Job not found"));

    return applicationRepository.findByJob(job);
}
}