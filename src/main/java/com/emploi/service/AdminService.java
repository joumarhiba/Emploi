package com.emploi.service;

import com.emploi.model.Admin;
import com.emploi.repository.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AdminService  implements UserDetailsService {
    private final AdminRepo adminRepo;

    public Admin loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepo.findByEmail(email);
        return adminRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("admin not found......"));
    }

    @Override
    public UserDetails loadUserByUsername(String emailAndRole) throws UsernameNotFoundException {
        Admin admin = null;
        System.out.println(" email and role " + emailAndRole);
        final String email = emailAndRole.split(":")[0];
        final String userRole = emailAndRole.split(":")[1];

        if("ADMIN".equals(userRole)){
            System.out.println(" inside load by Admin , the userRole "+userRole+ " email : "+email);
            admin = this.loadUserByEmail(email);
        }
        else if("COMPANY".equals(userRole)){
            System.out.println(" inside load by user name Company");
           // user = agentService.loadUserByEmail(email);
        }

        return admin;
    }
}
