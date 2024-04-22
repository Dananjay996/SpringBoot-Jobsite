package com.dj.SpringBootJobsite.company.impl;

import com.dj.SpringBootJobsite.company.Company;
import com.dj.SpringBootJobsite.company.CompanyController;
import com.dj.SpringBootJobsite.company.CompanyRepository;
import com.dj.SpringBootJobsite.company.CompanyService;
import com.dj.SpringBootJobsite.job.Job;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceimpl implements CompanyService {

    CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceimpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElse(null);
    }

    public List<Job> getJobsByCompanyId(Long id){
        Company company = companyRepository.findById(id).orElse(null);
        if(company == null){
            return null;
        }
        return company.getJobs();
    }

    @Override
    public boolean updateCompany(Company company) {
        if(companyRepository.findById(company.getId()).orElse(null) == null){
            return false;
        }
        Company currentCompany = companyRepository.findById(company.getId()).orElse(null);
        assert currentCompany != null;
        currentCompany.setName(company.getName());
        currentCompany.setDescription(company.getDescription());
        currentCompany.setJobs(company.getJobs());
        companyRepository.save(currentCompany);
        return true;
    }

    @Override
    public boolean createCompany(Company company) {
        companyRepository.save(company);
        return true;
    }

    @Override
    public void deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        if(company == null){
            throw new RuntimeException("Company not found");
        }

        companyRepository.delete(company);
    }
}
