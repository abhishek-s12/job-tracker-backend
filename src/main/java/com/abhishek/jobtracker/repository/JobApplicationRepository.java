package com.abhishek.jobtracker.repository;
import java.util.Optional;
import com.abhishek.jobtracker.entity.Job;
import com.abhishek.jobtracker.entity.User;
import java.util.List;
import com.abhishek.jobtracker.entity.User;
import java.util.List;
import com.abhishek.jobtracker.entity.Job;

import com.abhishek.jobtracker.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {
            Optional<JobApplication>
            findByUserAndJob(User user, Job job);
            List<JobApplication> findByUser(User user);
            List<JobApplication> findByJob(Job job);
}