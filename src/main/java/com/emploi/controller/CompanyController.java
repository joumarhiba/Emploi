package com.emploi.controller;

import com.emploi.helpers.FileUploadUtil;
import com.emploi.model.Company;
import com.emploi.model.Offre;
import com.emploi.repository.CompanyRepo;
import com.emploi.service.CompanyService;
import com.emploi.service.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Company")
@RequiredArgsConstructor

public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepo companyRepo;

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllOffres(){
        List<Company> companies = companyService.findAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) throws Exception {
        Company savedCompany = companyRepo.save(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }
}
