package com.abhishek.jobtracker.repository;

import com.abhishek.jobtracker.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}