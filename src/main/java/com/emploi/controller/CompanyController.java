package com.emploi.controller;

import com.emploi.model.Company;
import com.emploi.model.Offre;
import com.emploi.service.CompanyService;
import com.emploi.service.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Company")
@RequiredArgsConstructor

public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllOffres(){
        List<Company> companies = companyService.findAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
        Company saveCompany = companyService.addCompany(company);
        System.out.println(saveCompany);
        return new ResponseEntity<>(saveCompany, HttpStatus.CREATED);
    }
}
