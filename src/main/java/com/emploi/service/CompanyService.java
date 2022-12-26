package com.emploi.service;

import com.emploi.model.Company;
import com.emploi.model.Offre;
import com.emploi.repository.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService implements UserDetailsService{

    private final CompanyRepo companyRepo;

    public Company addOffre(Company company){
        return companyRepo.save(company);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
