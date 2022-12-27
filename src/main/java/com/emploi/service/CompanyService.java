package com.emploi.service;

import com.emploi.model.Company;
import com.emploi.model.Offre;
import com.emploi.repository.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService implements UserDetailsService{

    private final CompanyRepo companyRepo;

    public Company addCompany(Company company){
        return companyRepo.save(company);
    }

    public List<Company> findAllCompanies(){
        return companyRepo.findAll();
    }



    public Company loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<Company> company = companyRepo.findByEmail(email);
        return companyRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Company not found ...."));
    }

    @Override
    public UserDetails loadUserByUsername(String emailAndRole) throws UsernameNotFoundException {
        return null;
    }

}
