package com.dj.SpringBootJobsite.company;

import com.dj.SpringBootJobsite.company.impl.CompanyServiceimpl;
import com.dj.SpringBootJobsite.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company == null){
            return new ResponseEntity<>(null, org.springframework.http.HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/{id}/jobs")
    public ResponseEntity<List<Job>> getJobsByCompanyId(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company == null){
            return new ResponseEntity<>(null, org.springframework.http.HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(companyService.getJobsByCompanyId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCompany(@PathVariable Long id, @RequestBody Company company){
        if(companyService.getCompanyById(id) == null){
            return new ResponseEntity<>(false, org.springframework.http.HttpStatus.NOT_FOUND);
        }
        company.setId(id);
        boolean flg = companyService.updateCompany(company);
        return new ResponseEntity<>(flg, org.springframework.http.HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> createCompany(@RequestBody Company company){
        boolean flg = companyService.createCompany(company);
        return new ResponseEntity<>(flg, org.springframework.http.HttpStatus.CREATED);
    }

    @PostMapping("/{id}/job")
    public ResponseEntity<Boolean> addJob(@PathVariable Long id, @RequestBody Job job){
        Company company = companyService.getCompanyById(id);
        if(company == null){
            return new ResponseEntity<>(false, org.springframework.http.HttpStatus.NOT_FOUND);
        }
        company.addJob(job);
        boolean flg = companyService.updateCompany(company);
        return new ResponseEntity<>(flg, org.springframework.http.HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company == null){
            return new ResponseEntity<>(false, org.springframework.http.HttpStatus.NOT_FOUND);
        }
        companyService.deleteCompanyById(id);
        return new ResponseEntity<>(true, org.springframework.http.HttpStatus.OK);
    }
}
