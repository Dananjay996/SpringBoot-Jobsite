package com.dj.SpringBootJobsite.job.impl;

import com.dj.SpringBootJobsite.job.Job;
import com.dj.SpringBootJobsite.job.JobRepository;
import com.dj.SpringBootJobsite.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

//    private final List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        //return jobs.stream().filter(job -> job.getId().equals(id)).findFirst().orElse(null);
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteJob(Long id) {
        try {
            //jobs.removeIf(job -> job.getId().equals(id));
            jobRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Job not found");
        }
    }

    @Override
    public void updateJob(Long id, Job job){
//        jobs.stream().filter(j -> j.getId().equals(id)).forEach(j -> {
//            j.setTitle(job.getTitle());
//            j.setDescription(job.getDescription());
//            j.setLocation(job.getLocation());
//            j.setMinSalary(job.getMinSalary());
//            j.setMaxSalary(job.getMaxSalary());
//        });
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job j = jobOptional.get();
            j.setTitle(job.getTitle());
            j.setDescription(job.getDescription());
            j.setLocation(job.getLocation());
            j.setMinSalary(job.getMinSalary());
            j.setMaxSalary(job.getMaxSalary());
            jobRepository.save(j);
        }
    }
}
