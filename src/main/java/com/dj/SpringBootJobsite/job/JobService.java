package com.dj.SpringBootJobsite.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    void deleteJob(Long id);

    void updateJob(Long id, Job job);
}
