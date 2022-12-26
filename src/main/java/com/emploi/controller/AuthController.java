package com.emploi.controller;


import com.emploi.helpers.AuthenticationRequest;
import com.emploi.model.Admin;
import com.emploi.security.JwtHandler;
import com.emploi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping
@RequiredArgsConstructor

public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AdminService adminService;
    private final JwtHandler jwtHandler;


    @GetMapping("/auth/")
    public String gets(){
        return "hhello";
    }

    @PostMapping("/auth/{role}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> authenticate(
            @PathVariable String role,
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       // authenticationRequest.getEmail(),
                        authenticationRequest.getEmail() + ":" + role,
                        authenticationRequest.getPassword())
        );
        Admin admin = adminService.loadUserByEmail(authenticationRequest.getEmail());
        //Admin admin = adminService.loadUserByEmail(authenticationRequest.getEmail() + ":" + role);

        System.out.println(admin + "tfuu");
        if(admin != null){
            return ResponseEntity.ok(jwtHandler.generateToken(admin));
        }

        return ResponseEntity.status(400).body("Error authenticating user");
    }
}
