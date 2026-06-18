package com.abhishek.jobtracker.controller;

import com.abhishek.jobtracker.entity.JobApplication;
import com.abhishek.jobtracker.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService applicationService;

    public JobApplicationController(
            JobApplicationService applicationService) {

        this.applicationService = applicationService;
    }

    @PostMapping("/{jobId}/apply/{userId}")
    public JobApplication apply(
            @PathVariable Long jobId,
            @PathVariable Long userId) {

        return applicationService.apply(
                userId,
                jobId
        );
    }

    @GetMapping("/user/{userId}")
    public List<JobApplication> getApplicationsByUser(
            @PathVariable Long userId) {

        return applicationService
                .getApplicationsByUser(userId);
    }
    @GetMapping("/job/{jobId}")
    public List<JobApplication> getApplicantsByJob(
        @PathVariable Long jobId) {

    return applicationService
            .getApplicantsByJob(jobId);
}
}