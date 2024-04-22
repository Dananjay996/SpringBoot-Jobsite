package com.dj.SpringBootJobsite.company;

import com.dj.SpringBootJobsite.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public interface CompanyService {
    public List<Company> getAllCompanies();
    public Company getCompanyById(Long id);

    public List<Job> getJobsByCompanyId(Long id);

    public boolean updateCompany(Company company);

    public boolean createCompany(Company company);

    public void deleteCompanyById(Long id);
}
